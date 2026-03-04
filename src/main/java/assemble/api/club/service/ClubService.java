package assemble.api.club.service;

import assemble.api.club.business.factory.ClubFactory;
import assemble.api.club.business.factory.ClubJoinRequestFactory;
import assemble.api.club.business.factory.MemberClubFactory;
import assemble.api.club.business.finder.ClubFinder;
import assemble.api.club.business.finder.ClubJoinRequestFinder;
import assemble.api.club.business.finder.MemberClubFinder;
import assemble.api.club.business.policy.ClubPolicy;
import assemble.api.club.business.policy.MemberClubPolicy;
import assemble.api.club.business.validator.MemberClubValidator;
import assemble.api.club.converter.ClubConverter;
import assemble.api.club.domain.Club;
import assemble.api.club.domain.enums.DifficultyLevel;
import assemble.api.club.domain.mapping.ClubJoinRequest;
import assemble.api.club.domain.mapping.MemberClub;
import assemble.api.club.dto.ClubRequestDTO;
import assemble.api.club.dto.ClubResponseDTO;
import assemble.api.club.repository.ClubJoinRequestRepository;
import assemble.api.club.repository.ClubRepository;
import assemble.api.club.repository.MemberClubRepository;
import assemble.api.member.business.factory.MemberLikesClubFactory;
import assemble.api.member.business.finder.MemberFinder;
import assemble.api.member.business.finder.MemberLikesClubFinder;
import assemble.api.member.business.policy.MemberLikesClubPolicy;
import assemble.api.member.domain.Member;
import assemble.api.member.domain.enums.JoinStatus;
import assemble.api.member.domain.mapping.MemberLikesClub;
import assemble.api.member.repository.MemberLikesClubRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class ClubService {

    private final ClubFactory clubFactory;
    private final ClubJoinRequestFactory clubJoinRequestFactory;
    private final ClubFinder clubFinder;
    private final ClubPolicy clubPolicy;
    private final ClubRepository clubRepository;
    private final MemberClubFinder  memberClubFinder;
    private final MemberClubRepository memberClubRepository;
    private final MemberLikesClubFinder memberLikesClubFinder;
    private final ClubJoinRequestFinder clubJoinRequestFinder;
    private final MemberLikesClubPolicy memberLikesClubPolicy;
    private final ClubJoinRequestRepository clubJoinRequestRepository;
    private final MemberClubPolicy memberClubPolicy;
    private final MemberClubFactory memberClubFactory;
    private final MemberFinder memberFinder;

    public ClubResponseDTO.ClubResultDTO createClub(Long memberId, ClubRequestDTO.CreateClubDTO request) {
        Member member = memberFinder.findById(memberId);
        Club club = clubFactory.create(request);
        clubRepository.save(club);

        MemberClub memberClub = memberClubFactory.create(member, club);
        memberClubRepository.save(memberClub);

        return ClubConverter.toClubResultDTO(club.getId());
    }

    public ClubResponseDTO.ClubResultDTO updateClub(Long memberId, Long clubId, ClubRequestDTO.UpdateClubDTO request) {
        Member member = memberFinder.findById(memberId);
        Club club = clubFinder.findByClubId(clubId);
        clubPolicy.validateUpdateInfo(club, member);
        club.updateInfo(request);
        return ClubConverter.toClubResultDTO(club.getId());
    }

    public ClubResponseDTO.ClubLikesResultDTO createClubLikes(Long memberId, Long clubId) {
        Member member = memberFinder.findById(memberId);
        Club club = clubFinder.findByClubId(clubId);
        Optional<MemberLikesClub> memberLikesClub = memberLikesClubFinder.findByMemberAndClub(member.getId(), clubId);

        boolean liked = memberLikesClubPolicy.checkMemberLikesClubPolicy(member, club, memberLikesClub);

        return ClubConverter.toClubLikesResultDTO(liked);
    }

    public ClubResponseDTO.ClubDetailResultDTO getClubDetailInfo(Long memberId, Long clubId) {
        Member member = memberFinder.findById(memberId);
        Club club = clubFinder.findByClubId(clubId);
        Long likesNum = memberLikesClubFinder.countByClub(clubId);
        boolean liked = memberLikesClubFinder.existsByMemberAndClub(member.getId(), clubId);
        return ClubConverter.toClubDetailResultDTO(club, likesNum, liked);
    }

    public ClubResponseDTO.FindClubListResultDTO getClubListInfo(Long memberId, String region, String category, List<DifficultyLevel> level, boolean recruiting, String sort, Pageable pageable) {
        Member member = memberFinder.findById(memberId);
        Page<Club> clubPage = clubFinder.findClubs(region, category, level, recruiting, sort, pageable);
        Set<Long> likedClubIds = memberLikesClubFinder.findLikedClubsByMember(member.getId());
        return ClubConverter.toFindClubListResultDTO(clubPage, likedClubIds);
    }

    public ClubResponseDTO.ClubMemberListResultDTO getClubMemberListInfo(Long clubId) {
        Club club = clubFinder.findByClubId(clubId);
        List<MemberClub> memberClubList = memberClubFinder.findByClub(club);
        return ClubConverter.toClubMemberListResultDTO(memberClubList, clubId);
    }

    public void createJoinClubRequest(Long memberId, Long clubId, ClubRequestDTO.JoinRequestDTO request) {
        Member member = memberFinder.findById(memberId);
        Club club = clubFinder.findByClubId(clubId);
        memberClubFinder.checkNotExistMemberClub(member, club);

        ClubJoinRequest clubJoinRequest = clubJoinRequestFactory.create(request.getDescription(), member, club);
        clubJoinRequestRepository.save(clubJoinRequest);
    }


    public void approveOrReject(Long memberId, Long clubId, ClubRequestDTO.ApproveOrRejectDTO request) {
        Member member = memberFinder.findById(memberId);
        Club club = clubFinder.findByClubId(clubId);
        MemberClub memberClub = memberClubFinder.findByMemberAndClub(member, club);
        memberClubPolicy.validateLeaderOrManager(memberClub);

        Member joinMember = memberFinder.findById(request.getMemberId());
        ClubJoinRequest clubJoinRequest = clubJoinRequestFinder.findByMemberAndClub(joinMember, club);
        clubJoinRequest.updateStatus(request.isApprove() ? JoinStatus.APPROVED : JoinStatus.REJECTED);

        MemberClub newMemberClub = memberClubFactory.create(joinMember, club);
        memberClubRepository.save(newMemberClub);
    }

    public void changeAuthority(Long memberId, Long clubId, ClubRequestDTO.ChangeMemberAuthorityDTO request) {
        Member member = memberFinder.findById(memberId);
        Club club = clubFinder.findByClubId(clubId);
        MemberClub memberClub = memberClubFinder.findByMemberAndClub(member, club);
        memberClubPolicy.validateLeaderOrManager(memberClub);

        Member joinMember = memberFinder.findById(request.getMemberId());
        MemberClub joinMemberClub = memberClubFinder.findByMemberAndClub(joinMember, club);

        joinMemberClub.changeRole(MemberClubValidator.parseRole(request.getAuthority()));
    }

    public ClubResponseDTO.GetJoinRequestListDTO getJoinRequestListInfo(Long memberId, Long clubId, Pageable pageable) {
        Member member = memberFinder.findById(memberId);
        Club club = clubFinder.findByClubId(clubId);
        MemberClub memberClub = memberClubFinder.findByMemberAndClub(member, club);

        Page<ClubJoinRequest> clubJoinRequestList = clubJoinRequestFinder.findByClubAndStatus(club, JoinStatus.PENDING, pageable);

        return ClubConverter.toGetJoinRequestListDTO(clubJoinRequestList);
    }

    public ClubResponseDTO.GetClubMemberAuthorityListDTO getClubAuthorityListInfo(Long memberId, Long clubId, Pageable pageable) {
        Member member = memberFinder.findById(memberId);
        Club club = clubFinder.findByClubId(clubId);
        MemberClub memberClub = memberClubFinder.findByMemberAndClub(member, club);

        Page<MemberClub> memberClubPage = memberClubFinder.findByClubPage(club, pageable);

        return ClubConverter.toGetClubMemberAuthorityListDTO(memberClubPage);
    }

    public void deleteMemberClub(Long memberId, Long clubId) {
        Member member = memberFinder.findById(memberId);
        Club club = clubFinder.findByClubId(clubId);
        MemberClub memberClub = memberClubFinder.findByMemberAndClub(member, club);
        memberClubRepository.delete(memberClub);
    }
}
