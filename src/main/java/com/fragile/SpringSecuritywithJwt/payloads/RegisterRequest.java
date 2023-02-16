package com.fragile.SpringSecuritywithJwt.payloads;

import jakarta.persistence.Column;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String name;

    private String email;

    private String password;

    private String about;
}


