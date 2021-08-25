package org.ksga.springboot.jpahomework.service.user;

import org.ksga.springboot.jpahomework.dto.model.user.UserDto;
import org.ksga.springboot.jpahomework.dto.response.JwtResponse;

public interface UserService {
    JwtResponse loginUser(UserDto userDto);
    UserDto registerUser(UserDto userDto);
    UserDto findUserByEmail(String email);
    boolean existsByEmail(String email);
}
