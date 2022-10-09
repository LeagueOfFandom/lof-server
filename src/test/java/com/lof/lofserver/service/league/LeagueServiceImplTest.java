package com.lof.lofserver.service.league;

import com.lof.lofserver.domain.league.LeagueRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class LeagueServiceImplTest {

    @InjectMocks
    private LeagueServiceImpl leagueService;

    @Mock
    private LeagueRepository leagueRepository;

    @Test
    @DisplayName("기본 리그 생성 테스트")
    void createBaseLeague(){
        //given
        given(leagueRepository.findAllIdByName(any())).willReturn(List.of(1L, 2L, 3L));

        //when

        //then
    }
}