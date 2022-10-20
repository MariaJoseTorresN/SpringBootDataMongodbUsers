package com.edu.eci.ieti.controller;

import com.edu.eci.ieti.dto.UserDto;
import com.edu.eci.ieti.entities.User;
import com.edu.eci.ieti.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAll() {
        List<UserDto> users = new ArrayList<>();
        List<User> userList = userService.getAll();
        for (User user:userList) {
            users.add(user.toDto());
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping( "/{id}" )
    public ResponseEntity<UserDto> findById( @PathVariable String id ) {
        User user = userService.findById(id);
        if (user == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user.toDto());
    }

    @PostMapping
    public ResponseEntity<UserDto> create( @RequestBody UserDto userDto ) {
        User user = userService.create(userDto.toEntity());
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(user.toDto());
    }

    @PutMapping( "/{id}" )
    public ResponseEntity<UserDto> update( @RequestBody UserDto user, @PathVariable String id ) {
        User newUser = userService.update(user.toEntity(),id);
        if (newUser == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(newUser.toDto());
    }

    @DeleteMapping( "/{id}" )
    public ResponseEntity<Boolean> delete( @PathVariable String id ) {
        if (userService.findById(id) == null){
            return ResponseEntity.notFound().build();
        }
        userService.deleteById(id);
        return ResponseEntity.ok(true);
    }

    @GetMapping("/findName/{queryText}")
    public ResponseEntity<List<UserDto>> findUsersWithNameOrLastNameLike(@PathVariable String queryText) {
        List<UserDto> usersDto = new ArrayList<>();
        userService.findUsersWithNameOrLastNameLike(queryText).forEach((user) -> usersDto.add(user.toDto()));
        return ResponseEntity.ok(usersDto);
    }

    @GetMapping("/findAfter/{startDate}")
    public ResponseEntity<List<UserDto>>findUsersCreatedAfter(@PathVariable String startDate) throws ParseException {
        List<UserDto> usersDto = new ArrayList<>();
        userService.findUsersCreatedAfter(new SimpleDateFormat("dd-MM-yyyy").parse(startDate)).forEach((user) -> usersDto.add(user.toDto()));
        return ResponseEntity.ok(usersDto);
    }
}
