package com.nkcode.nksocialmedia.service.abstraction;

import com.nkcode.nksocialmedia.dao.entity.Post;
import com.nkcode.nksocialmedia.dto.request.CreatePostRequestDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PostService {

    Post createPost(CreatePostRequestDto createPostRequestDto, MultipartFile[] multipartFile) throws IOException;
}
