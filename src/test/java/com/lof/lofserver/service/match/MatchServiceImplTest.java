package com.lof.lofserver.service.match;

import com.lof.lofserver.domain.match.MatchRepository;
import com.lof.lofserver.domain.user.UserEntity;
import com.lof.lofserver.domain.user.UserRepository;
import com.lof.lofserver.service.match.response.MatchView;
import com.vladmihalcea.hibernate.type.array.internal.LocalDateArrayTypeDescriptor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class MatchServiceImplTest {

    @InjectMocks
    MatchServiceImpl matchService;

    @Mock
    MatchRepository matchRepository;

    @Mock
    UserRepository userRepository;

    @Test
    @DisplayName("라이브 매치 가져오기")
    void getLiveMatch() {
        //given
        Long userId = 1L;
        given(userRepository.findById(any(Long.class))).willReturn(getUserEntityTest());
        given(matchRepository.findAllByStatus(any(String.class))).willReturn(List.of());

        //when
        Object result = matchService.getLiveMatchList(userId);

        //then
        assertNotNull(result);
        assertThat(result).isInstanceOf(ArrayList.class);

    }

    @Test
    @DisplayName("이번달 매치 가져오기")
    void getThisMonthMatch() {
        //given
        Long userId = 1L;
        given(userRepository.findById(any(Long.class))).willReturn(getUserEntityTest());
        given(matchRepository.findAllByBeginAtBetween(any(LocalDateTime.class),any(LocalDateTime.class))).willReturn(List.of());

        //when
        Object result = matchService.getMatchListByMonth(userId, LocalDate.now(), false);

        //then
        assertNotNull(result);
        assertThat(result).isInstanceOf(ArrayList.class);

    }

    @Test
    @DisplayName("매치 정보 가져오기")
    void getMatchInfo(){
        //given
        Long userId = 1L;
        given(userRepository.findById(any(Long.class))).willReturn(getUserEntityTest());
        given(matchRepository.findAllByBeginAtBetween(any(LocalDateTime.class),any(LocalDateTime.class))).willReturn(List.of());

        //when
        List<MatchView> matchListByDate = matchService.getMatchListByDate(userId, LocalDate.now(), true);

        //then
        assertNotNull(matchListByDate);
        assertThat(matchListByDate).isInstanceOf(ArrayList.class);
    }


    private Optional<UserEntity> getUserEntityTest(){
        //user alarm setting
        Map<Long, Boolean> alarmMap = new HashMap<>();
        alarmMap.put(1L, true);
        //userEntity setting
        UserEntity userEntity = UserEntity.builder().email("test").build();
        userEntity.setUserSelectedMatchList(alarmMap);
        //return
        return Optional.of(userEntity);
    }

}