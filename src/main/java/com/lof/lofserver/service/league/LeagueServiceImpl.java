package com.lof.lofserver.service.league;

import com.lof.lofserver.domain.league.LeagueEntity;
import com.lof.lofserver.domain.league.LeagueRepository;
import com.lof.lofserver.domain.team.TeamEntity;
import com.lof.lofserver.domain.team.TeamRepository;
import com.lof.lofserver.domain.user.UserEntity;
import com.lof.lofserver.domain.user.UserRepository;
import com.lof.lofserver.service.league.response.AllLeagueAndTeamList;
import com.lof.lofserver.service.league.response.sub.LeagueInfo;
import com.lof.lofserver.service.league.response.sub.TeamInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LeagueServiceImpl implements LeagueService{

    private final LeagueRepository leagueRepository;
    private final UserRepository userRepository;
    private final TeamRepository teamRepository;

    //** 기본 리그 이름 리스트 가져오기
    private List<String> getBaseLeagueNameList(){
        return List.of("worlds");
    }

    private List<Long> getBaseLeagueIdList() {
        //기본 리그 이름 리스트
        List<String> baseLeagueNameList = getBaseLeagueNameList();

        //기본 리그 이름 리스트로 기본 리그 아이디 리스트 가져오기
        return leagueRepository.findAllIdByName(baseLeagueNameList);
    }

    /** 리그 정보 생성
     * @param leagueSlug - 리그 설명
     * @param teamEntityList - 리그에 속한 팀 엔티티 리스트
     * @param userSelectedTeamIdList - 유저가 선택한 팀 아이디 리스트
     * @return LeagueInfo - 리그 정보
     */
    private LeagueInfo createLeagueInfoByLeagueSlugAndTeamEntityListAndUserTeamList(String leagueSlug,List<TeamEntity> teamEntityList, List<Long> userSelectedTeamIdList){
        //리그의 팀 정보 리스트 생성
        List<TeamInfo> teamInfoList = teamEntityList.stream()
                .map(teamEntity -> TeamInfo.builder()
                        .teamId(teamEntity.getId())
                        .teamName(teamEntity.getName())
                        .teamImg(teamEntity.getImageUrl())
                        .teamCheck(userSelectedTeamIdList.contains(teamEntity.getId()))
                        .build())
                .toList();

        //리그 정보 생성
        return LeagueInfo.builder()
                .note(leagueSlug)
                .teamInfo(teamInfoList)
                .build();
    }


    /** 기본 리그 리스트 가져오기
     * @param userId - 유저 아이디
     * @return AllLeagueAndTeamList - 기본 리그 리스트
     */
    @Override
    public AllLeagueAndTeamList getAllLeagueAndTeamList(Long userId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow();

        //기본 리그 리스트 가져오기
        List<Long> leagueIdList = getBaseLeagueIdList();
        List<LeagueEntity> leagueEntityList = leagueRepository.findAllById(leagueIdList);

        //반환할 리그 정보 리스트 생성
        List<String> leagueNameList = new ArrayList<>();
        List<LeagueInfo> leagueInfoList = new ArrayList<>();

        //리그 정보 생성
        for(LeagueEntity leagueEntity : leagueEntityList){
            //리그 별 최근 팀 리스트 가져오기
            List<TeamEntity> teamEntityList = teamRepository.findAllBySeriesId(leagueEntity.getLatestSeriesId());
            //유저 정보를 포함한 전체 팀 정보 생성
            leagueNameList.add(leagueEntity.getName());
            leagueInfoList.add(createLeagueInfoByLeagueSlugAndTeamEntityListAndUserTeamList(leagueEntity.getSlug(), teamEntityList, userEntity.getTeamList()));
        }

        return AllLeagueAndTeamList.builder()
                .leagueNameList(leagueNameList)
                .leagueInfoList(leagueInfoList)
                .build();
    }
}
