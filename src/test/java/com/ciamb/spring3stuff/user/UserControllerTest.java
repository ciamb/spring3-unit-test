package com.ciamb.spring3stuff.user;

import com.ciamb.spring3stuff.user.dto.UserDto;
import com.ciamb.spring3stuff.user.dto.post.PostUserDto;
import com.ciamb.spring3stuff.user.exception.EmailAlreadyUsedException;
import com.ciamb.spring3stuff.user.exception.UserNotFoundException;
import com.ciamb.spring3stuff.user.exception.UsernameAlreadyUsedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService service;

    private UserController underTest;

    @BeforeEach()
    void setUp() {
        underTest = new UserController(service);
    }

    @Test
    @DisplayName("deve fare la post e tornare la response")
    void testCreate_shouldResponseWithSavedUser()
            throws EmailAlreadyUsedException, UsernameAlreadyUsedException {
        //dato
        PostUserDto postDto = new PostUserDto();
        UserDto responseDto = new UserDto();
        ResponseEntity<UserDto> responseEntity =
                new ResponseEntity<>(responseDto, HttpStatus.CREATED);

        //quando
        when(service.insert(postDto)).thenReturn(responseDto);

        ResponseEntity<UserDto> response = underTest.create(postDto);

        //allora
        assertThat(response).isNotNull();
        assertThat(response).isEqualTo(responseEntity);
        verify(service, times(1)).insert(postDto);
    }

    @Test
    @DisplayName("deve rispondere con lo user con l'id associato")
    void testGetById_shouldResponseWithUserWithId()
            throws UserNotFoundException {
        //dato
        int userId = 1;
        UserDto responseDto = new UserDto();
        ResponseEntity<UserDto> responseEntity =
                new ResponseEntity<>(responseDto, HttpStatus.OK);

        //quando
        when(service.findById(userId)).thenReturn(responseDto);

        ResponseEntity<UserDto> response = underTest.getById(userId);

        //allora
        assertThat(response).isNotNull();
        assertThat(response).isEqualTo(responseEntity);
        verify(service, times(1)).findById(userId);
    }
}