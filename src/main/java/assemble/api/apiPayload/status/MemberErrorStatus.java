package assemble.api.apiPayload.status;

import assemble.api.member.infrastructure.email.EmailSender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberErrorStatus implements ErrorReason {

    WRONG_CODE(HttpStatus.BAD_REQUEST, "MEMBER4001", "이메일 인증코드가 올바르지 않습니다"),
    EXIST_EMAIL(HttpStatus.BAD_REQUEST, "MEMBER4002", "이미 존재하는 이메일 주소입니다"),
    NOT_EMAIL_VERIFY(HttpStatus.BAD_REQUEST, "MEMBER4003", "이메일 인증 정보가 없습니다. 다시 진행하세요"),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

}
