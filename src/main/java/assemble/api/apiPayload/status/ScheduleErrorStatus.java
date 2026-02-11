package assemble.api.apiPayload.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ScheduleErrorStatus implements ErrorReason {

    NOT_EXIST_SCHEDULE(HttpStatus.BAD_REQUEST, "Schedule4001", "일정이 존재하지 않습니다"),

    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

}