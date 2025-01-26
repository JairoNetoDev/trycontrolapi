package com.trycontrol.user.controller;

import com.trycontrol.user.dto.UserDTO;
import com.trycontrol.user.dto.UserDetailsDTO;
import com.trycontrol.user.infra.model.User;
import com.trycontrol.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class UserController {
    @Autowired
    public UserService userService;

    @GetMapping("/user")
    public ResponseEntity<UserDetailsDTO> getUserByLogin(@RequestBody UserDTO userDetails) {
        return userService.getUserByLogin(userDetails);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/user")
    public ResponseEntity<UserDetailsDTO> createUser(@RequestBody User userDetails) {
        return userService.createUser(userDetails);
    }

    @PutMapping("/user")
    public ResponseEntity<UserDetailsDTO> updateUser(@RequestBody UserDTO userDetails){
        return userService.updateUserDetails(userDetails);
    }

    @DeleteMapping("/user")
    public ResponseEntity<String> deleteUserByEmail(@RequestBody UserDTO userDetails){
        return userService.deleteUser(userDetails);
    }

}
