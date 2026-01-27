package assemble.api.apiPayload.status;

import ch.qos.logback.core.status.ErrorStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ClubErrorStatus implements ErrorReason {

    INVALID_DIFFICULTY_LEVEL(HttpStatus.BAD_REQUEST, "CLUB4001", "유효하지 않은 활동 난이도입니다."),
    INVALID_INTEREST_CATEGORY(HttpStatus.BAD_REQUEST, "CLUB4002", "유효하지 않은 카테고리입니다."),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
