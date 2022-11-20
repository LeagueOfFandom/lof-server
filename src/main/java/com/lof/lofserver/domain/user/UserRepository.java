package com.lof.lofserver.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Boolean existsByEmail(String email);
    UserEntity findByEmail(String email);

    Boolean existsByNickname(String nickname);
}
