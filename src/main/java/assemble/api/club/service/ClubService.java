package assemble.api.club.service;

import assemble.api.club.business.factory.ClubFactory;
import assemble.api.club.business.finder.ClubFinder;
import assemble.api.club.business.policy.ClubPolicy;
import assemble.api.club.converter.ClubConverter;
import assemble.api.club.domain.Club;
import assemble.api.club.domain.mapping.MemberClub;
import assemble.api.club.dto.ClubRequestDTO;
import assemble.api.club.dto.ClubResponseDTO;
import assemble.api.club.repository.ClubRepository;
import assemble.api.club.repository.MemberClubRepository;
import assemble.api.member.business.factory.MemberLikesClubFactory;
import assemble.api.member.business.finder.MemberLikesClubFinder;
import assemble.api.member.business.policy.MemberLikesClubPolicy;
import assemble.api.member.domain.Member;
import assemble.api.member.domain.mapping.MemberLikesClub;
import assemble.api.member.repository.MemberLikesClubRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class ClubService {

    private final ClubFactory clubFactory;
    private final ClubFinder clubFinder;
    private final ClubPolicy clubPolicy;
    private final ClubRepository clubRepository;
    private final MemberClubRepository memberClubRepository;
    private final MemberLikesClubFinder memberLikesClubFinder;
    private final MemberLikesClubPolicy memberLikesClubPolicy;

    public ClubResponseDTO.ClubResultDTO createClub(Member member, ClubRequestDTO.CreateClubDTO request) {

        Club club = clubFactory.create(request);
        clubRepository.save(club);

        MemberClub memberClub = MemberClub.create(member, club);
        memberClubRepository.save(memberClub);

        return ClubConverter.toClubResultDTO(club.getId());
    }

    public ClubResponseDTO.ClubResultDTO updateClub(Member member, Long clubId, ClubRequestDTO.UpdateClubDTO request) {
        Club club = clubFinder.findByClubId(clubId);
        clubPolicy.validateUpdateInfo(club, member);
        club.updateInfo(request);
        return ClubConverter.toClubResultDTO(club.getId());
    }

    public ClubResponseDTO.ClubLikesResultDTO createClubLikes(Member member, Long clubId) {
        Club club = clubFinder.findByClubId(clubId);
        Optional<MemberLikesClub> memberLikesClub = memberLikesClubFinder.findByMemberAndClub(member.getId(), clubId);

        boolean liked = memberLikesClubPolicy.checkMemberLikesClubPolicy(member, club, memberLikesClub);

        return ClubConverter.toClubLikesResultDTO(liked);
    }

    public ClubResponseDTO.ClubDetailResultDTO getClubDetailInfo(Member member, Long clubId) {
        Club club = clubFinder.findByClubId(clubId);
        Long likesNum = memberLikesClubFinder.countByClub(clubId);
        boolean liked = memberLikesClubFinder.existsByMemberAndClub(member.getId(), clubId);
        return ClubConverter.toClubDetailResultDTO(club, likesNum, liked);
    }

    public ClubResponseDTO.FindClubListResultDTO getClubListInfo(Member member, String region, String category, String level, boolean recruiting, String sort) {
        List<Club> clubList = clubFinder.findClubs(region, category, level, recruiting, sort);
        Set<Long> likedClubIds = memberLikesClubFinder.findLikedClubsByMember(member.getId());
        return ClubConverter.toFindClubListResultDTO(clubList, likedClubIds);
    }
}
