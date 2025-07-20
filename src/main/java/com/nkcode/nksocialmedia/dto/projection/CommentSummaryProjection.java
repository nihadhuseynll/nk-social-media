package com.nkcode.nksocialmedia.dto.projection;

import java.time.LocalDateTime;
import java.util.UUID;

public interface CommentSummaryProjection {

    String getUserName();

    LocalDateTime getCreatedDate();

    String getDescription();

    UUID getParentCommentId();

    int getReactionCount();

    int getReplyCount();
}
