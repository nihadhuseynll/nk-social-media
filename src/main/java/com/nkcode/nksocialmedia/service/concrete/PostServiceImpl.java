package com.nkcode.nksocialmedia.service.concrete;

import com.nkcode.nksocialmedia.dao.entity.Photo;
import com.nkcode.nksocialmedia.dao.entity.Post;
import com.nkcode.nksocialmedia.dao.entity.User;
import com.nkcode.nksocialmedia.dao.repository.PhotoRepository;
import com.nkcode.nksocialmedia.dao.repository.PostRepository;
import com.nkcode.nksocialmedia.dao.repository.UserRepository;
import com.nkcode.nksocialmedia.dto.request.CreatePostRequestDto;
import com.nkcode.nksocialmedia.exception.custom.UserNotFoundException;
import com.nkcode.nksocialmedia.service.abstraction.PostService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Getter
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    @Value("${spring.file.upload-dir}")
    private String uploadDir;

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final PhotoRepository photoRepository;

    @Override
    public Post createPost(UUID userId, CreatePostRequestDto createPostRequestDto,
                           MultipartFile[] multipartFiles) throws IOException {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("The specified user does not exist or has been deleted."));

        Post post = Post.builder()
                .user(user)
                .description(createPostRequestDto.getDescription())
                .build();

        postRepository.save(post);

        for (MultipartFile multipartFile : multipartFiles) {
            String imageUrl = saveImage(multipartFile);

            Photo photo = photoRepository.save(Photo.builder()
                    .imageUrl(imageUrl)
                    .post(post)
                    .build());
            post.getPhotos().add(photo);
        }

        user.getPosts().add(post);
        return postRepository.save(post);
    }

    private String saveImage(MultipartFile file) throws IOException {
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String fileName = file.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return filePath.toString();
    }
}
