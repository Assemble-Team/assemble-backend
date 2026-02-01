package assemble.api.notice.converter;

import assemble.api.notice.domain.Notice;
import assemble.api.notice.dto.NoticeResponseDTO;

import java.util.List;

public class NoticeConverter {

    public static NoticeResponseDTO.ClubNoticeResultDTO toClubNoticeResultDTO(Long noticeId, Long clubId){
        return NoticeResponseDTO.ClubNoticeResultDTO.builder()
                .noticeId(noticeId)
                .clubId(clubId)
                .build();
    }

    public static NoticeResponseDTO.ClubNoticeListResultDTO toClubNoticeListResultDTO(List<Notice> noticeList, Long clubId) {
        List<NoticeResponseDTO.GetClubNoticeDTO> result = noticeList.stream()
                .map(NoticeConverter::toGetClubNoticeDTO)
                .toList();
        return NoticeResponseDTO.ClubNoticeListResultDTO.builder()
                .list(result)
                .clubId(clubId)
                .build();
    }

    public static NoticeResponseDTO.GetClubNoticeDTO toGetClubNoticeDTO(Notice notice){
        return NoticeResponseDTO.GetClubNoticeDTO.builder()
                .noticeId(notice.getId())
                .title(notice.getTitle())
                .content(notice.getContent())
                .createdAt(notice.getCreatedAt())
                .build();
    }
}
