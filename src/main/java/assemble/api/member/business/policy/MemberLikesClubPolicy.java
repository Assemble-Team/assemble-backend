package assemble.api.member.business.policy;

import assemble.api.club.domain.Club;
import assemble.api.member.business.factory.MemberLikesClubFactory;
import assemble.api.member.domain.Member;
import assemble.api.member.domain.mapping.MemberLikesClub;
import assemble.api.member.repository.MemberLikesClubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MemberLikesClubPolicy {

    private final MemberLikesClubRepository memberLikesClubRepository;
    private final MemberLikesClubFactory  memberLikesClubFactory;

    public boolean checkMemberLikesClubPolicy(Member member, Club club, Optional<MemberLikesClub> memberLikesClub){
        if(memberLikesClub.isPresent()) {
            memberLikesClubRepository.delete(memberLikesClub.get());
            return false;
        }
        MemberLikesClub newMemberLikesClub = memberLikesClubFactory.create(member, club);
        memberLikesClubRepository.save(newMemberLikesClub);
        return true;
    }
}
