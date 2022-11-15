package app.jjickda.global.config.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final String ERROR_LOG_MESSAGE = "[ERROR] {} : {}";

    @ExceptionHandler(PagePermissionException.class)
    public String handleBusinessException(PagePermissionException e) {
        log.warn(ERROR_LOG_MESSAGE, e.getClass().getSimpleName(), e.getMessage(), e);
        return "error/403";
    }
}
