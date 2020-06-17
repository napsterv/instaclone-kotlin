package io.instagram.instagram.utils

import io.instagram.instagram.user.AppBaseUser
import io.instagram.instagram.user.UserRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import java.io.File

@Component
class Utilities(val userRepository: UserRepository) {

    fun getUploadDirectory(): String {
        val path = System.getProperty("user.dir").plus("/files/")
        getOrCreateDirectory(File(path))
        return path
    }

    private fun getOrCreateDirectory(path: File): File {
        if (!path.exists()) path.mkdir()
        return path
    }

    fun getCurrentUser(): AppBaseUser {
        return userRepository.findByUsername(SecurityContextHolder.getContext().authentication.name)
    }
}