package com.tutorials.flightyauthms.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GenerateJwtRqModel {

    @NotBlank(message = "Username must be provided")
    String username;

    Boolean rememberMe;
}
