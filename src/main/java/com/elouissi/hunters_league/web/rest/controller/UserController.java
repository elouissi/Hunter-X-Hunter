package com.elouissi.hunters_league.web.rest.controller;

import com.elouissi.hunters_league.domain.User;
import com.elouissi.hunters_league.service.UserService;
import com.elouissi.hunters_league.web.rest.VM.UserVM;
import com.elouissi.hunters_league.web.rest.VM.mapper.RegisterMapper;
import com.elouissi.hunters_league.web.rest.VM.mapper.UserMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper){
        this.userService = userService;
        this.userMapper = userMapper;

    }
    @PostMapping("/update/{username}")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserVM userVM, @PathVariable String username) {
        Optional<User> userOptional = userService.getUserByUsername(username);

        if (userOptional.isPresent()) {
            User existingUser = userOptional.get();
            User userToUpdate = userMapper.VmToEntity(userVM);
            userToUpdate.setId(existingUser.getId());

            User updatedUser = userService.updateUser(userToUpdate);
            return ResponseEntity.ok("Utilisateur mis à jour avec succès.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("L'utilisateur n'existe pas.");
        }
    }
    @GetMapping("/delete/{username}")
    public ResponseEntity<?> remove(@PathVariable String username){
        Optional<User> userOptional = userService.getUserByUsername(username);
        if (userOptional.isPresent()){
            User existingUser = userOptional.get();
            User deletedUser =   userService.remove(existingUser);
            return ResponseEntity.ok("Utilisateur a rejeté avec succès.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("L'utilisateur n'existe pas.");
        }

    }




}
