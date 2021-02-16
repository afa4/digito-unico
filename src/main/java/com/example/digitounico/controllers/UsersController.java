package com.example.digitounico.controllers;

import com.example.digitounico.controllers.docs.UsersApi;
import com.example.digitounico.entities.dto.KeyRequest;
import com.example.digitounico.entities.dto.UserRequest;
import com.example.digitounico.services.UsersCrudService;
import com.example.digitounico.services.UsersCryptoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UsersController implements UsersApi {

    private final UsersCrudService usersCrudService;
    private final UsersCryptoService usersCryptoService;

    @PostMapping("/users")
    public ResponseEntity create(@RequestBody UserRequest user) {
        var createdUser = usersCrudService.create(user.toAppUser());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
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
        var createdOrUpdatedUser = usersCrudService.updateOrCreate(user.toAppUser(uid));
        return ResponseEntity.status(HttpStatus.OK).body(createdOrUpdatedUser);
    }

    @DeleteMapping("/users/{uid}")
    public ResponseEntity deleteByUid(@PathVariable UUID uid) {
        usersCrudService.delete(uid);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/users/{uid}/encrypt")
    public ResponseEntity encrypt(@PathVariable UUID uid, @RequestBody KeyRequest key) {
        var user = usersCryptoService.encrypt(uid, key.getBase64EncodedKey());
        return ResponseEntity.ok(user);
    }

    @PostMapping("/users/{uid}/decrypt")
    public ResponseEntity decrypt(@PathVariable UUID uid, @RequestBody KeyRequest key) {
        var user = usersCryptoService.decrypt(uid, key.getBase64EncodedKey());
        return ResponseEntity.ok(user);
    }
}
