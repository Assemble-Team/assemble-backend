package assemble.api.club.business.factory;

import assemble.api.club.domain.Club;
import assemble.api.club.domain.mapping.ClubJoinRequest;
import assemble.api.member.domain.Member;
import assemble.api.member.domain.enums.JoinStatus;
import org.springframework.stereotype.Component;

@Component
public class ClubJoinRequestFactory {

    public ClubJoinRequest create(String description, Member member, Club club){
        return ClubJoinRequest.builder()
                .member(member)
                .club(club)
                .message(description)
                .status(JoinStatus.PENDING)
                .build();
    }
}
