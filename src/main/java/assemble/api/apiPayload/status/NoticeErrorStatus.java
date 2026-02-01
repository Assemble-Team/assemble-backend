package assemble.api.apiPayload.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum NoticeErrorStatus implements ErrorReason {

    NOT_EXIST_NOTICE(HttpStatus.BAD_REQUEST, "Notice4001", "공지사항이 존재하지 않습니다"),

    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

}
