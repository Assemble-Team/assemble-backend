package assemble.api.notice.converter;

import assemble.api.notice.dto.NoticeResponseDTO;

public class NoticeConverter {

    public static NoticeResponseDTO.ClubNoticeResultDTO toClubNoticeResultDTO(Long noticeId, Long clubId){
        return NoticeResponseDTO.ClubNoticeResultDTO.builder()
                .noticeId(noticeId)
                .clubId(clubId)
                .build();
    }
}
