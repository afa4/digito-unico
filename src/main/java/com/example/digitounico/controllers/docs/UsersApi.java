package com.example.digitounico.controllers.docs;

import com.example.digitounico.entities.AppUser;
import com.example.digitounico.entities.dto.KeyRequest;
import com.example.digitounico.entities.dto.UserRequest;
import com.example.digitounico.exceptions.ApplicationExceptionMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface UsersApi {

    @Operation(summary = "Creates an application user with name and email.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "User was created.",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Requested user email is already registered",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApplicationExceptionMessage.class)
                    )}
            )
    })
    ResponseEntity create(UserRequest user);

    @Operation(summary = "Recovers a list of registered users.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Returns the list of registered users.",
                    content = {@Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = AppUser.class))
                    )}
            )
    })
    ResponseEntity getAll();

    @Operation(summary = "Recovers a user by its universal id (UUID uid).")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Returns the user.",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AppUser.class)
                    )}
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found.",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApplicationExceptionMessage.class)
                    )}
            )
    })
    ResponseEntity getByUid(UUID uid);

    ResponseEntity update(UUID uid, UserRequest user);

    ResponseEntity deleteByUid(UUID uid);

    ResponseEntity encrypt(UUID uid, KeyRequest key);

    ResponseEntity decrypt(UUID uid, KeyRequest key);
}
