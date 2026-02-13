package assemble.api.club.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ClubRequestDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateClubDTO{

        @NotNull
        String name;

        @NotNull
        String level;

        @NotNull
        String category;

        @NotNull
        String region;

        Long maxNumber;

        @NotNull
        String description;

        String imageUrl;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateClubDTO{
        String name;

        String description;

        String imageUrl;

        Long maxNumber;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JoinRequestDTO{
        @NotNull
        String description;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ApproveOrRejectDTO{
        @NotNull
        Long memberId;

        @NotNull
        boolean approve;
    }
}
