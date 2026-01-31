package assemble.api.chat.domain.mapping;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QChatRead is a Querydsl query type for ChatRead
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QChatRead extends EntityPathBase<ChatRead> {

    private static final long serialVersionUID = 1126494610L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QChatRead chatRead = new QChatRead("chatRead");

    public final assemble.api.global.base.QBaseEntity _super = new assemble.api.global.base.QBaseEntity(this);

    public final assemble.api.chat.domain.QChat chat;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final assemble.api.chatMessage.domain.QChatMessage lastReadMessage;

    public final assemble.api.member.domain.QMember member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QChatRead(String variable) {
        this(ChatRead.class, forVariable(variable), INITS);
    }

    public QChatRead(Path<? extends ChatRead> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QChatRead(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QChatRead(PathMetadata metadata, PathInits inits) {
        this(ChatRead.class, metadata, inits);
    }

    public QChatRead(Class<? extends ChatRead> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.chat = inits.isInitialized("chat") ? new assemble.api.chat.domain.QChat(forProperty("chat"), inits.get("chat")) : null;
        this.lastReadMessage = inits.isInitialized("lastReadMessage") ? new assemble.api.chatMessage.domain.QChatMessage(forProperty("lastReadMessage"), inits.get("lastReadMessage")) : null;
        this.member = inits.isInitialized("member") ? new assemble.api.member.domain.QMember(forProperty("member")) : null;
    }

}

