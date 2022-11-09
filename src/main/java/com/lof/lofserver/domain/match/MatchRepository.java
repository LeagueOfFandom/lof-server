package com.lof.lofserver.domain.match;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface MatchRepository extends JpaRepository<MatchEntity,Long> {
    List<MatchEntity> findAllByStatus(String status);
    List<MatchEntity> findAllByBeginAtBetween(LocalDateTime start, LocalDateTime end);

    @Query(value = "select * from match_list where status='finished' and ( (json_value(opponents,'$[0].opponent.id') = ?1 and json_value(opponents,'$[1].opponent.id') = ?2) or (json_value(opponents,'$[0].opponent.id') = ?2 and json_value(opponents,'$[1].opponent.id') = ?1))", nativeQuery = true)
    List<MatchEntity> findAllByTeamIds(Long homeId, Long awayId);
}
