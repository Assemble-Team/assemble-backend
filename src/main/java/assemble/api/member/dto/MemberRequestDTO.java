package assemble.api.member.dto;

import assemble.api.member.domain.enums.InterestCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

public class MemberRequestDTO {


    @Getter
    @NoArgsConstructor
    public static class SignUpDTO{

        @NotBlank(message = "닉네임은 필수 입력입니다")
        private String name;

        @NotBlank(message = "이메일은 필수 입력입니다.")
        @Pattern(
                regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.(com|net|org|co\\.kr|go\\.kr)$",
                message = "유효한 이메일 도메인만 입력하세요."
        )
        private String email;

        @NotBlank(message = "비밀번호는 필수 입력입니다")
        private String password;

        private Set<InterestCategory> categories = new HashSet<>();

    }

    @Getter
    @NoArgsConstructor
    public static class SendEmailDTO{

        @NotBlank(message = "이메일은 필수 입력입니다.")
        @Pattern(
                regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.(com|net|org|co\\.kr|go\\.kr)$",
                message = "유효한 이메일 도메인만 입력하세요."
        )
        private String email;

    }

    @Getter
    @NoArgsConstructor
    public static class CheckEmailDTO{

        @NotBlank(message = "이메일은 필수 입력입니다.")
        @Pattern(
                regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.(com|net|org|co\\.kr|go\\.kr)$",
                message = "유효한 이메일 도메인만 입력하세요."
        )
        private String email;

        @NotBlank(message = "인증코드는 필수 입력입니다")
        private String code;

    }
}
