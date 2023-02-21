package com.version1.ecommerce.service;

import com.version1.ecommerce.dto.ResponseDto;
import com.version1.ecommerce.dto.user.SignInDto;
import com.version1.ecommerce.dto.user.SignInResponseDto;
import com.version1.ecommerce.dto.user.SignUpDto;
import com.version1.ecommerce.exceptions.AuthenticationFailException;
import com.version1.ecommerce.exceptions.CustomException;
import com.version1.ecommerce.model.AuthenticationToken;
import com.version1.ecommerce.model.User;
import com.version1.ecommerce.repository.UserRepository;
import jakarta.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationService authenticationService;

    @Transactional


 public ResponseDto signUp(SignUpDto signUpDto){

     //check if user is already present
     if (Objects.nonNull(userRepository.findByEmail(signUpDto.getEmail()))) {
         // If the email address has been registered then throw an exception.
         throw new CustomException("User already present");
     }

     //encrypt password
     String encryptedpassword = signUpDto.getPassword();
     try{
        encryptedpassword = hashPassword(signUpDto.getPassword());
     } catch (NoSuchAlgorithmException e) {
         e.printStackTrace();
     }




     //save the user

     User user = new User(signUpDto.getFirstName(), signUpDto.getFirstName(),
             signUpDto.getEmail(), encryptedpassword);
     userRepository.save(user);


    //create the token

     final AuthenticationToken authenticationToken = new AuthenticationToken(user);
authenticationService.saveConfirmationToken(authenticationToken);

     ResponseDto responseDto = new
             ResponseDto("success", "user created successfully");
     return responseDto;



 }




    String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String hash = DatatypeConverter
                .printHexBinary(digest).toUpperCase();
        return hash;
    }

    public SignInResponseDto signIn(SignInDto signInDto) {
        //find user by email
     User user = userRepository.findByEmail(signInDto.getEmail());
        if(!Objects.nonNull(user)){
         throw new AuthenticationFailException("user not present");
       }
        //hash the password
        try {
            // check if password is right
            if (!user.getPassword().equals(hashPassword(signInDto.getPassword()))){
                // passwords do not match
                throw  new AuthenticationFailException("wrong password");
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            //logger.error("hashing password failed {}", e.getMessage());
            //throw new CustomException(e.getMessage());
        }

        //compare the password in db
        //if password match

        AuthenticationToken token = authenticationService.getToken(user);

        if(Objects.isNull(token)){
            throw new CustomException("token is not present");
        }
        return new SignInResponseDto("success", token.getToken());


        //retrieve token
        //return response
    }
}
