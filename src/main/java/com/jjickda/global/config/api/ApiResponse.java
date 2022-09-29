package com.jjickda.global.config.api;

import groovyjarjarantlr4.v4.runtime.misc.Nullable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;


@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResponse<T> {

    private final LocalDateTime timestamp = LocalDateTime.now();

    private final int status = HttpStatus.OK.value();
    private T data;

    public ApiResponse(T data) {
        this.data = data;
    }
}
