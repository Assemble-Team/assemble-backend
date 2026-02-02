package assemble.api.club.business.finder;

import assemble.api.apiPayload.handler.GeneralException;
import assemble.api.apiPayload.status.ClubErrorStatus;
import assemble.api.club.domain.Club;
import assemble.api.club.domain.mapping.MemberClub;
import assemble.api.club.repository.MemberClubRepository;
import assemble.api.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MemberClubFinder {

    private final MemberClubRepository memberClubRepository;

    public MemberClub findByMemberAndClub(Member member, Club club) {
        return memberClubRepository.findByMemberAndClub(member, club)
                .orElseThrow(() -> new GeneralException(ClubErrorStatus.NOT_JOIN_MEMBER_AND_CLUB));
    }

    public List<MemberClub> findByClub(Club club) {
        List<MemberClub> memberClubList = memberClubRepository.findByClub(club);
        if(memberClubList.isEmpty()){
            throw new GeneralException(ClubErrorStatus.NOT_EXIST_ANY_MEMBER_IN_CLUB);
        }
        return memberClubList;
    }
}
