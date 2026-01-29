package assemble.api.member.repository;

import assemble.api.member.domain.mapping.MemberLikesClub;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberLikesClubRepository extends JpaRepository<MemberLikesClub, Long> {

    Optional<MemberLikesClub> findByMemberIdAndClubId(Long memberId, Long clubId);
}
