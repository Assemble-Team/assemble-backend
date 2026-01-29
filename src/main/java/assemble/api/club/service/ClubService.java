package assemble.api.club.service;

import assemble.api.apiPayload.handler.GeneralException;
import assemble.api.apiPayload.status.MemberErrorStatus;
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
import assemble.api.member.domain.Member;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class ClubService {

    private final ClubFactory clubFactory;
    private final ClubFinder clubFinder;
    private final ClubPolicy clubPolicy;
    private final ClubRepository clubRepository;
    private final MemberClubRepository memberClubRepository;

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
}
