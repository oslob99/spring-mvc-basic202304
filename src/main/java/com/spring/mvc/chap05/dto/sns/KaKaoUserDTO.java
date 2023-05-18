package com.spring.mvc.chap05.dto.sns;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Getter@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Builder
public class KaKaoUserDTO {

    private String id;

    @JsonProperty("connected_at")
    private LocalDateTime connectedAt;

    @JsonProperty("kakao_account")
    private KaKaoAccount kaKaoAccount;

    @Setter @Getter @ToString
    public static class KaKaoAccount {

        private String email;
        private Profile profile;

        @Getter @Setter @ToString
        public static class Profile {

            private String nickname;
            @JsonProperty("profile_image_url")
            private String profileImageUrl;

        }
    }
}
