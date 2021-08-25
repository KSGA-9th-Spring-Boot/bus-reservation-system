package org.ksga.springboot.jpahomework.service.user;

import lombok.RequiredArgsConstructor;
import org.ksga.springboot.jpahomework.dto.mapper.UserMapper;
import org.ksga.springboot.jpahomework.dto.model.user.RoleDto;
import org.ksga.springboot.jpahomework.dto.model.user.UserDto;
import org.ksga.springboot.jpahomework.dto.response.JwtResponse;
import org.ksga.springboot.jpahomework.model.user.Role;
import org.ksga.springboot.jpahomework.model.user.User;
import org.ksga.springboot.jpahomework.model.user.UserRoles;
import org.ksga.springboot.jpahomework.repository.user.RoleRepository;
import org.ksga.springboot.jpahomework.repository.user.UserRepository;
import org.ksga.springboot.jpahomework.security.jwt.JwtUtils;
import org.ksga.springboot.jpahomework.security.service.UserDetailsImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @Override
    public JwtResponse loginUser(UserDto userDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Set<String> roles = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());
        return new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), roles);
    }

    @Override
    public UserDto registerUser(UserDto userDto) {
        Set<String> strRoles = userDto.getRoles().stream().map(RoleDto::getRole).collect(Collectors.toSet());
        Set<Role> roles = new HashSet<>();

        if (strRoles.isEmpty()) {
            Optional<Role> userRole = roleRepository.findByRole(UserRoles.ROLE_PASSENGER);
            if (userRole.isEmpty()) {
                Role rolePassenger = new Role();
                rolePassenger.setRole(UserRoles.ROLE_PASSENGER);
                roleRepository.save(rolePassenger);
                roles.add(rolePassenger);
            } else {
                roles.add(userRole.get());
            }
        } else {
            strRoles.forEach(role -> {
                if ("admin".equalsIgnoreCase(role)) {
                    Role adminRole = roleRepository.findByRole(UserRoles.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error: Role Admin is not found."));
                    roles.add(adminRole);
                } else {
                    Role userRole = roleRepository.findByRole(UserRoles.ROLE_PASSENGER)
                            .orElseThrow(() -> new RuntimeException("Error: Role Passenger is not found."));
                    roles.add(userRole);
                }
            });
        }

        User user = userMapper.userDtoToUser(userDto);
        user.setRoles(roles);
        userRepository.save(user);
        return userMapper.userToUserDto(user);
    }

    @Transactional
    @Override
    public UserDto findUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return userMapper.userToUserDto(user.get());
        }
        throw new RuntimeException("Email Not Found");
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

}
