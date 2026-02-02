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
    NOT_EXIST_CLUB(HttpStatus.BAD_REQUEST, "CLUB4003", "존재하지 않는 소모임입니다"),
    NOT_JOIN_MEMBER_AND_CLUB(HttpStatus.BAD_REQUEST, "CLUB4004", "해당 회원은 해당 소모임에 가입한 적이 없습니다"),
    UPDATE_PERMISSION_DENIED(HttpStatus.BAD_REQUEST, "CLUB4005", "해당 회원은 소모임을 수정할 권한이 없습니다"),
    CREATE_NOTICE_PERMISSION_DENIED(HttpStatus.BAD_REQUEST, "CLUB4006", "해당 회원은 소모임의 공지사항을 생성할 권한이 없습니다"),
    NOT_EXIST_ANY_MEMBER_IN_CLUB(HttpStatus.BAD_REQUEST, "CLUB4007", "해당 소모임에 존재하는 회원이 없습니다"),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
