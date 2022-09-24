package com.jjickda.global.config.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponse {

    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<FieldError> errors;

    private ErrorResponse(String message, List<FieldError> errors) {
        this.message = message;
        this.errors = errors;
    }

    public static ErrorResponse of(ErrorCode code) {
        return new ErrorResponse(code.getMessage(), null);
    }

    public static ErrorResponse of(ErrorCode code, String message) {
        return new ErrorResponse(message, null);
    }

    public static ErrorResponse of(ErrorCode code, BindingResult bindingResult) {
        return new ErrorResponse(code.getMessage(), FieldError.of(bindingResult));
    }

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