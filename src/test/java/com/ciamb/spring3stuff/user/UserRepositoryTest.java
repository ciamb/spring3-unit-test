package com.ciamb.spring3stuff.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
class UserRepositoryTest {

    @Autowired
    private UserRepository underTest;

    @MockBean
    private Clock clock;

    @BeforeEach()
    void setUp() {
        when(clock.getZone()).thenReturn(ZoneId.of("Europe/Paris"));
        when(clock.instant()).thenReturn(Instant.now());
    }

    @Test
    @DisplayName("repo -> user esiste gia by email")
    void testExistsByEmail_shouldChckIfUsrnameIsAlreadyUsed() {
        // dato
        String email = "user@email.com";
        UserEntity entity = new UserEntity(
                1,
                "ciambellino",
                email,
                "kencowcn",
                LocalDateTime.now(clock)
        );

        // quando
        underTest.save(entity);
        boolean exist = underTest.existsByEmail(email);

        // allora
        assertThat(exist).isTrue();

    }

    @Test
    @DisplayName("repo -> user esiste gia by username")
    void existsByUsername() {
        String username = "ciambellino";
        UserEntity entity = new UserEntity(
                1,
                username,
                "user@email.com",
                "kencowcn",
                LocalDateTime.now(clock)
        );

        // quando
        underTest.save(entity);
        boolean exist = underTest.existsByUsername(username);

        // allora
        assertThat(exist).isTrue();

    }

}