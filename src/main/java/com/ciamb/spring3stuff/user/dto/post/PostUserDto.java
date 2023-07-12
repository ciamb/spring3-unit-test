package com.ciamb.spring3stuff.user.dto.post;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostUserDto {

    @Schema(name = "username", example = "ciambellino")
    @NotNull
    private String username;

    @Schema(name = "email", example = "user@mail.com")
    @NotNull
    private String email;

    @Schema(name = "password", example = "dhh8jwc.,298",
            description = "Deve andare dai 6 ai 15 caratteri")
    @NotNull
    private String password;

}
