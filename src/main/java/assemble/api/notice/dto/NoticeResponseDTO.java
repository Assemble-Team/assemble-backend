package assemble.api.notice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class NoticeResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ClubNoticeResultDTO{
        private Long clubId;
        private Long noticeId;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ClubNoticeListResultDTO{
        private List<GetClubNoticeDTO> list;
        private Long clubId;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetClubNoticeDTO{
        private Long noticeId;
        private String title;
        private String content;
        private LocalDateTime createdAt;
    }
}
