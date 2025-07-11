package com.nkcode.nksocialmedia.service.abstraction;

import com.nkcode.nksocialmedia.dao.entity.User;
import com.nkcode.nksocialmedia.dto.request.LoginRequestDto;
import com.nkcode.nksocialmedia.dto.request.RegistrationRequestDto;

public interface UserService {
    User register(RegistrationRequestDto registrationRequestDto);

    String login(LoginRequestDto loginRequestDto);
}
