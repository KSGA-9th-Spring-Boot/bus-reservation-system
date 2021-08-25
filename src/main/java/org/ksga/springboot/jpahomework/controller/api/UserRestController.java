package org.ksga.springboot.jpahomework.controller.api;

import org.ksga.springboot.jpahomework.dto.model.bus.AgencyDto;
import org.ksga.springboot.jpahomework.dto.model.user.RoleDto;
import org.ksga.springboot.jpahomework.dto.model.user.UserDto;
import org.ksga.springboot.jpahomework.dto.request.LoginRequest;
import org.ksga.springboot.jpahomework.dto.request.RegisterRequest;
import org.ksga.springboot.jpahomework.dto.response.JwtResponse;
import org.ksga.springboot.jpahomework.dto.response.Response;
import org.ksga.springboot.jpahomework.service.bus.AgencyService;
import org.ksga.springboot.jpahomework.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
public class UserRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private AgencyService agencyService;

    @Autowired
    private PasswordEncoder encoder;

    @GetMapping("/{email}")
    public Response<UserDto> findUserByEmail(@PathVariable String email) {
        return Response.<UserDto>ok().setPayload(userService.findUserByEmail(email));
    }

    @PostMapping("/login")
    public Response<JwtResponse> loginUser(@RequestBody LoginRequest login) {
        try {
            UserDto userDto = new UserDto()
                    .setEmail(login.getEmail())
                    .setPassword(login.getPassword());
            JwtResponse jwtResponse = userService.loginUser(userDto);
            return Response.<JwtResponse>ok().setPayload(jwtResponse);
        } catch (Exception ex) {
            return Response
                    .<JwtResponse>exception()
                    .setErrors(ex.getMessage());
        }
    }

    @PostMapping("/register")
    public Response<UserDto> registerUser(@RequestBody RegisterRequest registerRequest) {
        try {
            if (userService.existsByEmail(registerRequest.getEmail())) {
                return Response
                        .<UserDto>badRequest()
                        .setErrors("Error: Email is already taken!");
            }
            UserDto userDto = new UserDto()
                    .setEmail(registerRequest.getEmail())
                    .setPassword(encoder.encode(registerRequest.getPassword()))
                    .setRoles(
                            registerRequest.getRoles().stream()
                                    .map(role -> new RoleDto().setRole(role))
                                    .collect(Collectors.toSet())
                    );
            UserDto user = userService.registerUser(userDto);
            AgencyDto agencyDto = new AgencyDto();
            agencyDto.setName(registerRequest.getFullName());
            agencyDto.setDetails(registerRequest.getAgencyDetail());
            agencyDto.setOwner(user);
            agencyService.insert(agencyDto);
            return Response
                    .<UserDto>ok()
                    .setPayload(user);
        } catch (RuntimeException ex) {
            return Response
                    .<UserDto>exception()
                    .setErrors(ex.getMessage());
        }
    }

}
