package com.nkcode.nksocialmedia.service.abstraction;

import com.nkcode.nksocialmedia.dao.entity.Post;
import com.nkcode.nksocialmedia.dto.request.CreatePostRequestDto;
import com.nkcode.nksocialmedia.dto.response.PostResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface PostService {

    Post createPost(UUID userId, CreatePostRequestDto createPostRequestDto,
                    MultipartFile[] multipartFile) throws IOException;

    List<PostResponseDto> getPosts() throws IOException;
}
