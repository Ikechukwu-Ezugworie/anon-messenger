package com.anon.message.message;

import com.anon.message.exception.NotFoundException;
import com.anon.message.user.User;
import com.anon.message.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@Slf4j
public class MessageServiceImpl implements MessageService {


    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    ModelMapper modelMapper = new ModelMapper();


    {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public MessageServiceImpl(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }


    @Override
    public MessagePojo sendMessage(String email, Message message) {
        if (email.isEmpty()) {
            log.error("email cannot be empty");
            throw new IllegalArgumentException("email cannot be empty");
        }
        Optional<User> user = userExists(email);
        if (user.isEmpty()) {
            log.error("User not found with email {}", email);
            throw new NotFoundException("User not found with email " + email);
        }
        log.info("Sending message to user {}", email);
        message.setUser(user.get());
        message.setId(UUID.randomUUID().toString());
        return modelMapper.map(messageRepository.save(message), MessagePojo.class);
    }

    @Override
    public boolean deleteMessage(String messageId) {
        log.info("Deleting message with id {}", messageId);
        if (!messageRepository.existsById(messageId)) throw new NotFoundException("Message not found");
        messageRepository.deleteById(messageId);
        return true;
    }

    @Override
    public List<Message> getUserMessages(String email) {
        log.info("Getting all messages for user {}", email);
        User userByEmail = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("No user found with email: " + email));
        return messageRepository
                .findByUser_Email(userByEmail.getEmail())
                .orElseThrow(() -> new NotFoundException("No messages found for user: " + email));
    }


    //method to check if a user exists by email
    private Optional<User> userExists(String email) {
        return userRepository.findByEmail(email);
    }

}
