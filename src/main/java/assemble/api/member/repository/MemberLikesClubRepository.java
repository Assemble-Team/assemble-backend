package assemble.api.member.repository;

import assemble.api.member.domain.mapping.MemberLikesClub;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface MemberLikesClubRepository extends JpaRepository<MemberLikesClub, Long> {

    Optional<MemberLikesClub> findByMemberIdAndClubId(Long memberId, Long clubId);

    Long countByMemberIdAndClubId(Long memberId, Long clubId);

    Long countByClubId(Long clubId);

    List<MemberLikesClub> findBymemberId(java.lang.Long memberId);
}
