package assemble.api.club.repository;

import assemble.api.club.domain.mapping.ClubJoinRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubJoinRequestRepository extends JpaRepository<ClubJoinRequest, Long> {
}
