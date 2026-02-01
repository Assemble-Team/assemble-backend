package assemble.api.notice.business.finder;

import assemble.api.apiPayload.handler.GeneralException;
import assemble.api.apiPayload.status.NoticeErrorStatus;
import assemble.api.club.domain.Club;
import assemble.api.notice.domain.Notice;
import assemble.api.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class NoticeFinder {

    private final NoticeRepository noticeRepository;

    public List<Notice> findByClub(Club club) {
        List<Notice> notices = noticeRepository.findByClubOrderByCreatedAtDesc(club, PageRequest.of(0, 3));
        if(notices.isEmpty()){
            throw new GeneralException(NoticeErrorStatus.NOT_EXIST_NOTICE);
        }
        return notices;
    }
}
