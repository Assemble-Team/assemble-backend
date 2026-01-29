package assemble.api.member.business.factory;

import assemble.api.club.domain.Club;
import assemble.api.member.domain.Member;
import assemble.api.member.domain.mapping.MemberLikesClub;
import org.springframework.stereotype.Component;

@Component
public class MemberLikesClubFactory {

    public MemberLikesClub create(Member member, Club club) {
        return MemberLikesClub.builder()
                .member(member)
                .club(club)
                .build();
    }
}
