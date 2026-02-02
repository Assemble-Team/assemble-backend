package assemble.api.club.converter;

import assemble.api.club.domain.Club;
import assemble.api.club.domain.mapping.MemberClub;
import assemble.api.club.dto.ClubResponseDTO;
import assemble.api.member.domain.Member;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

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

    public static ClubResponseDTO.FindClubListResultDTO toFindClubListResultDTO(Page<Club> clubPage, Set<Long> likedClubIds) {
        List<ClubResponseDTO.FindClubResultDTO> result = clubPage.stream()
                .map(club -> toFindClubResultDTO(club, likedClubIds.contains(club.getId())
                ))
                .toList();
        return ClubResponseDTO.FindClubListResultDTO.builder()
                .list(result)
                .page(clubPage.getNumber())
                .size(clubPage.getSize())
                .totalPages(clubPage.getTotalPages())
                .totalElements(clubPage.getTotalPages())
                .build();
    }

    public static ClubResponseDTO.FindClubResultDTO toFindClubResultDTO(Club club, boolean liked) {
        return ClubResponseDTO.FindClubResultDTO.builder()
                .clubId(club.getId())
                .name(club.getName())
                .imageUrl(club.getImageUrl())
                .description(club.getDescription())
                .category(club.getInterestCategory())
                .level(club.getLevel())
                .region(club.getRegion())
                .status(club.getStatus())
                .curNumbers(club.getCurNumbers())
                .maxNumbers(club.getMaxNumbers())
                .likes(club.getLikesCount())
                .liked(liked)
                .build();
    }

    public static ClubResponseDTO.ClubMemberListResultDTO toClubMemberListResultDTO(List<MemberClub> memberClubList, Long clubId) {
        List<ClubResponseDTO.ClubMemberDTO> list = memberClubList.stream()
                .map(memberClub -> {
                    Member member = memberClub.getMember();
                    return toClubMemberDTO(member, memberClub);
                }).toList();
        return ClubResponseDTO.ClubMemberListResultDTO.builder()
                .list(list)
                .clubMemberNum(list.size())
                .clubId(clubId)
                .build();
    }

    public static ClubResponseDTO.ClubMemberDTO toClubMemberDTO(Member member, MemberClub memberClub) {
        return ClubResponseDTO.ClubMemberDTO.builder()
                .memberId(member.getId())
                .role(memberClub.getRole())
                .name(member.getUsername())
                .description(member.getDescription())
                .imageUrl(member.getUrl())
                .build();
    }
}
