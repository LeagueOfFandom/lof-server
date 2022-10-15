package com.lof.lofserver.service.league;

import com.lof.lofserver.domain.league.LeagueRepository;
import com.lof.lofserver.domain.team.TeamEntity;
import com.lof.lofserver.domain.team.TeamRepository;
import com.lof.lofserver.domain.user.UserEntity;
import com.lof.lofserver.domain.user.UserRepository;
import com.lof.lofserver.service.league.response.BaseLeagueAndTeamList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class LeagueServiceImplTest {

    @InjectMocks
    LeagueServiceImpl leagueService;

    @Mock
    UserRepository userRepository;

    @Mock
    LeagueRepository leagueRepository;

    @Mock
    TeamRepository teamRepository;

    @Test
    @DisplayName("전체 리그 생성 테스트")
    void createAllLeague(){
        //given
        Long userId = 1L;
        given(leagueRepository.findAllIdByName(any())).willReturn(null);
        given(userRepository.findById(userId)).willReturn(Optional.of(UserEntity.builder().build()));

        //when
        Object baseLeagueAndTeamList = leagueService.getAllLeagueAndTeamListByUserId(1L);

        //then
        assertThat(baseLeagueAndTeamList).isInstanceOf(BaseLeagueAndTeamList.class);
    }

    @Test
    @DisplayName("팀 리스트 업데이트 테스트")
    void updateTeamList(){
        //given
        Long userId = 1L;
        List<Long> beforeTeamList = List.of(1L, 2L, 3L);
        List<Long> afterTeamList = List.of( 2L, 3L, 4L);

        UserEntity userEntity = UserEntity.builder().build();
        userEntity.setTeamList(beforeTeamList);
        UserEntity newUserEntity = UserEntity.builder().build();
        newUserEntity.setTeamList(afterTeamList);

        given(userRepository.findById(userId)).willReturn(Optional.of(userEntity));
        given(teamRepository.existsById(any())).willReturn(true);
        given(userRepository.save(any())).willReturn(newUserEntity);

        //when
        List<Long> teamListByUserId = leagueService.setTeamListByUserId(1L, afterTeamList);

        //then
        assertThat(teamListByUserId).isInstanceOf(List.class);
        assertThat(teamListByUserId).isEqualTo(afterTeamList);
    }
}