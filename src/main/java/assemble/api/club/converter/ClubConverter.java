package assemble.api.club.converter;

import assemble.api.club.dto.ClubResponseDTO;

import java.time.LocalDateTime;

public class ClubConverter {

    public static ClubResponseDTO.CreateClubResultDTO toCreateClubResultDTO(Long clubId){
        return ClubResponseDTO.CreateClubResultDTO.builder()
                .clubId(clubId)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
