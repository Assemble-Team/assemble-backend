package assemble.api.member.domain;

import assemble.api.board.domain.Board;
import assemble.api.chat.domain.mapping.ChatRead;
import assemble.api.chatMessage.domain.ChatMessage;
import assemble.api.comment.domain.Comment;
import assemble.api.global.base.BaseEntity;
import assemble.api.likes.domain.Likes;
import assemble.api.member.domain.enums.InterestCategory;
import assemble.api.member.domain.mapping.ClubJoinRequest;
import assemble.api.club.domain.mapping.MemberClub;
import assemble.api.member.domain.mapping.MemberLikesClub;
import assemble.api.member.domain.mapping.MemberSchedule;
import assemble.api.notification.domain.Notification;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 30)
    private String email;

    @Column(nullable = false, length = 30)
    private String username;

    @Column(nullable = false)
    private String password;

    private String description;

    private String url;

    @ElementCollection(targetClass = InterestCategory.class)
    @CollectionTable(
            name = "member_interest_category",
            joinColumns = @JoinColumn(name = "member_id")
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    @Builder.Default
    private Set<InterestCategory> categories = new HashSet<>();

    @Column(nullable = false)
    @Builder.Default
    private boolean pushEnabled = true;

    @Column(nullable = false)
    @Builder.Default
    private boolean chatEnabled = true;

    @Column(nullable = false)
    @Builder.Default
    private boolean scheduleReminderEnabled = true;

    @OneToMany(mappedBy = "member")
    @Builder.Default
    private List<Board> boardList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    @Builder.Default
    private List<ChatRead> chatReadList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    @Builder.Default
    private List<ChatMessage> chatMessageList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    @Builder.Default
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    @Builder.Default
    private List<Likes> likesList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    @Builder.Default
    private List<MemberLikesClub> memberLikesClubList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    @Builder.Default
    private List<MemberClub> memberClubList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    @Builder.Default
    private List<ClubJoinRequest> clubJoinRequestList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    @Builder.Default
    private List<Notification> notificationList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    @Builder.Default
    private List<MemberSchedule> memberScheduleList = new ArrayList<>();
}
