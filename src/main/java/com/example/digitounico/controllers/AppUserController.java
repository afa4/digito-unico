package com.example.digitounico.controllers;

import com.example.digitounico.entities.AppUser;
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
    public ResponseEntity create(@RequestBody AppUser user) {
        appUserService.create(user);
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
    public ResponseEntity update(@PathVariable UUID uid, @RequestBody AppUser user) {
        user.setUid(uid);
        appUserService.updateOrCreate(user);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/users/{uid}")
    public ResponseEntity deleteByUid(@PathVariable UUID uid) {
        appUserService.delete(uid);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
