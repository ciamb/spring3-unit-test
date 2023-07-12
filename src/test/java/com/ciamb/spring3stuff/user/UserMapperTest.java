package com.ciamb.spring3stuff.user;

import com.ciamb.spring3stuff.user.dto.UserDto;
import com.ciamb.spring3stuff.user.dto.post.PostUserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class UserMapperTest {

    private UserMapper underTest;

    @BeforeEach
    void setUp() {
        underTest = new UserMapper();
    }

    @Test
    @DisplayName("deve mappare UserEntity in UserDto")
    void testToDto_shouldMapUserEntityToDto() {
        //dato
        UserEntity entity = new UserEntity();
        UserDto dto = new UserDto();

        //quando
        UserDto mappedDto = underTest.toDto(entity);

        //allora
        assertThat(mappedDto).isNotNull();
        assertThat(mappedDto).hasAllNullFieldsOrProperties();
        assertThat(mappedDto).isEqualTo(dto);
    }

    @Test
    @DisplayName("deve mappare da postdto a entity")
    void testToEntityFromPost_shouldMapPostUserDtoToUserEntity() {
        //dato
        UserEntity entity = new UserEntity();
        PostUserDto postDto = new PostUserDto();

        //quando
        UserEntity mappedEntity = underTest.toEntityFromPost(postDto);

        //allora
        assertThat(mappedEntity).isNotNull();
        assertThat(mappedEntity).hasAllNullFieldsOrProperties();
        assertThat(mappedEntity).isEqualTo(entity);
    }
}