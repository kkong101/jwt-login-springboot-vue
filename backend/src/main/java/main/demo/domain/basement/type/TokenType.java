package main.demo.domain.basement.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TokenType {
    ACCESS_TOKEN(1000L * 60 * 5,"user_info", "access"),
    REFRESH_TOKEN(1000L * 60 * 60 * 24 * 15,"user_info", "refresh");

    private final Long expireTime;
    private final String subject;
    private final String tokenType;
}
