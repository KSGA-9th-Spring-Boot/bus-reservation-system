package org.ksga.springboot.jpahomework.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
public class RegisterRequest {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String agencyDetail;
    private String mobilePhone;
    private Set<String> roles = new HashSet<>(List.of("PASSENGER"));

    public String getFullName() {
        return firstName + lastName;
    }
}
