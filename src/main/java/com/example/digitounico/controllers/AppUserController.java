package com.example.digitounico.controllers;

import com.example.digitounico.entities.dto.UserRequest;
import com.example.digitounico.services.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class AppUserController {

    private final AppUserService appUserService;

    @PostMapping("/users")
    public ResponseEntity create(@RequestBody UserRequest user) {
        appUserService.create(null, user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/users")
    public ResponseEntity getAll() {
        return ResponseEntity.ok(appUserService.findAll());
    }

    @GetMapping("/users/{uid}")
    public ResponseEntity getByUid(@PathVariable UUID uid) {
        return ResponseEntity.ok(appUserService.findByUid(uid));
    }

    @PutMapping("/users/{uid}")
    public ResponseEntity update(@PathVariable UUID uid, @RequestBody UserRequest user) {
        appUserService.updateOrCreate(uid, user);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/users/{uid}")
    public ResponseEntity deleteByUid(@PathVariable UUID uid) {
        appUserService.delete(uid);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/users/{uid}/encrypt")
    public ResponseEntity encrypt(@PathVariable UUID uid) {
        return null;
    }

}
