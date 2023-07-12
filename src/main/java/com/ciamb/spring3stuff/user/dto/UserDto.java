package com.ciamb.spring3stuff.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Integer id;
    private String username;
    private String email;
    private String password;
    private LocalDateTime registrationDate;
}
