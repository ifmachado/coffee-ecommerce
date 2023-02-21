package com.version1.ecommerce.service;


import com.version1.ecommerce.model.AuthenticationToken;
import com.version1.ecommerce.model.User;
import com.version1.ecommerce.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
@Autowired
    TokenRepository tokenRepository;

    public void saveConfirmationToken(AuthenticationToken authenticationToken) {
tokenRepository.save(authenticationToken);

    }

    public AuthenticationToken getToken(User user) {
        return tokenRepository.findByUser(user);
    }
}
