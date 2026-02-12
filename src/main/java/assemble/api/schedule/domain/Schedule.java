package assemble.api.schedule.domain;

import assemble.api.club.domain.Club;
import assemble.api.global.base.BaseEntity;
import assemble.api.member.domain.mapping.MemberSchedule;
import assemble.api.schedule.dto.ScheduleRequestDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "schedule")
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private LocalDateTime startAt;

    @Column(nullable = false)
    private String location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id")
    private Club club;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL)
    @Builder.Default
    private List<MemberSchedule> memberScheduleList = new ArrayList<>();

    public void updateInfo(ScheduleRequestDTO.UpdateScheduleDTO request){
        if(request.getTitle() != null && !request.getTitle().isBlank()){
            this.title = request.getTitle();
        }
        if(request.getLocation() != null && !request.getLocation().isBlank()){
            this.location = request.getLocation();
        }
        if(request.getStartAt() != null){
            this.startAt = request.getStartAt();
        }
    }
}
