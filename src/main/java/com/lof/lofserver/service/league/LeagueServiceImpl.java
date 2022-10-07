package com.lof.lofserver.service.league;

import com.lof.lofserver.domain.league.LeagueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LeagueServiceImpl implements LeagueService{

    private final LeagueRepository leagueRepository;

    //** 기본 리그 이름 리스트 가져오기
    private List<String> getBaseLeagueNameList(){
        return List.of("worlds");
    }
    @Override
    public List<Long> getBaseLeagueIdList() {
        //기본 리그 이름 리스트
        List<String> baseLeagueNameList = getBaseLeagueNameList();

        //기본 리그 이름 리스트로 기본 리그 아이디 리스트 가져오기
        return leagueRepository.findAllIdByName(baseLeagueNameList);
    }
}
