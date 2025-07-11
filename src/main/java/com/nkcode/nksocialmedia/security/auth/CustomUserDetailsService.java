package com.nkcode.nksocialmedia.security.auth;

import com.nkcode.nksocialmedia.dao.entity.User;
import com.nkcode.nksocialmedia.dao.repository.UserRepository;
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

    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<User> user = userRepository.findByUserName(username);
        user.orElseThrow(() -> new UserNotFoundException("username not found int the database"));
        return new CustomUserDetails(user.get());
    }
}
