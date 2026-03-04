package assemble.api.club.converter;

import assemble.api.club.domain.Club;
import assemble.api.club.domain.mapping.ClubJoinRequest;
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
                .online(club.isOnline())
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
                .online(club.isOnline())
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

    public static ClubResponseDTO.GetJoinRequestListDTO toGetJoinRequestListDTO(Page<ClubJoinRequest> clubJoinRequestList) {
        List<ClubResponseDTO.GetJoinRequestDTO> list = clubJoinRequestList.stream()
                .map(ClubConverter::toGetJoinRequestDTO).toList();
        return ClubResponseDTO.GetJoinRequestListDTO.builder()
                .list(list)
                .size(clubJoinRequestList.getSize())
                .page(clubJoinRequestList.getNumber())
                .totalPage(clubJoinRequestList.getTotalPages())
                .build();
    }

    public static ClubResponseDTO.GetJoinRequestDTO toGetJoinRequestDTO(ClubJoinRequest request){
        return ClubResponseDTO.GetJoinRequestDTO.builder()
                .name(request.getMember().getUsername())
                .memberId(request.getMember().getId())
                .description(request.getMessage())
                .imageUrl(request.getMember().getUrl())
                .requestDate(request.getCreatedAt().toLocalDate())
                .build();
    }

    public static ClubResponseDTO.GetClubMemberAuthorityListDTO toGetClubMemberAuthorityListDTO(Page<MemberClub> memberClubPage) {
        List<ClubResponseDTO.GetClubMemberAuthorityDTO> list = memberClubPage.stream()
                .map(ClubConverter::toGetClubMemberAuthorityDTO).toList();
        return ClubResponseDTO.GetClubMemberAuthorityListDTO.builder()
                .list(list)
                .totalPage(memberClubPage.getTotalPages())
                .size(memberClubPage.getSize())
                .page(memberClubPage.getNumber())
                .build();
    }

    public static ClubResponseDTO.GetClubMemberAuthorityDTO toGetClubMemberAuthorityDTO(MemberClub memberClub){
        return ClubResponseDTO.GetClubMemberAuthorityDTO.builder()
                .memberId(memberClub.getMember().getId())
                .role(memberClub.getRole())
                .imageUrl(memberClub.getMember().getUrl())
                .name(memberClub.getMember().getUsername())
                .build();
    }
}
