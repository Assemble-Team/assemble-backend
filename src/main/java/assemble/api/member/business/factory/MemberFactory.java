package assemble.api.member.business.factory;

import assemble.api.member.domain.Member;
import assemble.api.member.dto.MemberRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class MemberFactory {

    public Member create(MemberRequestDTO.SignUpDTO signUpDTO) {
        return Member.builder()
                .email(signUpDTO.getEmail())
                .username(signUpDTO.getName())
                .password(signUpDTO.getPassword())
                .categories(signUpDTO.getCategories())
                .build();
    }
}
