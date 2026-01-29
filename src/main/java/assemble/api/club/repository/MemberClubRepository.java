package assemble.api.club.repository;

import assemble.api.club.domain.Club;
import assemble.api.club.domain.mapping.MemberClub;
import assemble.api.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberClubRepository extends JpaRepository<MemberClub, Long> {

    Optional<MemberClub> findByMemberAndClub(Member member, Club club);
}
