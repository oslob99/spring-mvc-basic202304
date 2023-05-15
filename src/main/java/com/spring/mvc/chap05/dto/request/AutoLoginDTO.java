package com.spring.mvc.chap05.dto.request;

import lombok.*;

import javax.servlet.annotation.HttpConstraint;
import java.time.LocalDateTime;

@Setter @Getter
@ToString @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AutoLoginDTO {

    private String sessionId;
    private LocalDateTime limitTime;
    private String account;
}
