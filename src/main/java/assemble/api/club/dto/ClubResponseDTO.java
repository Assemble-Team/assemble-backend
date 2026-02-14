package assemble.api.club.dto;

import assemble.api.club.domain.enums.ClubStatus;
import assemble.api.club.domain.enums.DifficultyLevel;
import assemble.api.member.domain.enums.InterestCategory;
import assemble.api.member.domain.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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
        String clubName;
        String description;
        String region;
        String imageUrl;
        InterestCategory category;
        DifficultyLevel level;
        Long likesNum;
        boolean liked;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FindClubListResultDTO{
        List<FindClubResultDTO> list;
        int page;
        int size;
        int totalPages;
        long totalElements;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FindClubResultDTO{
        Long clubId;
        String name;
        String imageUrl;
        String description;
        InterestCategory category;
        DifficultyLevel level;
        String region;
        ClubStatus status;
        Long curNumbers;
        Long maxNumbers;
        Long likes;
        boolean liked;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ClubMemberListResultDTO{
        Long clubId;
        List<ClubMemberDTO> list;
        long clubMemberNum;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ClubMemberDTO{
        Long memberId;
        String imageUrl;
        String name;
        String description;
        Role role;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetJoinRequestListDTO{
        List<GetJoinRequestDTO> list;
        int page;
        int size;
        int totalPage;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetJoinRequestDTO{
        LocalDate requestDate;
        Long memberId;
        String name;
        String imageUrl;
        String description;
    }
}
