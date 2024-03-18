package com.anon.message.auth;

import com.anon.message.user.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

  private String firstname;
  private String lastname;
  private String email;
  private String password;
  private Role role;

  @Nullable
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private String messageUrl;
}
