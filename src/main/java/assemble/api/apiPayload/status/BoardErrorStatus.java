package assemble.api.apiPayload.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum BoardErrorStatus implements ErrorReason {

    NOT_EXIST_BOARD(HttpStatus.BAD_REQUEST, "BOARD4001", "존재하지 않는 게시판입니다"),

    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
