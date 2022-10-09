package app.jjickda.global.config.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final String message;
    private final ErrorCode errorCode;

}
