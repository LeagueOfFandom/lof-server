package com.lof.lofserver.service.match;

import com.lof.lofserver.domain.match.MatchEntity;
import com.lof.lofserver.domain.match.MatchRepository;
import com.lof.lofserver.domain.match.sub.Opponent;
import com.lof.lofserver.domain.match.sub.Result;
import com.lof.lofserver.domain.team.TeamRepository;
import com.lof.lofserver.domain.user.UserEntity;
import com.lof.lofserver.domain.user.UserRepository;
import com.lof.lofserver.service.match.detail.MatchDetailService;
import com.lof.lofserver.service.match.response.MatchView;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchServiceImpl implements MatchService {
    private final MatchDetailService matchDetailService;
    private final MatchRepository matchRepository;
    private final UserRepository userRepository;
    private final TeamRepository teamRepository;

    public List<MatchView> testLiveMatchViewList(Long userId){
        Long score = 1L;
        UserEntity userEntity = userRepository.findById(userId).orElse(null);
        if(userEntity.getAlarmList() != null && userEntity.getAlarmList().size() > 0)
            score = 2L;
        List<MatchView> matchViewList = new ArrayList<>();
        matchViewList.add(MatchView.builder()
                .matchId(651268L)
                .homeName("BFO")
                .homeImg("https://cdn.pandascore.co/images/team/image/126073/220px_saigon_buffalologo_square.png")
                .awayName("IST")
                .awayImg("https://cdn.pandascore.co/images/team/image/126066/220px_istanbul_wildcatslogo_square.png")
                .date("2022-11-06")
                .time("09:52:58")
                .league("Playoffs")
                .isAlarm(false)
                .homeScore(score)
                .awayScore(2L)
                .status("running")
                .videoLink("https://www.twitch.tv/lck_korea")
                .build());
        return matchViewList;
    }

    public List<MatchView> testMatchViewList(){
        List<MatchView> matchViewList = new ArrayList<>();
        matchViewList.add(MatchView.builder()
                .matchId(658691L)
                .homeName("GEN")
                .homeImg("https://cdn.pandascore.co/images/team/image/2882/geng-hooir6i9.png")
                .awayName("RNG")
                .awayImg("https://cdn.pandascore.co/images/team/image/74/royal-never-give-up-cyacqft1.png")
                .date("2022-11-03")
                .time("10:10:01")
                .league("Group D")
                .isAlarm(false)
                .homeScore(1L)
                .awayScore(0L)
                .status("finished")
                .videoLink("https://www.twitch.tv/lck_korea")
                .build());
        matchViewList.add(MatchView.builder()
                .matchId(658219L)
                .homeName("DRX")
                .homeImg("https://cdn.pandascore.co/images/team/image/126370/220px_dr_xlogo_square.png")
                .awayName("RGE")
                .awayImg("https://cdn.pandascore.co/images/team/image/3983/rogue__28_european_team_29logo_square.png")
                .date("2022-11-04")
                .time("09:56:36")
                .league("Group C")
                .isAlarm(false)
                .homeScore(1L)
                .awayScore(0L)
                .status("finished")
                .videoLink("https://www.twitch.tv/lck_korea")
                .build());
        matchViewList.add(MatchView.builder()
                .matchId(657771L)
                .homeName("DK")
                .homeImg("https://cdn.pandascore.co/images/team/image/128409/dwg_ki_alogo_square.png")
                .awayName("JDG")
                .awayImg("https://cdn.pandascore.co/images/team/image/318/qg-reapers.png")
                .date("2022-11-04")
                .time("10:10:01")
                .league("Group D")
                .isAlarm(false)
                .homeScore(0L)
                .awayScore(1L)
                .status("not_started")
                .videoLink("https://www.twitch.tv/lck_korea")
                .build());
        matchViewList.add(MatchView.builder()
                .matchId(651588L)
                .homeName("LLL")
                .homeImg("https://cdn.pandascore.co/images/team/image/128313/lou_dlogo_square.png")
                .awayName("EG")
                .awayImg("https://cdn.pandascore.co/images/team/image/2876/220px_evil_geniuses_2020logo_square.png")
                .date("2022-11-05")
                .time("11:56:23")
                .league("Play-In Group A")
                .isAlarm(false)
                .homeScore(0L)
                .awayScore(1L)
                .status("not_started")
                .videoLink("https://www.twitch.tv/lck_korea")
                .build());
        return matchViewList;
    }

    /**
     * ?????? ??? ???????????? ??? number ??? ????????????.
     * @param userEntity - ?????? ??????
     * @return List<Long>
     */
    private List<Long> getTeamIdList(UserEntity userEntity) {
        List<Long> teamIdList = new ArrayList<>();
        for(Long teamId : userEntity.getTeamList())
            teamIdList.add(teamRepository.findById(teamId).orElseThrow().getTeamNum());

        return teamIdList;
    }

    /**
     * matchEntity to matchView
     * @param matchEntity - match ?????????
     * @param isAlarm - ?????? ?????? ?????? ??????
     * @return MatchView
     */
    private MatchView matchEntityToMatchView(MatchEntity matchEntity, Boolean isAlarm){
        if(matchEntity.getOpponents() == null || matchEntity.getOpponents().size() < 2)
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
     * ???????????? ??????
     * @param userEntity - ?????? ?????????
     * @param matchEntity - ?????? ?????????
     * @return Boolean
     */
    private Boolean isAlarm(UserEntity userEntity, MatchEntity matchEntity){
        if(matchEntity.getOpponents() == null || matchEntity.getOpponents().size() < 2)
            return false;

        List<Long> teamIdList = getTeamIdList(userEntity);

        if(userEntity.getUserSelectedMatchList().containsKey(matchEntity.getId()))
            return userEntity.getUserSelectedMatchList().get(matchEntity.getId());
        else return teamIdList.contains(matchEntity.getOpponents().get(0).getOpponent().getId())
                || teamIdList.contains(matchEntity.getOpponents().get(1).getOpponent().getId());
    }

    /**
     * ????????? ??? ???????????? ????????? ?????? ????????? ??????
     * @param matchEntityList - ?????? ????????? ?????????
     * @param userEntity - ?????? ?????????
     * @return List<MatchView>
     */
    private List<MatchEntity> getMatchEntityListByUserTeam(List<MatchEntity> matchEntityList, UserEntity userEntity){
        List<Long> teamIdList = getTeamIdList(userEntity);
        List<MatchEntity> userTeamMatchEntityList = new ArrayList<>();
        for(MatchEntity matchEntity : matchEntityList){
            if(matchEntity.getOpponents() == null || matchEntity.getOpponents().size() < 2)
                continue;
            if(teamIdList.contains(matchEntity.getOpponents().get(0).getOpponent().getId())
                    || teamIdList.contains(matchEntity.getOpponents().get(1).getOpponent().getId()))
                userTeamMatchEntityList.add(matchEntity);
        }
        return userTeamMatchEntityList;
    }

    /**
     * ?????? ???????????? ?????? ????????? ??????
     * @param userId - ?????? ?????????
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
     * ????????? ?????? ????????? ??????
     * @param userId - ?????? ?????????
     * @param date - ??????
     * @param onlyMyTeam - ??? ?????? ?????? ??????
     * @return List<MatchView>
     */
    @Override
    public List<MatchView> getMatchListByDate(Long userId, LocalDate date, Boolean onlyMyTeam) {

        UserEntity userEntity = userRepository.findById(userId).orElseThrow();

        //?????? ????????? ?????? static date
        date = LocalDate.of(2022, 11, 1);

        //????????? matchEntityList
        List<MatchEntity> matchEntityList = new ArrayList<>();

        //????????? ????????? ?????? ??????
        int count = 0;
        while(matchEntityList.size() == 0){
            //korea time
            matchEntityList = matchRepository.findAllByBeginAtBetween(date.atStartOfDay().plusHours(9), date.atTime(23, 59, 59).plusHours(9));
            //??? ????????? ?????? ?????? ????????? ??? ??? ????????? ??????
            if(onlyMyTeam)
                matchEntityList = getMatchEntityListByUserTeam(matchEntityList, userEntity);

            date = date.plusDays(1);
            if(count++ > 10) break;
        }

        List<MatchView> matchViewList = new ArrayList<>();

        //???????????? ??????
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

        //??? ????????? ?????? ?????? ????????? ??? ??? ????????? ??????
        if(onlyMyTeam) matchEntityList = getMatchEntityListByUserTeam(matchEntityList, userEntity);

        List<MatchView> matchViewList = new ArrayList<>();
        for (MatchEntity matchEntity : matchEntityList)
            matchViewList.add(matchEntityToMatchView(matchEntity, isAlarm(userEntity, matchEntity)));

        return matchViewList;
    }

    @Override
    public ResponseEntity<?> getMatchDetail(Long matchId) {
        return matchDetailService.getTeamVsTeam(matchId);
    }
}
