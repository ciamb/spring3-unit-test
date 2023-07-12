package com.ciamb.spring3stuff.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository
        extends JpaRepository<UserEntity, Integer> {

    boolean existsByEmail(String email);
    boolean existsByUsername(String username);

}
