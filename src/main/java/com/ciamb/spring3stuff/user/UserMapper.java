package com.ciamb.spring3stuff.user;

import com.ciamb.spring3stuff.user.dto.UserDto;
import com.ciamb.spring3stuff.user.dto.post.PostUserDto;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    UserDto toDto(@NotNull UserEntity entity) {
        return new UserDto(
                entity.getId(),
                entity.getUsername(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getRegistrationDate());
    }

    UserEntity toEntityFromPost(@NotNull PostUserDto dto) {
        return UserEntity.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build();
    }
}
