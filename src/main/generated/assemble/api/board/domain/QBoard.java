package assemble.api.board.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoard is a Querydsl query type for Board
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoard extends EntityPathBase<Board> {

    private static final long serialVersionUID = 358267668L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoard board = new QBoard("board");

    public final assemble.api.global.base.QBaseEntity _super = new assemble.api.global.base.QBaseEntity(this);

    public final assemble.api.club.domain.QClub club;

    public final ListPath<assemble.api.comment.domain.Comment, assemble.api.comment.domain.QComment> commentList = this.<assemble.api.comment.domain.Comment, assemble.api.comment.domain.QComment>createList("commentList", assemble.api.comment.domain.Comment.class, assemble.api.comment.domain.QComment.class, PathInits.DIRECT2);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<assemble.api.likes.domain.Likes, assemble.api.likes.domain.QLikes> likesList = this.<assemble.api.likes.domain.Likes, assemble.api.likes.domain.QLikes>createList("likesList", assemble.api.likes.domain.Likes.class, assemble.api.likes.domain.QLikes.class, PathInits.DIRECT2);

    public final assemble.api.member.domain.QMember member;

    public final StringPath title = createString("title");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QBoard(String variable) {
        this(Board.class, forVariable(variable), INITS);
    }

    public QBoard(Path<? extends Board> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoard(PathMetadata metadata, PathInits inits) {
        this(Board.class, metadata, inits);
    }

    public QBoard(Class<? extends Board> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.club = inits.isInitialized("club") ? new assemble.api.club.domain.QClub(forProperty("club"), inits.get("club")) : null;
        this.member = inits.isInitialized("member") ? new assemble.api.member.domain.QMember(forProperty("member")) : null;
    }

}

