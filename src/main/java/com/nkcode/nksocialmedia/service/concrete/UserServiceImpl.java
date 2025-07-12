package com.nkcode.nksocialmedia.service.concrete;

import com.nkcode.nksocialmedia.dao.entity.Role;
import com.nkcode.nksocialmedia.dao.entity.User;
import com.nkcode.nksocialmedia.dao.repository.RoleRepository;
import com.nkcode.nksocialmedia.dao.repository.UserRepository;
import com.nkcode.nksocialmedia.dto.request.LoginRequestDto;
import com.nkcode.nksocialmedia.dto.request.RegistrationRequestDto;
import com.nkcode.nksocialmedia.dto.request.RoleRequestDto;
import com.nkcode.nksocialmedia.exception.custom.RoleNotFoundException;
import com.nkcode.nksocialmedia.mapper.UserMapper;
import com.nkcode.nksocialmedia.security.Jwt.JwtService;
import com.nkcode.nksocialmedia.security.auth.CustomUserDetails;
import com.nkcode.nksocialmedia.service.abstraction.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {

    JwtService jwtService;
    UserMapper userMapper;
    UserRepository userRepository;
    RoleRepository roleRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;
    AuthenticationManager authenticationManager;

    @Override
    public User register(RegistrationRequestDto registrationRequestDto) {
        Set<RoleRequestDto> roles = registrationRequestDto.getRoles();

        Set<Role> foundedRoles = roles.stream()
                .map(role -> roleRepository.findByRoleName(role.getRoleName())
                        .orElseThrow(() ->
                                new RoleNotFoundException("Specified role is not recognized by the system.")))
                .collect(Collectors.toSet());

        registrationRequestDto.setPassword(bCryptPasswordEncoder
                .encode(registrationRequestDto.getPassword()));

        User user = userMapper.toEntity(registrationRequestDto);
        user.setRoles(foundedRoles);

        return userRepository.save(user);
    }

    @Override
    public String login(LoginRequestDto loginRequestDto) {
        Authentication authenticate = authenticationManager.
                authenticate(new UsernamePasswordAuthenticationToken
                        (loginRequestDto.getUserName(), loginRequestDto.getPassword()));

        CustomUserDetails customUserDetails = (CustomUserDetails) authenticate.getPrincipal();
        User user = customUserDetails.getUser(); // Əsl entity-yə çıxış

        return jwtService.generateToken(user);
    }

}
