package assemble.api.club.repository;

import assemble.api.club.business.policy.ClubPolicy;
import assemble.api.club.domain.Club;
import assemble.api.club.domain.enums.DifficultyLevel;
import assemble.api.member.domain.enums.InterestCategory;

import java.util.List;

public interface ClubRepositoryCustom {
    List<Club> findClubsBy(String region, InterestCategory category, DifficultyLevel level, boolean recruiting, String sort);
}
