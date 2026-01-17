package assemble.api.member.domain;

import assemble.api.board.domain.Board;
import assemble.api.chat.domain.mapping.ChatRead;
import assemble.api.chatMessage.domain.ChatMessage;
import assemble.api.comment.domain.Comment;
import assemble.api.common.base.BaseEntity;
import assemble.api.likes.domain.Likes;
import assemble.api.member.domain.enums.InterestCategory;
import assemble.api.member.domain.mapping.ClubJoinRequest;
import assemble.api.member.domain.mapping.MemberClub;
import assemble.api.member.domain.mapping.MemberLikesClub;
import assemble.api.member.domain.mapping.MemberSchedule;
import assemble.api.notification.domain.Notification;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    @Column(nullable = false, length = 30)
    private String username;

    @Column(nullable = false, length = 255)
    private String description;

    private String url;

    @Enumerated(EnumType.STRING)
    private InterestCategory category;

    private boolean pushEnabled;

    private boolean chatEnabled;

    private boolean scheduleReminderEnabled;

    @OneToMany(mappedBy = "member")
    private List<Board> boardList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<ChatRead> chatReadList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<ChatMessage> chatMessageList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Likes> likesList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<MemberLikesClub> memberLikesClubList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<MemberClub> memberClubList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<ClubJoinRequest> clubJoinRequestList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Notification> notificationList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<MemberSchedule> memberScheduleList = new ArrayList<>();
}
