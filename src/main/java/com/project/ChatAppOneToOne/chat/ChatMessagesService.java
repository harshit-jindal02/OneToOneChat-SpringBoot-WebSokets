package com.project.ChatAppOneToOne.chat;

import com.project.ChatAppOneToOne.chatroom.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessagesService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomService chatRoomService;

    public ChatMessages save(ChatMessages chatMessages) {
        var chatId = chatRoomService.getChatRoomId(chatMessages.getSenderId(), chatMessages.getRecipientId(), true)
                .orElseThrow();
        chatMessages.setChatId(chatId);
        return chatMessageRepository.save(chatMessages);
    }

    public List<ChatMessages> findChatMessages(String senderId, String recipientId) {
        var chatId = chatRoomService.getChatRoomId(senderId, recipientId, false);
        return chatId.map(chatMessageRepository::findByChatId).orElse(new ArrayList<>());
    }
}
