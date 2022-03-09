package main.demo.mvc.repository;

import com.querydsl.jpa.impl.JPAUpdateClause;
import main.demo.domain.dto.response.Response_User;
import main.demo.domain.entity.user.B_User;
import main.demo.domain.entity.user.QB_User;
import main.demo.mvc.repository.basement.BaseRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public class Repository_User extends BaseRepository {

    QB_User q_user = QB_User.b_User;

    public Optional<Response_User.User> getUser(String id) {
        B_User queryResult = query.selectFrom(q_user).where(q_user.id.eq(id)).fetchOne();

        Response_User.User test = mapper.map(queryResult, Response_User.User.class);

        return (queryResult != null) ? Optional.of(mapper.map(queryResult, Response_User.User.class)) : Optional.empty();
    }

    @Transactional
    public long updateRefreshToken(String id,String refreshToken) {
        JPAUpdateClause update = query.update(q_user).where(q_user.id.eq(id));

        update.set(q_user.refreshToken,refreshToken);

        return update.execute();
    }

}

//    CREATE TABLE `test_user` (
//        `user_idx` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '이용자 테이블 인덱스',
//        `create_time` datetime NOT NULL COMMENT '생성일',
//        `update_time` datetime NOT NULL COMMENT '업데이트 일자',
//        `user_account_token_refresh` longtext COMMENT '리프레시 토큰 값',
//        `user_account_salt` char(12) NOT NULL COMMENT '패스워드 랜덤값',
//        `user_account_id` varchar(30) NOT NULL COMMENT '이용자 계정 아이디',
//        `user_account_password` char(64) DEFAULT NULL COMMENT '이용자 계정 암호',
//        PRIMARY KEY (`user_idx`)
//        ) ENGINE=InnoDB CHARSET=utf8 COMMENT='테스트 유저 테이블';


