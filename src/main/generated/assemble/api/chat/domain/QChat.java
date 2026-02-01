package assemble.api.chat.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QChat is a Querydsl query type for Chat
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QChat extends EntityPathBase<Chat> {

    private static final long serialVersionUID = 1529906364L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QChat chat = new QChat("chat");

    public final assemble.api.global.base.QBaseEntity _super = new assemble.api.global.base.QBaseEntity(this);

    public final ListPath<assemble.api.chatMessage.domain.ChatMessage, assemble.api.chatMessage.domain.QChatMessage> chatMessageList = this.<assemble.api.chatMessage.domain.ChatMessage, assemble.api.chatMessage.domain.QChatMessage>createList("chatMessageList", assemble.api.chatMessage.domain.ChatMessage.class, assemble.api.chatMessage.domain.QChatMessage.class, PathInits.DIRECT2);

    public final ListPath<assemble.api.chat.domain.mapping.ChatRead, assemble.api.chat.domain.mapping.QChatRead> chatReadList = this.<assemble.api.chat.domain.mapping.ChatRead, assemble.api.chat.domain.mapping.QChatRead>createList("chatReadList", assemble.api.chat.domain.mapping.ChatRead.class, assemble.api.chat.domain.mapping.QChatRead.class, PathInits.DIRECT2);

    public final assemble.api.club.domain.QClub club;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QChat(String variable) {
        this(Chat.class, forVariable(variable), INITS);
    }

    public QChat(Path<? extends Chat> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QChat(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QChat(PathMetadata metadata, PathInits inits) {
        this(Chat.class, metadata, inits);
    }

    public QChat(Class<? extends Chat> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.club = inits.isInitialized("club") ? new assemble.api.club.domain.QClub(forProperty("club"), inits.get("club")) : null;
    }

}

