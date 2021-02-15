package com.example.digitounico.controllers;

import com.example.digitounico.entities.dto.SingleDigitResponse;
import com.example.digitounico.services.SingleDigitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SingleDigitController {

    private final SingleDigitService singleDigitService;

    @GetMapping("/single-digits")
    public ResponseEntity create(@RequestParam("integer") String integer,
                                 @RequestParam("repeat-times") Integer repeatTimes) {
        var singleDigit = singleDigitService.getSingleDigit(integer, repeatTimes);
        return ResponseEntity.ok(new SingleDigitResponse(singleDigit));
    }
}
