package com.nkcode.nksocialmedia.service;

import com.nkcode.nksocialmedia.dao.entity.User;
import com.nkcode.nksocialmedia.dao.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {

    UserRepository userRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;
    AuthenticationManager authenticationManager;
    JwtService jwtService;

    public User register(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public String verify(User user) {
        Authentication authenticate = authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));

        if (authenticate.isAuthenticated()) {
            return jwtService.generateToken(user);
        }
        return "failure";
    }
}
