package assemble.api.chatMessage.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QChatMessage is a Querydsl query type for ChatMessage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QChatMessage extends EntityPathBase<ChatMessage> {

    private static final long serialVersionUID = 1525019124L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QChatMessage chatMessage = new QChatMessage("chatMessage");

    public final assemble.api.global.base.QBaseEntity _super = new assemble.api.global.base.QBaseEntity(this);

    public final assemble.api.chat.domain.QChat chat;

    public final ListPath<assemble.api.chat.domain.mapping.ChatRead, assemble.api.chat.domain.mapping.QChatRead> chatReadList = this.<assemble.api.chat.domain.mapping.ChatRead, assemble.api.chat.domain.mapping.QChatRead>createList("chatReadList", assemble.api.chat.domain.mapping.ChatRead.class, assemble.api.chat.domain.mapping.QChatRead.class, PathInits.DIRECT2);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final assemble.api.member.domain.QMember member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QChatMessage(String variable) {
        this(ChatMessage.class, forVariable(variable), INITS);
    }

    public QChatMessage(Path<? extends ChatMessage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QChatMessage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QChatMessage(PathMetadata metadata, PathInits inits) {
        this(ChatMessage.class, metadata, inits);
    }

    public QChatMessage(Class<? extends ChatMessage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.chat = inits.isInitialized("chat") ? new assemble.api.chat.domain.QChat(forProperty("chat"), inits.get("chat")) : null;
        this.member = inits.isInitialized("member") ? new assemble.api.member.domain.QMember(forProperty("member")) : null;
    }

}

