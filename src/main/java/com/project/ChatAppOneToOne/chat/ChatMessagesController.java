package com.project.ChatAppOneToOne.chat;

import com.project.ChatAppOneToOne.chatroom.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatMessagesController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMessagesService chatMessagesService;

    @GetMapping("/messages/{senderId}/{recipientId}")
    public ResponseEntity<List<ChatMessages>> getMessages(
            @PathVariable("senderId") String senderId, @PathVariable("recipientId") String recipientId) {
        return ResponseEntity.ok(chatMessagesService.findChatMessages(senderId, recipientId));
    }

    @MessageMapping("/chat")
    public void processChatMessages(@Payload ChatMessages chatMessages) {
        ChatMessages savedMessages = chatMessagesService.save(chatMessages);
        messagingTemplate.convertAndSendToUser(chatMessages.getRecipientId(), "/queue/messages",
                ChatNotification.builder()
                        .id(savedMessages.getChatId())
                        .senderId(chatMessages.getSenderId())
                        .recipientId(chatMessages.getRecipientId())
                        .content(chatMessages.getContent())
                        .build());
    }
}
