package com.example.digitounico.controllers;

import com.example.digitounico.entities.dto.UserRequest;
import com.example.digitounico.services.UsersCrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UsersController {

    private final UsersCrudService usersCrudService;

    @PostMapping("/users")
    public ResponseEntity create(@RequestBody UserRequest user) {
        usersCrudService.create(user.toAppUser());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/users")
    public ResponseEntity getAll() {
        return ResponseEntity.ok(usersCrudService.findAll());
    }

    @GetMapping("/users/{uid}")
    public ResponseEntity getByUid(@PathVariable UUID uid) {
        return ResponseEntity.ok(usersCrudService.findByUid(uid));
    }

    @PutMapping("/users/{uid}")
    public ResponseEntity update(@PathVariable UUID uid, @RequestBody UserRequest user) {
        usersCrudService.updateOrCreate(user.toAppUser(uid));
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/users/{uid}")
    public ResponseEntity deleteByUid(@PathVariable UUID uid) {
        usersCrudService.delete(uid);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/users/{uid}/encrypt")
    public ResponseEntity encrypt(@PathVariable UUID uid) {
        return null;
    }

    @PostMapping("/users/{uid}/decrypt")
    public ResponseEntity decrypt(@PathVariable UUID uid) {
        return null;
    }

}
