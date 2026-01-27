package assemble.api.club.service;

import assemble.api.apiPayload.handler.GeneralException;
import assemble.api.apiPayload.status.MemberErrorStatus;
import assemble.api.club.business.factory.ClubFactory;
import assemble.api.club.converter.ClubConverter;
import assemble.api.club.domain.Club;
import assemble.api.club.domain.mapping.MemberClub;
import assemble.api.club.dto.ClubRequestDTO;
import assemble.api.club.dto.ClubResponseDTO;
import assemble.api.club.repository.ClubRepository;
import assemble.api.club.repository.MemberClubRepository;
import assemble.api.member.domain.Member;
import assemble.api.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class ClubService {

    private final ClubFactory clubFactory;
    private final ClubRepository clubRepository;
    private final MemberClubRepository memberClubRepository;

    public ClubResponseDTO.CreateClubResultDTO createClub(Member member, ClubRequestDTO.CreateClubDTO request) {

        Club club = clubFactory.create(request);
        clubRepository.save(club);

        MemberClub memberClub = MemberClub.create(member, club);
        memberClubRepository.save(memberClub);

        return ClubConverter.toCreateClubResultDTO(club.getId());
    }
}
