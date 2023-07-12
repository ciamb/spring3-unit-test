package com.ciamb.spring3stuff.user;

import com.ciamb.spring3stuff.global.ProblemDetailWrapper;
import com.ciamb.spring3stuff.user.dto.UserDto;
import com.ciamb.spring3stuff.user.dto.post.PostUserDto;
import com.ciamb.spring3stuff.user.exception.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @Operation(summary = "Crea un utente",
            description = "Ritorna l'utente creato con l'Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Utente creato correttamente",
                    content = @Content(
                            schema = @Schema(allOf = UserDto.class),
                            mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "400",
                    description = "Errore durante la creazione dell'utente," +
                            " l'email o lo username sono già presenti",
                    content = @Content(
                            schema = @Schema(allOf = ProblemDetailWrapper.class),
                            mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
            )
    })
    @PostMapping()
    protected ResponseEntity<UserDto> create(@RequestBody PostUserDto dto)
            throws EmailAlreadyUsedException, UsernameAlreadyUsedException {

        UserDto userDto = service.insert(dto);
        ResponseEntity<UserDto> response =
                new ResponseEntity<>(userDto, HttpStatus.CREATED);
        return response;
    }

    @Operation(summary = "Trova un utente tramite id",
            description = "Ritorna l'utente con l'id richiesto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Utente con id richiesto trovato",
                    content = @Content(
                            schema = @Schema(allOf = UserDto.class),
                            mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404",
                    description = "Utente con id richiesto non trovato",
                    content = @Content(
                            schema = @Schema(allOf = ProblemDetailWrapper.class),
                            mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE))
    })
    @GetMapping("/{id}")
    protected ResponseEntity<UserDto> getById(
            @PathVariable @NotNull Integer id) throws UserNotFoundException {

        UserDto userDto = service.findById(id);
        ResponseEntity<UserDto> response =
                new ResponseEntity<>(userDto, HttpStatus.OK);
        return response;
    }
}
