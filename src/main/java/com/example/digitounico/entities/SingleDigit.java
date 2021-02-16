package com.example.digitounico.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SingleDigit {
    private String integer;
    private Integer repeatTimes;
    private String singleDigit;
}
