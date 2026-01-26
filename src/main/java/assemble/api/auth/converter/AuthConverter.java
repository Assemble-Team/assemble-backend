package assemble.api.auth.converter;

import assemble.api.auth.dto.AuthResponseDTO;
import assemble.api.member.domain.Member;

import java.time.LocalDateTime;

public class AuthConverter {

    public static AuthResponseDTO.LoginResultDTO toLoginResultDTO(Long memberId, String refresh){
        return AuthResponseDTO.LoginResultDTO.builder()
                .memberId(memberId)
                .refreshToken(refresh)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
