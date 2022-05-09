package com.example.coworkingspacescheduling.service;

import com.example.coworkingspacescheduling.model.AppUser;
import com.example.coworkingspacescheduling.persistence.UserRepo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepo userRepo;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private final PasswordEncoder passwordEncoder;

    @Override
    public AppUser saveUser(AppUser user) {
        LOGGER.info("Save the new user {} to the database", user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public AppUser getUser(String name) {
        LOGGER.info("Get the user with full name: {}", name);
        return userRepo.findByFullName(name);
    }

    @Override
    public List<AppUser> getAllUsers() {
        LOGGER.info("Get all users");
        return userRepo.findAll();
    }


    // UserDetailsService
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // userRepo.findByFullName(username) does not work properly
        // tried with Postmagithn (username: "kevin", password: "1234")
        // does not find the user
        // when called userRepo.findByFullName("kevin") it finds the user
        AppUser user = userRepo.findByFullName(username);
        if (user == null) {
            LOGGER.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            LOGGER.info("User found in the database: {}", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("User"));
        return new org.springframework.security.core.userdetails.User(user.getFullName(), user.getPassword(), authorities);
    }
}
