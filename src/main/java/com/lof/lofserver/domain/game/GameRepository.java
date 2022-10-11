package com.lof.lofserver.domain.game;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository extends JpaRepository<GameEntity, Long> {

    List<GameEntity> findAllByStatus(String status);
}
