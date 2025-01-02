package com.elouissi.hunters_league.web.rest.controller;

import com.elouissi.hunters_league.domain.AppUser;
import com.elouissi.hunters_league.service.DTO.RankDTO;
import com.elouissi.hunters_league.service.DTO.UserResponse;
import com.elouissi.hunters_league.service.UserService;
import com.elouissi.hunters_league.web.rest.VM.UserVM;
import com.elouissi.hunters_league.web.rest.VM.mapper.UserMapper;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper){
        this.userService = userService;
        this.userMapper = userMapper;

    }
//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<Page<UserResponse>> getAllUsers(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("nationality").ascending());
        Page<AppUser> users = userService.getUsersPaginated(pageable);

        Page<UserResponse> usersDTO = userMapper.toUserResponsePage(users);

        return ResponseEntity.ok(usersDTO);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/update/{username}")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserVM userVM, @PathVariable String username) {
        Optional<AppUser> userOptional = userService.getUserByUsername(username);

        if (userOptional.isPresent()) {
            AppUser existingAppUser = userOptional.get();
            AppUser appUserToUpdate = userMapper.VmToEntity(userVM);
            appUserToUpdate.setId(existingAppUser.getId());

            AppUser updatedAppUser = userService.updateUser(appUserToUpdate);
            return ResponseEntity.ok("Utilisateur mis à jour avec succès.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("L'utilisateur n'existe pas.");
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/delete/{username}")
    public ResponseEntity<?> remove(@PathVariable String username){
        Optional<AppUser> userOptional = userService.getUserByUsername(username);
        if (userOptional.isPresent()){
            AppUser existingAppUser = userOptional.get();
            AppUser deletedAppUser =   userService.remove(existingAppUser);
            return ResponseEntity.ok("Utilisateur a rejeté avec succès.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("L'utilisateur n'existe pas.");
        }

    }
    @GetMapping("/search")
    public ResponseEntity<List<AppUser>> searchUsers(@RequestParam(required = false) String username, @RequestParam(required = false) String email) {
        List<AppUser> appUsers = userService.findByCriteria(username, email);
        return ResponseEntity.ok(appUsers);
    }
    @GetMapping("/rank")
    public ResponseEntity<List<RankDTO>> userRanks(){
        List<RankDTO> rankDTOList = userService.getRankOfUserByScore();
        return ResponseEntity.ok(rankDTOList);
    }



}
