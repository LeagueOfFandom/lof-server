package com.lof.lofserver.domain.league;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class LeagueRepositoryTest {
    @Autowired
    private LeagueRepository leagueRepository;

    @Test
    @DisplayName("이름으로 리그 검색 - 실패")
    void findLeagueByNameFail() {
        //given
        List<String> name = List.of("리그1", "리그2", "리그3");

        //when
        List<Long> allIdByName = leagueRepository.findAllIdByName(name);

        //then
        assertThat(allIdByName).isEmpty();
    }

}