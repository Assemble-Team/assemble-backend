package assemble.api.notice.repository;

import assemble.api.club.domain.Club;
import assemble.api.notice.domain.Notice;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    List<Notice> findByClubOrderByCreatedAtDesc(Club club, Pageable pageable);
}
