package assemble.api.club.repository;

import assemble.api.club.domain.mapping.MemberClub;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberClubRepository extends JpaRepository<MemberClub, Long> {
}
