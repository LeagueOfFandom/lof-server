package com.lof.lofserver.service.league;

import com.lof.lofserver.domain.league.LeagueEntity;
import com.lof.lofserver.domain.league.LeagueRepository;
import com.lof.lofserver.domain.user.UserEntity;
import com.lof.lofserver.domain.user.UserRepository;
import com.lof.lofserver.service.league.response.AllLeagueAndTeamList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LeagueServiceImpl implements LeagueService{

    private final LeagueRepository leagueRepository;
    private final UserRepository userRepository;

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

    @Override
    public AllLeagueAndTeamList getAllLeagueAndTeamList(Long userId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow();

        List<Long> leagueIdList = getBaseLeagueIdList();
        List<LeagueEntity> leagueEntityList = leagueRepository.findAllById(leagueIdList);

        for(LeagueEntity leagueEntity : leagueEntityList){
            Long currentSeriesId = leagueEntity.getLatestSeriesId();
        }

        return null;
    }
}
