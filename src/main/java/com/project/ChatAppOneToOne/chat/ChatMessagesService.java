package com.project.ChatAppOneToOne.chat;

import com.project.ChatAppOneToOne.chatroom.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatMessagesService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomService chatRoomService;

    public ChatMessages save(ChatMessages chatMessages) {}
}
