package com.example.digitounico.entities.dto;

import com.example.digitounico.entities.SingleDigit;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SingleDigitRequest {
    private String integer;
    private Integer repeatTimes;

    public SingleDigit toSingleDigit(Long userId, int singleDigit) {
        return SingleDigit.builder()
                .appUserId(userId)
                .integer(integer)
                .repeatTimes(repeatTimes)
                .singleDigit(singleDigit)
                .build();
    }
}
