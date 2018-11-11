package com.angel.service.controller;

import com.angel.service.domain.User;
import com.angel.service.domain.Vehicle;
import com.angel.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findOne(@PathVariable("id") Long id) {
        User user = userService.findOne(id);
        if(user != null)
            return new ResponseEntity<>(user, HttpStatus.FOUND);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<User> add(@RequestBody User userRequest) {
        User user = userService.addUser(userRequest);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/{userId}/vehicle")
    public ResponseEntity<User> addVehicle(@PathVariable("userId") Long userId,
                                           @RequestBody Vehicle vehicle) {
        User user = userService.addVehicle(userId, vehicle);
        if(user != null)
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
