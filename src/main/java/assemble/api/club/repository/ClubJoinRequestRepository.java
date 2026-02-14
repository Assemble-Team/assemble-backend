package assemble.api.club.repository;

import assemble.api.club.domain.Club;
import assemble.api.club.domain.mapping.ClubJoinRequest;
import assemble.api.member.domain.Member;
import assemble.api.member.domain.enums.JoinStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ClubJoinRequestRepository extends JpaRepository<ClubJoinRequest, Long> {

    Optional<ClubJoinRequest> findByMemberAndClub(Member joinMember, Club club);

    @EntityGraph(attributePaths = {"member"})
    Page<ClubJoinRequest> findByClubAndStatus(Club club, JoinStatus status, Pageable pageable);
}
