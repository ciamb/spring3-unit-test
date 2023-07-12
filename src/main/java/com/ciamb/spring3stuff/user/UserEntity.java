package com.ciamb.spring3stuff.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity(name = "my_user")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username", nullable = false)
    @Size(max = 12, message = "Non può contenere più di 12 caratteri")
    private String username;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    @Size(min = 6, max = 15, message = "La password deve andare dai 6 ai 15 caratteri")
    private String password;

    @Column(name = "date_of_signup")
    private LocalDateTime registrationDate;

    @PrePersist
    public void setRegistrationDate() {
        this.registrationDate = LocalDateTime.now(Clock.system(ZoneId.of("Europe/Paris")));
    }

}
