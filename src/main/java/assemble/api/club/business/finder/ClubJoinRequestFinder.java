package assemble.api.club.business.finder;

import assemble.api.apiPayload.handler.GeneralException;
import assemble.api.apiPayload.status.ClubErrorStatus;
import assemble.api.club.domain.Club;
import assemble.api.club.domain.mapping.ClubJoinRequest;
import assemble.api.club.repository.ClubJoinRequestRepository;
import assemble.api.club.repository.ClubRepository;
import assemble.api.member.domain.Member;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClubJoinRequestFinder {

    private final ClubJoinRequestRepository clubJoinRequestRepository;

    public ClubJoinRequest findByMemberAndClub(Member joinMember, Club club) {
        return clubJoinRequestRepository.findByMemberAndClub(joinMember, club)
                .orElseThrow(() -> new GeneralException(ClubErrorStatus.NOT_EXIST_CLUB_JOIN_REQUEST));
    }
}
