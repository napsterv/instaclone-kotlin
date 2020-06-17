package io.instagram.instagram

import io.instagram.instagram.user.AppBaseUser
import io.instagram.instagram.user.UserRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(private val repository: UserRepository) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val user: AppBaseUser = repository.findByUsername(username)
        return User(user.username,
                    user.password,
                    user.authorities)
    }

}