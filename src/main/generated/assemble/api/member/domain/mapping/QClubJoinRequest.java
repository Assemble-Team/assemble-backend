package assemble.api.member.domain.mapping;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QClubJoinRequest is a Querydsl query type for ClubJoinRequest
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QClubJoinRequest extends EntityPathBase<ClubJoinRequest> {

    private static final long serialVersionUID = -258440471L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QClubJoinRequest clubJoinRequest = new QClubJoinRequest("clubJoinRequest");

    public final assemble.api.global.base.QBaseEntity _super = new assemble.api.global.base.QBaseEntity(this);

    public final assemble.api.club.domain.QClub club;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final assemble.api.member.domain.QMember member;

    public final StringPath message = createString("message");

    public final EnumPath<assemble.api.member.domain.enums.JoinStatus> status = createEnum("status", assemble.api.member.domain.enums.JoinStatus.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QClubJoinRequest(String variable) {
        this(ClubJoinRequest.class, forVariable(variable), INITS);
    }

    public QClubJoinRequest(Path<? extends ClubJoinRequest> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QClubJoinRequest(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QClubJoinRequest(PathMetadata metadata, PathInits inits) {
        this(ClubJoinRequest.class, metadata, inits);
    }

    public QClubJoinRequest(Class<? extends ClubJoinRequest> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.club = inits.isInitialized("club") ? new assemble.api.club.domain.QClub(forProperty("club"), inits.get("club")) : null;
        this.member = inits.isInitialized("member") ? new assemble.api.member.domain.QMember(forProperty("member")) : null;
    }

}

