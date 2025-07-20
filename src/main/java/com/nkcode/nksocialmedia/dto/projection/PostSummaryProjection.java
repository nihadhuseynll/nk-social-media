package com.nkcode.nksocialmedia.dto.projection;

import java.time.LocalDateTime;

public interface PostSummaryProjection {

    String getDescription();

    LocalDateTime getCreatedDate();

    String getUserName();

    int getCommentCount();

    int getReactionCount();
}
