package com.example.digitounico.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class AppUser {
    @JsonIgnore
    private Long id;
    private UUID uid;
    private String name;
    private String email;
    private List<SingleDigit> singleDigits;
}
