package com.lof.lofserver.service.match;

import com.lof.lofserver.service.match.response.MatchView;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MatchServiceImplTest {

    @InjectMocks
    MatchServiceImpl matchService;

    @Test
    @DisplayName("라이브 매치 가져오기")
    void getLiveMatch() {
        //given
        Long userId = 1L;

        //when
        Object result = matchService.getLiveMatchList(userId);

        //then
        assertNotNull(result);
        assertThat(result).isInstanceOf(MatchView.class);

    }

}