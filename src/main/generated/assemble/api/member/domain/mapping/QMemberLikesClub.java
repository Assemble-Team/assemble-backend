package assemble.api.member.domain.mapping;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberLikesClub is a Querydsl query type for MemberLikesClub
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberLikesClub extends EntityPathBase<MemberLikesClub> {

    private static final long serialVersionUID = -1168636782L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberLikesClub memberLikesClub = new QMemberLikesClub("memberLikesClub");

    public final assemble.api.global.base.QBaseEntity _super = new assemble.api.global.base.QBaseEntity(this);

    public final assemble.api.club.domain.QClub club;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final assemble.api.member.domain.QMember member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QMemberLikesClub(String variable) {
        this(MemberLikesClub.class, forVariable(variable), INITS);
    }

    public QMemberLikesClub(Path<? extends MemberLikesClub> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberLikesClub(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberLikesClub(PathMetadata metadata, PathInits inits) {
        this(MemberLikesClub.class, metadata, inits);
    }

    public QMemberLikesClub(Class<? extends MemberLikesClub> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.club = inits.isInitialized("club") ? new assemble.api.club.domain.QClub(forProperty("club"), inits.get("club")) : null;
        this.member = inits.isInitialized("member") ? new assemble.api.member.domain.QMember(forProperty("member")) : null;
    }

}

