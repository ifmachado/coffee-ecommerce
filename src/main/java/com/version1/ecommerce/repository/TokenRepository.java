package com.version1.ecommerce.repository;


import com.version1.ecommerce.model.AuthenticationToken;
import com.version1.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<AuthenticationToken, Integer> {
AuthenticationToken findByUser(User user);
}
