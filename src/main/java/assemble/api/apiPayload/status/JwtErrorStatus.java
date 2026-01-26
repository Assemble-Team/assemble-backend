package assemble.api.apiPayload.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum JwtErrorStatus implements ErrorReason {

    NO_AUTHORITY(HttpStatus.BAD_REQUEST, "JWT4001", "권한정보가 존재하지 않습니다"),
    WRONG_TYPE_SIGNATURE(HttpStatus.BAD_REQUEST, "JWT4002", "잘못된 JWT 서명입니다."),
    TOKEN_EXPIRED (HttpStatus.BAD_REQUEST, "JWT4003", "만료된 JWT 토큰입니다."),
    WRONG_TYPE_TOKEN(HttpStatus.BAD_REQUEST, "JWT4004", "지원되지 않는 JWT 토큰입니다."),
    NOT_VALID_TOKEN(HttpStatus.BAD_REQUEST, "JWT4005", "JWT 토큰이 잘못되었습니다."),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
