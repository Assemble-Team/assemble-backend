package assemble.api.club.domain;

import assemble.api.board.domain.Board;
import assemble.api.chat.domain.Chat;
import assemble.api.club.domain.enums.ClubStatus;
import assemble.api.club.domain.enums.DifficultyLevel;
import assemble.api.club.domain.mapping.MemberClub;
import assemble.api.club.dto.ClubRequestDTO;
import assemble.api.global.base.BaseEntity;
import assemble.api.member.domain.enums.InterestCategory;
import assemble.api.member.domain.mapping.ClubJoinRequest;
import assemble.api.member.domain.mapping.MemberLikesClub;
import assemble.api.schedule.domain.Schedule;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "club")
public class Club extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    private String name;

    @Column(nullable = false)
    private String region;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DifficultyLevel level;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ClubStatus status;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private InterestCategory interestCategory;

    @Column(nullable = false, length = 255)
    private String description;

    @Builder.Default
    private Long curNumbers = 1L;

    private Long maxNumbers;

    @Builder.Default
    private Long likesCount = 0L;

    private String imageUrl;

    @OneToOne(mappedBy = "club")
    private Chat chat;

    @OneToMany(mappedBy = "club")
    @Builder.Default
    private List<Board> boardList = new ArrayList<>();

    @OneToMany(mappedBy = "club")
    @Builder.Default
    private List<MemberLikesClub> memberLikesClubList = new ArrayList<>();

    @OneToMany(mappedBy = "club")
    @Builder.Default
    private List<MemberClub> memberClubList = new ArrayList<>();

    @OneToMany(mappedBy = "club")
    @Builder.Default
    private List<ClubJoinRequest> clubJoinRequestList = new ArrayList<>();

    @OneToMany(mappedBy = "club")
    @Builder.Default
    private List<Schedule>  scheduleList = new ArrayList<>();

    public void updateInfo(ClubRequestDTO.UpdateClubDTO request) {
        if(request.getName() != null){
            this.name = request.getName();
        }
        if(request.getDescription() != null){
            this.description = request.getDescription();
        }
        if(request.getImageUrl() != null){
            this.imageUrl = request.getImageUrl();
        }
    }

}
