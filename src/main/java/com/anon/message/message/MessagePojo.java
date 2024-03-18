package com.anon.message.message;

import com.anon.message.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessagePojo {
    private String id;
    private User userId;
    private String message;
    private LocalDateTime timestamp;
}
