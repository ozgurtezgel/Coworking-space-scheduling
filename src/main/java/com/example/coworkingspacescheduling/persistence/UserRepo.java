package com.example.coworkingspacescheduling.persistence;

import com.example.coworkingspacescheduling.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<AppUser, Long> {
    AppUser findByFullName(String name);
}
