package com.lof.lofserver.service.match;

import com.lof.lofserver.domain.match.MatchEntity;
import com.lof.lofserver.domain.match.MatchRepository;
import com.lof.lofserver.domain.match.sub.Opponent;
import com.lof.lofserver.domain.match.sub.Result;
import com.lof.lofserver.domain.user.UserEntity;
import com.lof.lofserver.domain.user.UserRepository;
import com.lof.lofserver.service.match.response.MatchView;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchServiceImpl implements MatchService {

    private final MatchRepository matchRepository;
    private final UserRepository userRepository;

    /**
     * matchEntity to matchView
     * @param matchEntity - match 엔티티
     * @param isAlarm - 유저 알람 설정 여부
     * @return MatchView
     */
    private MatchView matchEntityToMatchView(MatchEntity matchEntity, Boolean isAlarm){
        if(matchEntity.getOpponents().size() < 2)
            return null;
        Opponent homeTeamInfo = matchEntity.getOpponents().get(0).getOpponent();
        Opponent awayTeamInfo = matchEntity.getOpponents().get(1).getOpponent();
        Result homeTeamResult = matchEntity.getResults().get(0);
        Result awayTeamResult = matchEntity.getResults().get(1);
        //korea time
        LocalDateTime scheduledAt = matchEntity.getBeginAt().plusHours(9);

        return MatchView.builder()
                .matchId(matchEntity.getId())
                .homeName(homeTeamInfo.getAcronym())
                .homeImg(homeTeamInfo.getImage_url())
                .homeScore(homeTeamResult.getScore())
                .awayName(awayTeamInfo.getAcronym())
                .awayImg(awayTeamInfo.getImage_url())
                .awayScore(awayTeamResult.getScore())
                .date(scheduledAt.toLocalDate().toString())
                .time(scheduledAt.toLocalTime().toString())
                .league(matchEntity.getTournament().getName())
                .status(matchEntity.getStatus())
                .isAlarm(isAlarm)
                .build();
    }

    /**
     * 알람여부 확인
     * @param userEntity - 유저 엔티티
     * @param matchEntity - 매치 엔티티
     * @return Boolean
     */
    private Boolean isAlarm(UserEntity userEntity, MatchEntity matchEntity){
        if(matchEntity.getOpponents().size() < 2)
            return false;

        if(userEntity.getUserSelectedMatchList().containsKey(matchEntity.getId()))
            return userEntity.getUserSelectedMatchList().get(matchEntity.getId());
        else return userEntity.getTeamList().contains(matchEntity.getOpponents().get(0).getOpponent().getId())
                || userEntity.getTeamList().contains(matchEntity.getOpponents().get(1).getOpponent().getId());
    }

    /**
     * 유저의 팀 리스트에 포함된 매치 리스트 반환
     * @param matchEntityList - 매치 엔티티 리스트
     * @param userEntity - 유저 엔티티
     * @return List<MatchView>
     */
    private List<MatchEntity> getMatchEntityListByUserTeam(List<MatchEntity> matchEntityList, UserEntity userEntity){
        List<MatchEntity> userTeamMatchEntityList = new ArrayList<>();
        for(MatchEntity matchEntity : matchEntityList){
            if(userEntity.getTeamList().contains(matchEntity.getOpponents().get(0).getOpponent().getId())
                    || userEntity.getTeamList().contains(matchEntity.getOpponents().get(1).getOpponent().getId()))
                userTeamMatchEntityList.add(matchEntity);
        }
        return userTeamMatchEntityList;
    }

    /**
     * 현재 진행중인 게임 리스트 조회
     * @param userId - 유저 아이디
     * @return List<MatchView>
     */
    @Override
    public List<MatchView> getLiveMatchList(Long userId) {
        List<MatchView> matchViewList = new ArrayList<>();

        UserEntity userEntity = userRepository.findById(userId).orElseThrow();
        List<MatchEntity> matchEntityList = matchRepository.findAllByStatus("running");

        for(MatchEntity matchEntity : matchEntityList)
            matchViewList.add(matchEntityToMatchView(matchEntity, isAlarm(userEntity, matchEntity)));

        return matchViewList;
    }

    /**
     * 날짜별 게임 리스트 조회
     * @param userId - 유저 아이디
     * @param date - 날짜
     * @param onlyMyTeam - 내 팀만 조회 여부
     * @return List<MatchView>
     */
    @Override
    public List<MatchView> getMatchListByDate(Long userId, LocalDate date, Boolean onlyMyTeam) {

        UserEntity userEntity = userRepository.findById(userId).orElseThrow();

        //반환할 matchEntityList
        List<MatchEntity> matchEntityList = new ArrayList<>();

        //오늘자 경기가 없을 경우
        int count = 0;
        while(matchEntityList.size() == 0){
            //korea time
            matchEntityList = matchRepository.findAllByBeginAtBetween(date.atStartOfDay().plusHours(9), date.atTime(23, 59, 59).plusHours(9));
            //내 팀만이 선택 되어 있다면 내 팀 경기만 반환
            if(onlyMyTeam)
                matchEntityList = getMatchEntityListByUserTeam(matchEntityList, userEntity);

            date = date.plusDays(1);
            if(count++ > 10) break;
        }

        List<MatchView> matchViewList = new ArrayList<>();

        //알람여부 반영
        for(MatchEntity matchEntity : matchEntityList)
            matchViewList.add(matchEntityToMatchView(matchEntity, isAlarm(userEntity, matchEntity)));

        return matchViewList;
    }

    @Override
    public List<MatchView> getMatchListByMonth(Long userId, LocalDate date, Boolean onlyMyTeam) {

        LocalDate startDateOfMonth = date.withDayOfMonth(1);
        LocalDate lastDateOfMonth = date.withDayOfMonth(date.lengthOfMonth());

        //korea time
        List<MatchEntity> matchEntityList = matchRepository.findAllByBeginAtBetween(startDateOfMonth.atStartOfDay().plusHours(9), lastDateOfMonth.atTime(23, 59, 59).plusHours(9));

        UserEntity userEntity = userRepository.findById(userId).orElseThrow();

        //내 팀만이 선택 되어 있다면 내 팀 경기만 반환
        if(onlyMyTeam) matchEntityList = getMatchEntityListByUserTeam(matchEntityList, userEntity);

        List<MatchView> matchViewList = new ArrayList<>();
        for (MatchEntity matchEntity : matchEntityList)
            matchViewList.add(matchEntityToMatchView(matchEntity, isAlarm(userEntity, matchEntity)));

        return matchViewList;
    }
}
