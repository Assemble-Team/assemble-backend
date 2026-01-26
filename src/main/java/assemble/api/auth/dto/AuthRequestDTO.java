package assemble.api.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

public class AuthRequestDTO {

    @Getter
    public static class LoginDTO{

        @NotBlank(message = "이메일은 필수 입력입니다.")
        @Pattern(
                regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.(com|net|org|co\\.kr|go\\.kr)$",
                message = "유효한 이메일 도메인만 입력하세요."
        )
        private String email;

        @NotBlank(message = "비밀번호는 필수 입력입니다")
        private String password;

    }

}
