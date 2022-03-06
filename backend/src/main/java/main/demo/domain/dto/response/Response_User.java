package main.demo.domain.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.demo.domain.basement.convert.EncryptConvert;
import main.demo.domain.basement.embed.Password;
import main.demo.domain.dto.response.basement.BaseResponse;
import main.demo.domain.entity.user.B_User;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.modelmapper.PropertyMap;
import org.springframework.http.HttpStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

public class Response_User {

    @Getter@Setter
    @NoArgsConstructor
    public static class User {
        private Long idx;
        private String id;
        private Password password;
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


    public static class UserMap extends PropertyMap<B_User, Response_User.User> {
        @Override
        protected void configure() {
//            map(source.getIdx(),destination.getIdx());
//            map(source.getAccount_id(),destination.getId());
//            map(source.getPassword(),destination.getPassword());
//            map(source.getAccess_token(),destination.getAccessToken());
//            map(source.getRefresh_token(),destination.getRefreshToken());
//            map(source.getCreated_at(),destination.getCreated());
//            map(source.getUpdate_at(),destination.getUpdated());
//            map().setIdx(source.getIdx());
//            map().setId(source.getAccount_id());
//            map().setPassword(source.getPassword());
//            map().setAccessToken(source.getAccess_token());
//            map().setRefreshToken(source.getRefresh_token());
//            map().setCreated(source.getCreated_at());
//            map().setUpdated(source.getUpdate_at());
        }

    }
}
