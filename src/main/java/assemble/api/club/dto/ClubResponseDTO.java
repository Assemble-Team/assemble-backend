package assemble.api.club.dto;

import assemble.api.club.domain.enums.ClubStatus;
import assemble.api.club.domain.enums.DifficultyLevel;
import assemble.api.member.domain.enums.InterestCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class ClubResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ClubResultDTO{
        Long clubId;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ClubLikesResultDTO{
        boolean liked;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ClubDetailResultDTO{
        private String clubName;
        private String description;
        private String region;
        private String imageUrl;
        private InterestCategory category;
        private DifficultyLevel level;
        private Long likesNum;
        private boolean liked;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FindClubListResultDTO{
        private List<FindClubResultDTO> list;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FindClubResultDTO{
        private String name;
        private String imageUrl;
        private String description;
        private InterestCategory category;
        private DifficultyLevel level;
        private String region;
        private ClubStatus status;
        private Long curNumbers;
        private Long maxNumbers;
        private Long likes;
        private boolean liked;
    }
}
