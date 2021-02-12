package com.example.digitounico.entities;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class AppUser {
    private Long id;
    private UUID uid;
    private String name;
    private String email;
}
