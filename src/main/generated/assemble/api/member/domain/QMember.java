package assemble.api.member.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = 998740672L;

    public static final QMember member = new QMember("member1");

    public final assemble.api.global.base.QBaseEntity _super = new assemble.api.global.base.QBaseEntity(this);

    public final ListPath<assemble.api.board.domain.Board, assemble.api.board.domain.QBoard> boardList = this.<assemble.api.board.domain.Board, assemble.api.board.domain.QBoard>createList("boardList", assemble.api.board.domain.Board.class, assemble.api.board.domain.QBoard.class, PathInits.DIRECT2);

    public final SetPath<assemble.api.member.domain.enums.InterestCategory, EnumPath<assemble.api.member.domain.enums.InterestCategory>> categories = this.<assemble.api.member.domain.enums.InterestCategory, EnumPath<assemble.api.member.domain.enums.InterestCategory>>createSet("categories", assemble.api.member.domain.enums.InterestCategory.class, EnumPath.class, PathInits.DIRECT2);

    public final BooleanPath chatEnabled = createBoolean("chatEnabled");

    public final ListPath<assemble.api.chatMessage.domain.ChatMessage, assemble.api.chatMessage.domain.QChatMessage> chatMessageList = this.<assemble.api.chatMessage.domain.ChatMessage, assemble.api.chatMessage.domain.QChatMessage>createList("chatMessageList", assemble.api.chatMessage.domain.ChatMessage.class, assemble.api.chatMessage.domain.QChatMessage.class, PathInits.DIRECT2);

    public final ListPath<assemble.api.chat.domain.mapping.ChatRead, assemble.api.chat.domain.mapping.QChatRead> chatReadList = this.<assemble.api.chat.domain.mapping.ChatRead, assemble.api.chat.domain.mapping.QChatRead>createList("chatReadList", assemble.api.chat.domain.mapping.ChatRead.class, assemble.api.chat.domain.mapping.QChatRead.class, PathInits.DIRECT2);

    public final ListPath<assemble.api.member.domain.mapping.ClubJoinRequest, assemble.api.member.domain.mapping.QClubJoinRequest> clubJoinRequestList = this.<assemble.api.member.domain.mapping.ClubJoinRequest, assemble.api.member.domain.mapping.QClubJoinRequest>createList("clubJoinRequestList", assemble.api.member.domain.mapping.ClubJoinRequest.class, assemble.api.member.domain.mapping.QClubJoinRequest.class, PathInits.DIRECT2);

    public final ListPath<assemble.api.comment.domain.Comment, assemble.api.comment.domain.QComment> commentList = this.<assemble.api.comment.domain.Comment, assemble.api.comment.domain.QComment>createList("commentList", assemble.api.comment.domain.Comment.class, assemble.api.comment.domain.QComment.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath description = createString("description");

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<assemble.api.likes.domain.Likes, assemble.api.likes.domain.QLikes> likesList = this.<assemble.api.likes.domain.Likes, assemble.api.likes.domain.QLikes>createList("likesList", assemble.api.likes.domain.Likes.class, assemble.api.likes.domain.QLikes.class, PathInits.DIRECT2);

    public final ListPath<assemble.api.club.domain.mapping.MemberClub, assemble.api.club.domain.mapping.QMemberClub> memberClubList = this.<assemble.api.club.domain.mapping.MemberClub, assemble.api.club.domain.mapping.QMemberClub>createList("memberClubList", assemble.api.club.domain.mapping.MemberClub.class, assemble.api.club.domain.mapping.QMemberClub.class, PathInits.DIRECT2);

    public final ListPath<assemble.api.member.domain.mapping.MemberLikesClub, assemble.api.member.domain.mapping.QMemberLikesClub> memberLikesClubList = this.<assemble.api.member.domain.mapping.MemberLikesClub, assemble.api.member.domain.mapping.QMemberLikesClub>createList("memberLikesClubList", assemble.api.member.domain.mapping.MemberLikesClub.class, assemble.api.member.domain.mapping.QMemberLikesClub.class, PathInits.DIRECT2);

    public final ListPath<assemble.api.member.domain.mapping.MemberSchedule, assemble.api.member.domain.mapping.QMemberSchedule> memberScheduleList = this.<assemble.api.member.domain.mapping.MemberSchedule, assemble.api.member.domain.mapping.QMemberSchedule>createList("memberScheduleList", assemble.api.member.domain.mapping.MemberSchedule.class, assemble.api.member.domain.mapping.QMemberSchedule.class, PathInits.DIRECT2);

    public final ListPath<assemble.api.notification.domain.Notification, assemble.api.notification.domain.QNotification> notificationList = this.<assemble.api.notification.domain.Notification, assemble.api.notification.domain.QNotification>createList("notificationList", assemble.api.notification.domain.Notification.class, assemble.api.notification.domain.QNotification.class, PathInits.DIRECT2);

    public final StringPath password = createString("password");

    public final BooleanPath pushEnabled = createBoolean("pushEnabled");

    public final BooleanPath scheduleReminderEnabled = createBoolean("scheduleReminderEnabled");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final StringPath url = createString("url");

    public final StringPath username = createString("username");

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

