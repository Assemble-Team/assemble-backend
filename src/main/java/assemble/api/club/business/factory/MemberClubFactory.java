package assemble.api.club.business.factory;

import assemble.api.club.domain.Club;
import assemble.api.club.domain.mapping.MemberClub;
import assemble.api.member.domain.Member;
import assemble.api.member.domain.enums.Role;
import org.springframework.stereotype.Component;

@Component
public class MemberClubFactory {

    public MemberClub create(Member member, Club club){
        return MemberClub.builder()
                .club(club)
                .member(member)
                .role(Role.MEMBER)
                .build();
    }
}
