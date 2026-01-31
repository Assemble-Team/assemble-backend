package assemble.api.club.repository;

import assemble.api.club.business.policy.ClubPolicy;
import assemble.api.club.domain.Club;
import assemble.api.club.domain.enums.DifficultyLevel;
import assemble.api.member.domain.enums.InterestCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClubRepositoryCustom {
    Page<Club> findClubsBy(String region, InterestCategory category, DifficultyLevel level, boolean recruiting, String sort, Pageable pageable);
}
