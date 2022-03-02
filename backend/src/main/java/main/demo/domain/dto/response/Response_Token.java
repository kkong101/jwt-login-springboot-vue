package main.demo.domain.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import main.demo.domain.dto.response.basement.BaseResponse;
import org.springframework.http.HttpStatus;

public class Response_Token {

    @Getter
    public static class Token {

        @Setter
        private String token;

        @Builder
        public Token(String token) {
            this.token = token;
        }
    }
}
