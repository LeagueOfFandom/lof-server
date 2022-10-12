package com.lof.lofserver.domain.match;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MatchRepository extends JpaRepository<MatchEntity,Long> {
    List<MatchEntity> findAllByStatus(String status);
    List<MatchEntity> findAllByBeginAtBetween(LocalDateTime start, LocalDateTime end);
}
