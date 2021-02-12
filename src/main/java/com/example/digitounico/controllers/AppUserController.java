package com.example.digitounico.controllers;

import com.example.digitounico.entities.AppUser;
import com.example.digitounico.repositories.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class AppUserController {

    private final AppUserRepository appUserRepository;

    @PostMapping("/users")
    public ResponseEntity create(@RequestBody AppUser user) {
        appUserRepository.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/users")
    public ResponseEntity getAll() {
        return ResponseEntity.ok(appUserRepository.selectAll());
    }

    @GetMapping("/users/{uid}")
    public ResponseEntity getByUid(@PathVariable UUID uid) {
        return ResponseEntity.ok(appUserRepository.selectByUid(uid));
    }

    @PutMapping("/users/{uid}")
    public ResponseEntity update(@PathVariable UUID uid, @RequestBody AppUser user) {
        user.setUid(uid);
        appUserRepository.update(user);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/users/{uid}")
    public ResponseEntity deleteByUid(@PathVariable UUID uid) {
        appUserRepository.delete(uid);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
