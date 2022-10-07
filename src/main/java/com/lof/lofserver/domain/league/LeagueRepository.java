package com.lof.lofserver.domain.league;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LeagueRepository extends JpaRepository<LeagueEntity, Long> {

    @Query("select l.id from LeagueEntity l where l.name in ?1")
    List<Long> findAllIdByName(List<String> nameList);
}
