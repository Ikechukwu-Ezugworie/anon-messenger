package com.anon.message.message;


import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/message")
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping()
    public ResponseEntity<List<Message>> getUserMessages(@RequestParam String userEmail) {
        List<Message> messages = messageService.getUserMessages(userEmail);
        return ResponseEntity.status(HttpStatus.OK).body(messages);
    }

    @PostMapping("/send")
    @PermitAll
    public ResponseEntity<?> sendMessage(@RequestParam String userEmail, @RequestBody Message message) {
        messageService.sendMessage(userEmail, message);
        return ResponseEntity.status(HttpStatus.CREATED).body("Message sent successfully");
    }


}
