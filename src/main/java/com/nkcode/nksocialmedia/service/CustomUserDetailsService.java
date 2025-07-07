package com.nkcode.nksocialmedia.service;

import com.nkcode.nksocialmedia.dao.entity.UserReg;
import com.nkcode.nksocialmedia.dao.repository.UserRegRepository;
import com.nkcode.nksocialmedia.exception.custom.UserNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomUserDetailsService implements UserDetailsService {

    UserRegRepository userRegRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<UserReg> userReg = userRegRepository.findByUsername(username);
        userReg.orElseThrow(() -> new UserNotFoundException("username not found int the database"));
        return new CustomUserDetails(userReg.get());
    }
}
