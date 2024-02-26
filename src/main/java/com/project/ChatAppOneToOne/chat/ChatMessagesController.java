package com.project.ChatAppOneToOne.chat;

import com.project.ChatAppOneToOne.chatroom.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatMessagesController {

    private final ChatMessagesService chatMessagesService;

    @GetMapping("/messages/{senderId}/{recipientId}")
    public ResponseEntity<List<ChatMessages>> getMessages(
            @PathVariable("senderId") String senderId, @PathVariable("recipientId") String recipientId) {
        return ResponseEntity.ok(chatMessagesService.findChatMessages(senderId, recipientId));
    }

    
}
