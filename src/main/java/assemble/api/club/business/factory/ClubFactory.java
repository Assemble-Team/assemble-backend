package assemble.api.club.business.factory;

import assemble.api.club.business.policy.ClubPolicy;
import assemble.api.club.domain.Club;
import assemble.api.club.domain.enums.ClubStatus;
import assemble.api.club.dto.ClubRequestDTO;
import assemble.api.club.repository.ClubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClubFactory {

    public Club create(ClubRequestDTO.CreateClubDTO request) {
        return Club.builder()
                .name(request.getName())
                .region(request.getRegion())
                .level(ClubPolicy.parseLevel(request.getLevel()))
                .interestCategory(ClubPolicy.parseCategory(request.getCategory()))
                .description(request.getDescription())
                .status(ClubStatus.RECRUTING)
                .maxNumbers(request.getMaxNumber())
                .imageUrl(request.getImageUrl())
                .build();
    }
}
