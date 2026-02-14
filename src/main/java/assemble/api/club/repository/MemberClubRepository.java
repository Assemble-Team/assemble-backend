package assemble.api.club.repository;

import assemble.api.club.domain.Club;
import assemble.api.club.domain.mapping.MemberClub;
import assemble.api.member.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberClubRepository extends JpaRepository<MemberClub, Long> {

    Optional<MemberClub> findByMemberAndClub(Member member, Club club);

    List<MemberClub> findByClub(Club club);

    boolean existsByMemberAndClub(Member member, Club club);

    @EntityGraph(attributePaths = {"member"})
    Page<MemberClub> findByClub(Club club, Pageable pageable);
}
