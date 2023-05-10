package com.spring.mvc.chap05.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@ToString @EqualsAndHashCode
public class LoginRequestDTO {
    private String account;
    private String password;
    private boolean autoLogin;
}
