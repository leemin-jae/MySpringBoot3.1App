package com.ktds.myspringboot.controller;

import com.ktds.myspringboot.dto.UserReqDto;
import com.ktds.myspringboot.dto.UserResDto;
import com.ktds.myspringboot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserRestController {
    final private UserService userService;

    @PostMapping
    public UserResDto saveUser(@RequestBody UserReqDto userReqDto){
        return userService.saveUser(userReqDto);
    }

    @GetMapping("/{myId}")
    public UserResDto getUserById(@PathVariable("myId") Long id){
        return userService.getUserById(id);
    }

    @GetMapping
    public List<UserResDto> getUser(){
        return userService.getUsers();
    }

    @PatchMapping("/{email}")
    public UserResDto upateUser(@PathVariable String email,
                                @RequestBody UserReqDto userReqDto){
        return userService.updateUser(email, userReqDto);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok(id + " User 삭제 완료.");
    }

}
