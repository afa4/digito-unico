package com.example.digitounico.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SingleDigit {
    @JsonIgnore
    private Long appUserId;
    private String integer;
    private Integer repeatTimes;
    private Integer singleDigit;
}
