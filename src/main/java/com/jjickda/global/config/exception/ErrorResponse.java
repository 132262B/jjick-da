package com.jjickda.global.config.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponse {


    private final LocalDateTime timestamp = LocalDateTime.now();
    private int status;
    private String message;


    public static ErrorResponse of(ErrorCode code) {
        return new ErrorResponse(code.getStatus().value(), code.getMessage(), null);
    }

    public static ErrorResponse of(ErrorCode code, String message) {
        return new ErrorResponse(code.getStatus().value(), message, null);
    }


    public static ErrorResponse of(ErrorCode code, BindingResult bindingResult) {
        return new ErrorResponse(code.getStatus().value(), code.getMessage(), FieldError.of(bindingResult));
    }

    private ErrorResponse(int status, String message, List<FieldError> errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<FieldError> errors;

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class FieldError {
        private String field;
        private String value;
        private String reason;

        private FieldError(String field, String value, String reason) {
            this.field = field;
            this.value = value;
            this.reason = reason;
        }

        private static List<FieldError> of(final BindingResult bindingResult) {
            return bindingResult.getFieldErrors().stream()
                    .map(error -> new FieldError(
                            error.getField(),
                            error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
                            error.getDefaultMessage()))
                    .collect(Collectors.toList());
        }
    }
}