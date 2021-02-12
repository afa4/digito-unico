package com.example.digitounico.entities.dto;

import com.example.digitounico.entities.AppUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class UserRequest {
    private @NotNull String name;
    private @NotNull @Email String email;

    public AppUser toAppUser() {
        return AppUser.builder()
                .name(name)
                .email(email)
                .build();
    }

    public AppUser toAppUser(UUID uid) {
        return AppUser.builder()
                .uid(uid)
                .name(name)
                .email(email)
                .build();
    }
}
