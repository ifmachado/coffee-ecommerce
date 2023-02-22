package com.version1.ecommerce.controller;

import com.version1.ecommerce.dto.ResponseDto;
import com.version1.ecommerce.dto.user.SignInDto;
import com.version1.ecommerce.dto.user.SignInResponseDto;
import com.version1.ecommerce.dto.user.SignUpDto;
import com.version1.ecommerce.exceptions.AuthenticationFailException;
import com.version1.ecommerce.model.User;
import com.version1.ecommerce.repository.UserRepository;
import com.version1.ecommerce.service.AuthenticationService;
import com.version1.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("user")
@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationService authenticationService;

    @GetMapping("/token")
    public List<User> findUserByToken(@RequestParam("token") String token) throws AuthenticationFailException {
        authenticationService.authenticate(token);
        return userRepository.findAll();
    }

    @GetMapping("/all")
    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

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
