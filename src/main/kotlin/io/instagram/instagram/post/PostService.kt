package io.instagram.instagram.post

import io.instagram.instagram.user.UserRepository
import io.instagram.instagram.utils.Utilities
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.security.Principal

@Service
class PostService(val postRepository: PostRepository, val utils: Utilities, val userRepository: UserRepository) {
    fun save(files: List<MultipartFile>, caption: String): Post {
        val user = utils.getCurrentUser()
        val requestUser: Principal = SecurityContextHolder.getContext().authentication
        print(requestUser.name)
        val post = Post(caption, user)

        val uploadDirectory: String = utils.getUploadDirectory()
        for (f in files) {
            File(uploadDirectory.plus(f.originalFilename)).createNewFile()
            post.postMediaList.add(PostMedia(f.originalFilename, post))
        }
        return postRepository.save(post)
    }

}