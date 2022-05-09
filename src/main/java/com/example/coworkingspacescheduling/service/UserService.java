package com.example.coworkingspacescheduling.service;

import com.example.coworkingspacescheduling.model.AppUser;

import java.util.List;

public interface UserService {
    AppUser saveUser(AppUser user);
    AppUser getUser(String name);
    List<AppUser> getAllUsers();
}
