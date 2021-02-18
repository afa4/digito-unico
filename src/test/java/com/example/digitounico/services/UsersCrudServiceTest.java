package com.example.digitounico.services;

import com.example.digitounico.entities.AppUser;
import com.example.digitounico.entities.dto.UserRequest;
import com.example.digitounico.exceptions.ApplicationException;
import com.example.digitounico.repositories.AppUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.UUID;

import static com.example.digitounico.utils.DigitoUnicoApplicationUtil.mockAppUser;
import static com.example.digitounico.utils.DigitoUnicoApplicationUtil.mockAppUserList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsersCrudServiceTest {
    @Mock
    private AppUserRepository appUserRepository;

    @InjectMocks
    private UsersCrudService usersCrudService;

    @Test
    public void shouldThrowApplicationException_whenTriesToCreateAppUserWithAnAlreadyRegisteredEmail() {
        when(appUserRepository.findByEmail(any())).thenReturn(mockAppUser("Alice", "bob@Email.com"));

        try {
            usersCrudService.create(mockAppUser("Bob", "bob@email.com"));
        } catch (ApplicationException ex) {
            Assertions.assertEquals("Email já cadastrado.", ex.getType().getMessage());
            Assertions.assertEquals(HttpStatus.CONFLICT, ex.getType().getReturnStatus());
        }
    }

    @Test
    public void shouldCreateAppUser_whenDoesntFindAnAlreadyRegisteredEmail() {
        when(appUserRepository.findByEmail(any())).thenReturn(null);

        usersCrudService.create(mockAppUser("Bob", "bob@email.com"));

        verify(appUserRepository).create(any());
    }

    @Test
    public void shouldThrowApplicationException_whenDoesntFindAnUserByUid() {
        when(appUserRepository.findByUid(any())).thenReturn(null);

        try {
            usersCrudService.findByUid(UUID.randomUUID());
        } catch (ApplicationException ex) {
            Assertions.assertEquals("Entidade não encontrada.", ex.getType().getMessage());
            Assertions.assertEquals(HttpStatus.NOT_FOUND, ex.getType().getReturnStatus());
        }
    }

    @Test
    public void shouldCallRepositoryFindAllMethod_whenFetchesAllUsers() {
        when(appUserRepository.findAll()).thenReturn(mockAppUserList(3));

        usersCrudService.findAll();

        verify(appUserRepository).findAll();
    }

    @Test
    public void shouldCallRepositoryDeleteMethod_whenTriesToDeleteUser() {
        when(appUserRepository.findByUid(any())).thenReturn(mockAppUser());
        doNothing().when(appUserRepository).delete(any());

        var randomUUID = UUID.randomUUID();

        usersCrudService.delete(randomUUID);

        verify(appUserRepository).delete(randomUUID);
    }
}
