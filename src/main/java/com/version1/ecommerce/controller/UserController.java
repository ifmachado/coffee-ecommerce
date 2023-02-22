package com.version1.ecommerce.controller;

import com.version1.ecommerce.dto.ResponseDto;
import com.version1.ecommerce.dto.user.SignInDto;
import com.version1.ecommerce.dto.user.SignInResponseDto;
import com.version1.ecommerce.dto.user.SignUpDto;
import com.version1.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("user")
@RestController
public class UserController {


    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public ResponseDto signup(@RequestBody SignUpDto signupdto) {
        return userService.signUp(signupdto);
    }

    @PostMapping("/signin")
    public SignInResponseDto signIn(@RequestBody SignInDto signInDto) {
        return userService.signIn(signInDto);
    }

//
//    @RequestMapping("user")
//    @RestController
//    public static class UserController {
//
//        @Autowired
//        UserService userService;
//
//        @PostMapping("/signup")
//        public ResponseDto signup(@RequestBody SignUpDto signupdto) {
//            return userService.signUp(signupdto);
//        }
//
//        @PostMapping("/signin")
//        public SignInResponseDto signIn(@RequestBody SignInDto signInDto) {
//            return userService.signIn(signInDto);
//        }
//
//
//
//    }
}
