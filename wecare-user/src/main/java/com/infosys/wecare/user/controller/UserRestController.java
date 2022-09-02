package com.infosys.wecare.user.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.wecare.user.dtos.LoginDto;
import com.infosys.wecare.user.dtos.UserDto;
import com.infosys.wecare.user.exceptions.WeCareException;
import com.infosys.wecare.user.service.UserService;

@RestController
@RequestMapping("users")
@CrossOrigin
public class UserRestController {
    @Autowired
    UserService userService;

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody @Valid UserDto userDto) throws WeCareException {
        String createdId = userService.createUser(userDto);
        return new ResponseEntity<>(createdId, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> loginUser(@RequestBody LoginDto loginDto) throws WeCareException {

        Boolean verified = userService.loginUser(loginDto);

        return new ResponseEntity<>(verified, HttpStatus.OK);

    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserProfile(@PathVariable String userId) throws WeCareException {
        UserDto userProfile = userService.getUser(userId);
        return new ResponseEntity<>(userProfile, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> showAllUsers(){
        List<UserDto> userDtoList = userService.getUserList();
        return new ResponseEntity<>(userDtoList, HttpStatus.OK);
    }

}
