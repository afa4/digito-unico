package com.example.digitounico.utils;

import com.example.digitounico.entities.AppUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class DigitoUnicoApplicationUtil {

    private Random random = new Random();

    public static AppUser mockAppUser() {
        var id = getRandomLongOnRange(1, 100);
        return AppUser.builder()
                .id(getRandomLongOnRange(1, 100))
                .uid(UUID.randomUUID())
                .name("Mockson" + id)
                .email("mockson" + id + "@email.com")
                .build();
    }

    public static List<AppUser> mockAppUserList(int size) {
        var list = new ArrayList<AppUser>();
        for (int i = 0; i < size; i++) {
            list.add(mockAppUser());
        }
        return list;
    }


    private static long getRandomLongOnRange(int min, int max) {
        return (long) ((Math.random() * (max - min)) + min);
    }
}
