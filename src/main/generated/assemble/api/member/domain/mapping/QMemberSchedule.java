package assemble.api.member.domain.mapping;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberSchedule is a Querydsl query type for MemberSchedule
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberSchedule extends EntityPathBase<MemberSchedule> {

    private static final long serialVersionUID = -1977815913L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberSchedule memberSchedule = new QMemberSchedule("memberSchedule");

    public final assemble.api.global.base.QBaseEntity _super = new assemble.api.global.base.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final assemble.api.member.domain.QMember member;

    public final assemble.api.schedule.domain.QSchedule schedule;

    public final EnumPath<assemble.api.member.domain.enums.ScheduleStatus> status = createEnum("status", assemble.api.member.domain.enums.ScheduleStatus.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QMemberSchedule(String variable) {
        this(MemberSchedule.class, forVariable(variable), INITS);
    }

    public QMemberSchedule(Path<? extends MemberSchedule> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberSchedule(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberSchedule(PathMetadata metadata, PathInits inits) {
        this(MemberSchedule.class, metadata, inits);
    }

    public QMemberSchedule(Class<? extends MemberSchedule> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new assemble.api.member.domain.QMember(forProperty("member")) : null;
        this.schedule = inits.isInitialized("schedule") ? new assemble.api.schedule.domain.QSchedule(forProperty("schedule"), inits.get("schedule")) : null;
    }

}

