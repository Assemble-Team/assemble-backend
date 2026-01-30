package assemble.api.club.converter;

import assemble.api.club.domain.Club;
import assemble.api.club.dto.ClubResponseDTO;

import java.time.LocalDateTime;

public class ClubConverter {

    public static ClubResponseDTO.ClubResultDTO toClubResultDTO(Long clubId){
        return ClubResponseDTO.ClubResultDTO.builder()
                .clubId(clubId)
                .build();
    }

    public static ClubResponseDTO.ClubLikesResultDTO toClubLikesResultDTO(boolean liked){
        return ClubResponseDTO.ClubLikesResultDTO.builder()
                .liked(liked)
                .build();
    }

    public static ClubResponseDTO.ClubDetailResultDTO toClubDetailResultDTO(Club club, Long likesNum, boolean liked) {
        return ClubResponseDTO.ClubDetailResultDTO.builder()
                .clubName(club.getName())
                .description(club.getDescription())
                .level(club.getLevel())
                .region(club.getRegion())
                .category(club.getInterestCategory())
                .imageUrl(club.getImageUrl())
                .likesNum(likesNum)
                .liked(liked)
                .build();
    }
}
