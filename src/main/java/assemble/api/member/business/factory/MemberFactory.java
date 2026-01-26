package assemble.api.member.business.factory;

import assemble.api.member.domain.Member;
import assemble.api.member.dto.MemberRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberFactory {

    private final PasswordEncoder passwordEncoder;

    public Member create(MemberRequestDTO.SignUpDTO signUpDTO) {
        return Member.builder()
                .email(signUpDTO.getEmail())
                .username(signUpDTO.getName())
                .password(passwordEncoder.encode(signUpDTO.getPassword()))
                .categories(signUpDTO.getCategories())
                .build();
    }
}
