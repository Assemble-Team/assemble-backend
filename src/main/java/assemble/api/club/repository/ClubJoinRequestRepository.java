package assemble.api.club.repository;

import assemble.api.club.domain.Club;
import assemble.api.club.domain.mapping.ClubJoinRequest;
import assemble.api.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ClubJoinRequestRepository extends JpaRepository<ClubJoinRequest, Long> {

    Optional<ClubJoinRequest> findByMemberAndClub(Member joinMember, Club club);
}
