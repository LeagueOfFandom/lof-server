package com.lof.lofserver.domain.match;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchRepository extends JpaRepository<MatchEntity,Long> {
    List<MatchEntity> findAllByStatus(String status);
}
