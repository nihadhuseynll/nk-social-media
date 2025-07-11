package com.nkcode.nksocialmedia.service.concrete;

import com.nkcode.nksocialmedia.dao.entity.User;
import com.nkcode.nksocialmedia.dao.repository.UserRepository;
import com.nkcode.nksocialmedia.dto.request.LoginRequestDto;
import com.nkcode.nksocialmedia.dto.request.RegistrationRequestDto;
import com.nkcode.nksocialmedia.mapper.UserMapper;
import com.nkcode.nksocialmedia.security.Jwt.JwtService;
import com.nkcode.nksocialmedia.service.abstraction.UserService;
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
public class UserServiceImpl implements UserService {

    JwtService jwtService;
    UserMapper userMapper;
    UserRepository userRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;
    AuthenticationManager authenticationManager;

    @Override
    public User register(RegistrationRequestDto registrationRequestDto) {
        registrationRequestDto.setPassword(bCryptPasswordEncoder.encode(registrationRequestDto.getPassword()));
        return userRepository.save(userMapper.toEntity(registrationRequestDto));
    }

    @Override
    public String login(LoginRequestDto loginRequestDto) {
        Authentication authenticate = authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken(loginRequestDto.getUserName(), loginRequestDto.getPassword()));

        if (authenticate.isAuthenticated()) {
            return jwtService.generateToken(loginRequestDto);
        }
        return "failure";
    }

}
