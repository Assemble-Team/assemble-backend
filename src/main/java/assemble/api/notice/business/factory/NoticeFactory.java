package assemble.api.notice.business.factory;

import assemble.api.club.domain.Club;
import assemble.api.notice.domain.Notice;
import assemble.api.notice.dto.NoticeRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class NoticeFactory {

    public Notice create(NoticeRequestDTO.ClubNoticeDTO request, Club club) {
        return Notice.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .club(club)
                .build();
    }
}
