package io.instagram.instagram.user

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<AppBaseUser, Int> {
    fun findByUsername(username: String): AppBaseUser

}