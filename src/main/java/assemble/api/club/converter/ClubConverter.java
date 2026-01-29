package assemble.api.club.converter;

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
}
