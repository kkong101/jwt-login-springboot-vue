package main.demo.domain.basement.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TokenType {
    ACCESS_TOKEN(1000L * 10 ,"user_info", "access"),
    REFRESH_TOKEN(1000L * 20 ,"user_info", "refresh"),
    ADDITION_REFRESH_TOKEN(1000L * 60 * 60 * 24 * 30 * 2,"request","refresh");

    private final Long expireTime;
    private final String subject;
    private final String tokenType;
}
