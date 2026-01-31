package assemble.api.club.repository;

import assemble.api.club.domain.Club;
import assemble.api.member.domain.enums.InterestCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClubRepository extends JpaRepository<Club, Long>, ClubRepositoryCustom {



}
