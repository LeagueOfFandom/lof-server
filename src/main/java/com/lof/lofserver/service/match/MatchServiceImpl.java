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
            matchViewList.add(matchEntityToMatchView(matchEntity, userEntity.getUserSelectedMatchList().containsKey(matchEntity.getId())));

        return matchViewList;
    }
}
