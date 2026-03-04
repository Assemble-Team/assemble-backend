package assemble.api.club.business.finder;

import assemble.api.apiPayload.handler.GeneralException;
import assemble.api.apiPayload.status.ClubErrorStatus;
import assemble.api.club.business.policy.ClubPolicy;
import assemble.api.club.domain.Club;
import assemble.api.club.domain.enums.DifficultyLevel;
import assemble.api.club.repository.ClubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class ClubFinder {

    private final ClubRepository clubRepository;

    public Club findByClubId(Long clubId) {
        return clubRepository.findById(clubId)
                .orElseThrow(() -> new GeneralException(ClubErrorStatus.NOT_EXIST_CLUB));
    }

    // clubPolicy.parseLevel 삭제 가능 여부 확인
    public Page<Club> findClubs(String region, String category, List<DifficultyLevel> level, boolean recruiting, String sort, Pageable pageable) {
        return clubRepository.findClubsBy(region, ClubPolicy.parseCategory(category), level, recruiting,  sort, pageable);
    }

}
