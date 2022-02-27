package main.demo.domain.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import main.demo.domain.basement.embed.Password;

import java.time.LocalDateTime;

public class Response_User {

    @Getter
    public static class User {
        private Long idx;
        private String id;
        private Password password;
        @Setter
        private String accessToken;
        private String refreshToken;
        private LocalDateTime created;
        private LocalDateTime updated;

        @Builder
        public User(Long idx, String id, Password password, String accessToken, String refreshToken, LocalDateTime created, LocalDateTime updated) {
            this.idx = idx;
            this.id = id;
            this.password = password;
            this.accessToken = accessToken;
            this.refreshToken = refreshToken;
            this.created = created;
            this.updated = updated;
        }
    }
}
