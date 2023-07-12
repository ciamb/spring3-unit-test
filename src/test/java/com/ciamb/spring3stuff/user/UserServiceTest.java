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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository repo;
    @Mock
    private UserMapper mapper;

    @InjectMocks
    private UserService underTest; //cosa sto andando a testare

    @BeforeEach
    void setUp() {
        underTest = new UserService(repo, mapper);
    }

    @Test
    @DisplayName("test aggiunta user")
    void testInsert_itShouldAddUser()
            throws EmailAlreadyUsedException, UsernameAlreadyUsedException {
        // dato
        PostUserDto postDto = new PostUserDto();
        UserDto responseDto = new UserDto();
        UserEntity entity = new UserEntity();

        // quando
        when(mapper.toEntityFromPost(postDto)).thenReturn(entity);
        when(repo.save(entity)).thenReturn(entity);
        when(mapper.toDto(entity)).thenReturn(responseDto);

        UserDto response = underTest.insert(postDto);

        // allora
        assertThat(response).isNotNull();
        assertThat(response).hasAllNullFieldsOrProperties(); // aggiungo questo test solo perché non sto inizializzando gli oggetti
        assertThat(response).isEqualTo(responseDto);
        verify(mapper, times(1)).toEntityFromPost(postDto);
        verify(repo, times(1)).save(entity); //il verify viene fatto sui mock, in questo caso repo e mapper
        verify(mapper, times(1)).toDto(entity);
    }

    @Test
    @DisplayName("test findById trovato")
    void testFindById_itShouldFindUserById() throws UserNotFoundException {
        // dato
        int userId = 1;
        UserDto responseDto = new UserDto();
        UserEntity entity = new UserEntity();

        // quando
        when(repo.findById(userId)).thenReturn(Optional.of(entity));
        when(mapper.toDto(entity)).thenReturn(responseDto);

        UserDto response = underTest.findById(userId);

        // allora
        assertThat(response).isNotNull();
        assertThat(response).hasAllNullFieldsOrProperties();
        assertThat(response).isEqualTo(responseDto);
        verify(repo, times(1)).findById(userId);
    }

    @Test
    @DisplayName("test findById non trovato + mesaggio eccezione")
    void testFindById_itShouldNotFoundTheUserAndThrowNotFoundException()
            throws UserNotFoundException {
        // dato
        int userId = 1;
        String expected = "L'utente con id " + userId + " non è stato trovato";

        // quando
        when(repo.findById(userId)).thenReturn(Optional.empty());
        Exception ex = assertThrows(UserNotFoundException.class, () -> {
            underTest.findById(userId);
        });

        String message = ex.toString();

        // allora
        assertTrue(message.contains(expected));
        verify(repo, times(1)).findById(userId);
    }

    @Test
    @DisplayName("lancia eccezione se esiste username")
    void testExistsByUsername_itShouldThrowException()
            throws UsernameAlreadyUsedException {
        //dato
        String username = "ciambellia";
        String expected = "Username " + username + " already in use";

        //quando
        when(repo.existsByUsername(username)).thenReturn(true);
        Exception ex = assertThrows(UsernameAlreadyUsedException.class, () -> {
            underTest.existsByUsername(username);
        });

        String message = ex.toString();

        //allora
        assertThat(message).isNotNull();
        assertTrue(message.contains(expected));
        verify(repo, times(1)).existsByUsername(username);
    }

    @Test
    @DisplayName("lancia eccezione se esiste email")
    void testExistsByEmail_itShouldThrowException()
            throws EmailAlreadyUsedException {
        //dato
        String email = "user@email.com";
        String expected = "Email " + email + " already in use";

        //quando
        when(repo.existsByEmail(email)).thenReturn(true);
        Exception ex = assertThrows(EmailAlreadyUsedException.class, () -> {
            underTest.existsByEmail(email);
        });

        String message = ex.toString();

        //allora
        assertThat(message).isNotNull();
        assertTrue(message.contains(expected));
        verify(repo, times(1)).existsByEmail(email);
    }
}