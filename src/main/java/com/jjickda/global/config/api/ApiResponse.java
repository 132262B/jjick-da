package com.jjickda.global.config.api;

import groovyjarjarantlr4.v4.runtime.misc.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class ApiResponse<T> {

    @Nullable
    private final T body;
}
