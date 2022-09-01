package com.edu.eci.ieti.controller;

import com.edu.eci.ieti.dto.UserDto;
import com.edu.eci.ieti.entities.User;
import com.edu.eci.ieti.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAll() {
        List<UserDto> users = new ArrayList<>();
        List<User> userList = userService.getAll();
        ModelMapper modelMapper = new ModelMapper();
        for (User user:userList) {
            users.add(modelMapper.map(user, UserDto.class));
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping( "/{id}" )
    public ResponseEntity<UserDto> findById( @PathVariable String id ) {
        ModelMapper modelMapper = new ModelMapper();
        User user = userService.findById(id);
        if (user == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(modelMapper.map(user, UserDto.class));
    }

    @PostMapping
    public ResponseEntity<UserDto> create( @RequestBody UserDto userDto ) {
        ModelMapper modelMapper = new ModelMapper();
        User user = userService.create(modelMapper.map(userDto,User.class));
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(modelMapper.map(user,UserDto.class));
    }

    @PutMapping( "/{id}" )
    public ResponseEntity<UserDto> update( @RequestBody UserDto user, @PathVariable String id ) {
        ModelMapper modelMapper = new ModelMapper();
        User newUser = userService.update(modelMapper.map(user,User.class),id);
        if (newUser == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @DeleteMapping( "/{id}" )
    public ResponseEntity<Boolean> delete( @PathVariable String id ) {
        if (userService.findById(id) == null){
            return ResponseEntity.notFound().build();
        }
        userService.deleteById(id);
        return ResponseEntity.ok(true);
    }
}
