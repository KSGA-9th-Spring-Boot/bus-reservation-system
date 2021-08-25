package org.ksga.springboot.jpahomework.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Accessors(chain = true)
public class JwtResponse {
    private String token;
    private String id;
    private String email;
    private Set<String> roles;
}
