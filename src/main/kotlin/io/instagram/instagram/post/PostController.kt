package io.instagram.instagram.post

import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("posts")
class PostController(val repository: PostRepository, val postDTO: PostService) {

    @GetMapping
    fun getPosts(): List<Post> {
        return repository.findAll()
    }

    @PostMapping
    fun createPost(@RequestParam("file") files: List<MultipartFile>, @RequestParam("caption") caption: String): Post {
        return postDTO.save(files, caption)
    }
}