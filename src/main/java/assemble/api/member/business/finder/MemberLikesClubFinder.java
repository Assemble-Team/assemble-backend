package assemble.api.member.business.finder;

import assemble.api.apiPayload.handler.GeneralException;
import assemble.api.apiPayload.status.MemberErrorStatus;
import assemble.api.club.domain.Club;
import assemble.api.member.domain.Member;
import assemble.api.member.domain.mapping.MemberLikesClub;
import assemble.api.member.repository.MemberLikesClubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MemberLikesClubFinder {

    private final MemberLikesClubRepository memberLikesClubRepository;

    public Optional<MemberLikesClub> findByMemberAndClub(Long memberId, Long clubId){
        return memberLikesClubRepository.findByMemberIdAndClubId(memberId, clubId);
    }
}
