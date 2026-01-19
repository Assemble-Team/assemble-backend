package assemble.api.member.converter;

import assemble.api.member.domain.Member;
import assemble.api.member.dto.MemberResponseDTO;

import java.time.LocalDateTime;

public class MemberConverter {

    public static MemberResponseDTO.MemberCreateDTO toMemberCreateDTO(Member member) {
        return MemberResponseDTO.MemberCreateDTO.builder()
                .id(member.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
