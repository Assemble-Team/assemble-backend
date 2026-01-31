package assemble.api.club.domain.mapping;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberClub is a Querydsl query type for MemberClub
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberClub extends EntityPathBase<MemberClub> {

    private static final long serialVersionUID = -1860916782L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberClub memberClub = new QMemberClub("memberClub");

    public final assemble.api.global.base.QBaseEntity _super = new assemble.api.global.base.QBaseEntity(this);

    public final assemble.api.club.domain.QClub club;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final assemble.api.member.domain.QMember member;

    public final EnumPath<assemble.api.member.domain.enums.Role> role = createEnum("role", assemble.api.member.domain.enums.Role.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QMemberClub(String variable) {
        this(MemberClub.class, forVariable(variable), INITS);
    }

    public QMemberClub(Path<? extends MemberClub> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberClub(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberClub(PathMetadata metadata, PathInits inits) {
        this(MemberClub.class, metadata, inits);
    }

    public QMemberClub(Class<? extends MemberClub> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.club = inits.isInitialized("club") ? new assemble.api.club.domain.QClub(forProperty("club"), inits.get("club")) : null;
        this.member = inits.isInitialized("member") ? new assemble.api.member.domain.QMember(forProperty("member")) : null;
    }

}

