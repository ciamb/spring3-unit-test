package com.ciamb.spring3stuff.user;

import com.ciamb.spring3stuff.user.dto.UserDto;
import com.ciamb.spring3stuff.user.dto.post.PostUserDto;
import com.ciamb.spring3stuff.user.exception.EmailAlreadyUsedException;
import com.ciamb.spring3stuff.user.exception.UserNotFoundException;
import com.ciamb.spring3stuff.user.exception.UsernameAlreadyUsedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repo;
    private final UserMapper mapper;

    public UserDto insert(PostUserDto postDto)
            throws UsernameAlreadyUsedException, EmailAlreadyUsedException {

        existsByUsername(postDto.getUsername());
        existsByEmail(postDto.getEmail());

        UserEntity user = repo
                .save(mapper.toEntityFromPost(postDto));
        return mapper.toDto(user);
    }

    public UserDto findById(Integer id) throws UserNotFoundException {
        UserEntity user = repo
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return mapper.toDto(user);
    }

    public void existsByEmail(String email)
            throws EmailAlreadyUsedException {

        if (repo.existsByEmail(email))
            throw new EmailAlreadyUsedException(email);
    }

    public void existsByUsername(String username)
            throws UsernameAlreadyUsedException {

        if (repo.existsByUsername(username))
            throw new UsernameAlreadyUsedException(username);
    }

}
