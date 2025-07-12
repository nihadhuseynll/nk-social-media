package com.nkcode.nksocialmedia.exception.payload;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ErrorResponse {

    LocalDateTime timestamp;
    int status;
    String error;
    String message;
    String path;
    List<String> errors;
}
