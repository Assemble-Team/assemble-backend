package assemble.api.chat.domain;

import assemble.api.chat.domain.mapping.ChatRead;
import assemble.api.chatMessage.domain.ChatMessage;
import assemble.api.club.domain.Club;
import assemble.api.common.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "chat")
public class Chat extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id")
    private Club club;

    @OneToMany(mappedBy = "chat")
    @Builder.Default
    private List<ChatRead> chatReadList = new ArrayList<>();

    @OneToMany(mappedBy = "chat")
    @Builder.Default
    private List<ChatMessage> chatMessageList = new ArrayList<>();
}
