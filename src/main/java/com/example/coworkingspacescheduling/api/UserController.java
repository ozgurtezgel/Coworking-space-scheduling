package com.example.coworkingspacescheduling.api;

import com.example.coworkingspacescheduling.model.AppUser;
import com.example.coworkingspacescheduling.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api")
public class UserController {

    private final UserService userService;

//    @GetMapping("/users")
//    public ResponseEntity<List<AppUser>> getAllUsers() {
//        return ResponseEntity.ok().body(userService.getAllUsers());
//    }

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<AppUser> getAllUsers() {
        return userService.getAllUsers();
    }


    @GetMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public AppUser getUser(@RequestParam String name) {
        return userService.getUser(name);
    }

//    @PostMapping("/user/save")
//    public ResponseEntity<AppUser> saveUser(@RequestBody AppUser appUser) {
//        return ResponseEntity.created(null).body(userService.saveUser(appUser));
//    }

    @PostMapping("/user/save")
    @ResponseStatus(HttpStatus.CREATED)
    public AppUser saveUser(@RequestBody AppUser appUser) {
        return userService.saveUser(appUser);
    }
}
