package com.anon.message.message;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MessageService {

    public MessagePojo sendMessage(String username, Message message);

    public boolean deleteMessage(String messageId);

    public List<Message> getUserMessages(String username);
}
