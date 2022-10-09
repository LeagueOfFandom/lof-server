package com.lof.lofserver.domain.team;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class TeamRepositoryTest {

    @Autowired
    private TeamRepository teamRepository;

    @Test
    @DisplayName("시리즈 아이디로 팀 전부 가져오기")
    void findAllBySeriesId() {
        //given
        Long seriesId = 1L;

        //when
        List<TeamEntity> allBySeriesId = teamRepository.findAllBySeriesId(seriesId);

        //then
        assertThat(allBySeriesId).isInstanceOf(List.class);
    }

}