package app.jjickda.global.config.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    // Test
    TEST_ERROR(HttpStatus.BAD_REQUEST, "테스트 에러 코드입니다."),

    // SIGN UP
    SIGN_UP_EMAIL_DUPLICATE(HttpStatus.BAD_REQUEST, "이미 가입된 이메일 주소입니다. 다른 이메일을 입력하여 주세요."),

    // Common
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "입력 값이 잘못 되었습니다."),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "메소드를 사용할 수 없습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버에 문제가 발생하였습니다. 관리자에게 문의 부탁드립니다."),
    INVALID_TYPE_VALUE(HttpStatus.BAD_REQUEST, "입력 값 타입이 잘못되었습니다."),
    HANDLE_ACCESS_DENIED(HttpStatus.FORBIDDEN, "접근 권한이 없습니다.");


    private final HttpStatus status;
    private final String message;

    ErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
