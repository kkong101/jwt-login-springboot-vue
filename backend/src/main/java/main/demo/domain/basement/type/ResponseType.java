package main.demo.domain.basement.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResponseType {

    SUCCESS(20000, "성공"),

    PARAMETER_WRONG(40001, "잘못된 파라미터"),

    KEY_WRONG(40003, "키 값 오류"),

    CHECK_REQUIRED_ITEM(40006, "필수 항목 체크"),

    TOKEN_INVALID(40101, "토큰 유효성"),

    TOKEN_TIME_OUT(40102, "토큰 시간 초과"),

    TOKEN_NOT_SUPPORT_FORM(40103, "지원하지 않는 토큰 양식"),

    TOKEN_NOT_SUPPORT_TYPE(40104, "지원하지 않는 토큰 타입"),

    TOKEN_NO_MATCH(40105, "토큰 불일치"),

    EXIST_USER(40106, "중복된 사용자"),

    NO_DUPLICATE_PASSWORD(40107, "일치하지 않은 비밀번호"),

    WRONG_ID(40108, "잘못된 접근입니다"),

    WRONG_PASSWORD(40109, "잘못된 접근입니다"),

    NO_ACTIVE_USER(40110, "잘못된 접근입니다"),

    UNAUTHORIZED_USER(40111, "권한이 없는 사용자"),

    DO_NOT_EXIST_ENTITY(41203, "존재하지 않는 자원"),

    AWS_EMAIL_ERROR(50015, "이메일 오류"),

    SEVER_ERROR(50000, "서버 오류");

    private final Integer code;

    private final String message;

}
