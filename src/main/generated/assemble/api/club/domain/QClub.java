package assemble.api.club.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QClub is a Querydsl query type for Club
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QClub extends EntityPathBase<Club> {

    private static final long serialVersionUID = -1564767624L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QClub club = new QClub("club");

    public final assemble.api.global.base.QBaseEntity _super = new assemble.api.global.base.QBaseEntity(this);

    public final ListPath<assemble.api.board.domain.Board, assemble.api.board.domain.QBoard> boardList = this.<assemble.api.board.domain.Board, assemble.api.board.domain.QBoard>createList("boardList", assemble.api.board.domain.Board.class, assemble.api.board.domain.QBoard.class, PathInits.DIRECT2);

    public final assemble.api.chat.domain.QChat chat;

    public final ListPath<assemble.api.member.domain.mapping.ClubJoinRequest, assemble.api.member.domain.mapping.QClubJoinRequest> clubJoinRequestList = this.<assemble.api.member.domain.mapping.ClubJoinRequest, assemble.api.member.domain.mapping.QClubJoinRequest>createList("clubJoinRequestList", assemble.api.member.domain.mapping.ClubJoinRequest.class, assemble.api.member.domain.mapping.QClubJoinRequest.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> curNumbers = createNumber("curNumbers", Long.class);

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imageUrl = createString("imageUrl");

    public final EnumPath<assemble.api.member.domain.enums.InterestCategory> interestCategory = createEnum("interestCategory", assemble.api.member.domain.enums.InterestCategory.class);

    public final EnumPath<assemble.api.club.domain.enums.DifficultyLevel> level = createEnum("level", assemble.api.club.domain.enums.DifficultyLevel.class);

    public final NumberPath<Long> likesCount = createNumber("likesCount", Long.class);

    public final NumberPath<Long> maxNumbers = createNumber("maxNumbers", Long.class);

    public final ListPath<assemble.api.club.domain.mapping.MemberClub, assemble.api.club.domain.mapping.QMemberClub> memberClubList = this.<assemble.api.club.domain.mapping.MemberClub, assemble.api.club.domain.mapping.QMemberClub>createList("memberClubList", assemble.api.club.domain.mapping.MemberClub.class, assemble.api.club.domain.mapping.QMemberClub.class, PathInits.DIRECT2);

    public final ListPath<assemble.api.member.domain.mapping.MemberLikesClub, assemble.api.member.domain.mapping.QMemberLikesClub> memberLikesClubList = this.<assemble.api.member.domain.mapping.MemberLikesClub, assemble.api.member.domain.mapping.QMemberLikesClub>createList("memberLikesClubList", assemble.api.member.domain.mapping.MemberLikesClub.class, assemble.api.member.domain.mapping.QMemberLikesClub.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final ListPath<assemble.api.notice.domain.Notice, assemble.api.notice.domain.QNotice> noticeList = this.<assemble.api.notice.domain.Notice, assemble.api.notice.domain.QNotice>createList("noticeList", assemble.api.notice.domain.Notice.class, assemble.api.notice.domain.QNotice.class, PathInits.DIRECT2);

    public final StringPath region = createString("region");

    public final ListPath<assemble.api.schedule.domain.Schedule, assemble.api.schedule.domain.QSchedule> scheduleList = this.<assemble.api.schedule.domain.Schedule, assemble.api.schedule.domain.QSchedule>createList("scheduleList", assemble.api.schedule.domain.Schedule.class, assemble.api.schedule.domain.QSchedule.class, PathInits.DIRECT2);

    public final EnumPath<assemble.api.club.domain.enums.ClubStatus> status = createEnum("status", assemble.api.club.domain.enums.ClubStatus.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QClub(String variable) {
        this(Club.class, forVariable(variable), INITS);
    }

    public QClub(Path<? extends Club> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QClub(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QClub(PathMetadata metadata, PathInits inits) {
        this(Club.class, metadata, inits);
    }

    public QClub(Class<? extends Club> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.chat = inits.isInitialized("chat") ? new assemble.api.chat.domain.QChat(forProperty("chat"), inits.get("chat")) : null;
    }

}

