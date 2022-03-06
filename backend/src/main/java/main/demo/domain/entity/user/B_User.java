package main.demo.domain.entity.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import main.demo.domain.basement.BaseEntity;
import main.demo.domain.basement.embed.Password;

import javax.persistence.*;

@Entity
@Table(name="test_user")
@Getter
@NoArgsConstructor
@AttributeOverride(name="idx", column = @Column(name = "user_idx", nullable = false, columnDefinition = "BIGINT", updatable = false) )
public class B_User extends BaseEntity {

    @Column(name = "user_account_id", nullable = false, columnDefinition = "VARCHAR(30)")
    private String id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "password", column =  @Column(name = "user_account_password", columnDefinition = "CHAR(64)", nullable = false)),
            @AttributeOverride(name = "salt", column =  @Column(name = "user_account_salt", columnDefinition = "CHAR(12)", nullable = false))
    })
    private Password password;

    @Column(name = "user_account_token_push", columnDefinition = "LONGTEXT")
    private String accessToken;

    @Column(name = "user_account_token_refresh", columnDefinition = "LONGTEXT")
    private String refreshToken;

    @Builder
    public B_User(String id, String password, String accessToken, String refreshToken) {
        this.id = id;
        this.password = new Password(password);
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
