package app.jjickda.global.config.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

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
