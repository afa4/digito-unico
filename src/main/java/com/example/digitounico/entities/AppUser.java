package com.example.digitounico.entities;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Builder
public class AppUser {
    private Long id;
    private UUID uid;
    private @NotNull String name;
    private @NotNull String email;
}
