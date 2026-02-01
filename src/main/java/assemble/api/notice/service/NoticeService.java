package assemble.api.notice.service;

import assemble.api.club.business.finder.ClubFinder;
import assemble.api.club.business.finder.MemberClubFinder;
import assemble.api.club.business.policy.MemberClubPolicy;
import assemble.api.club.domain.Club;
import assemble.api.club.domain.mapping.MemberClub;
import assemble.api.member.domain.Member;
import assemble.api.notice.business.factory.NoticeFactory;
import assemble.api.notice.business.finder.NoticeFinder;
import assemble.api.notice.converter.NoticeConverter;
import assemble.api.notice.domain.Notice;
import assemble.api.notice.dto.NoticeRequestDTO;
import assemble.api.notice.dto.NoticeResponseDTO;
import assemble.api.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final ClubFinder clubFinder;
    private final NoticeFinder noticeFinder;
    private final MemberClubFinder memberClubFinder;
    private final NoticeFactory noticeFactory;
    private final MemberClubPolicy memberClubPolicy;
    private final NoticeRepository noticeRepository;

    public NoticeResponseDTO.ClubNoticeResultDTO createClubNotice(Member member, Long clubId, NoticeRequestDTO.ClubNoticeDTO request) {
        Club club = clubFinder.findByClubId(clubId);

        MemberClub memberClub = memberClubFinder.findByMemberAndClub(member, club);
        memberClubPolicy.validateLeaderOrManager(memberClub);

        Notice notice = noticeFactory.create(request, club);
        noticeRepository.save(notice);

        return NoticeConverter.toClubNoticeResultDTO(notice.getId(), club.getId());
    }

    public NoticeResponseDTO.ClubNoticeListResultDTO getClubNoticeList(Member member, Long clubId) {
        Club club = clubFinder.findByClubId(clubId);

        MemberClub memberClub = memberClubFinder.findByMemberAndClub(member, club);

        List<Notice> notice = noticeFinder.findByClub(club);
        return NoticeConverter.toClubNoticeListResultDTO(notice, clubId);
    }
}
