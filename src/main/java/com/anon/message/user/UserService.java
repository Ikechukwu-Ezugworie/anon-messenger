package com.anon.message.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {


    @Value("${application.base-url}")
    private String baseUrl;

    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;


    public void changePassword(ChangePasswordRequest request, Principal connectedUser) {

        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        // check if the current password is correct
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new IllegalStateException("Wrong password");
        }
        // check if the two new passwords are the same
        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new IllegalStateException("Password are not the same");
        }

        // update the password
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        // save the new password
        repository.save(user);
    }

    /**
     * Get user url
     */
    public String getUserMessageUrl(String email) {
        log.info("Retrieving user by email: {}", email);
        Optional<User> user = repository.findByEmail(email);
        if (user.isEmpty()) {
            log.info("User with email: {} not found", email);
            throw new IllegalStateException("User not found");
        }
        return user.get().getMessageUrl();
    }

}
