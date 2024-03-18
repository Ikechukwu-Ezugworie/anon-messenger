package com.anon.message.message;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, String> {

    public Optional<List<Message>> findByUser_Email(String email);

}
