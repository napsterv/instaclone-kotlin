package io.instagram.instagram.user

import io.instagram.instagram.auth.JwtUtil
import io.instagram.instagram.auth.LoginRequest
import io.instagram.instagram.jwt.TokenResponse
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.InternalAuthenticationServiceException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(private val authenticationManager: AuthenticationManager,
                     private val jwtUtil: JwtUtil,
                     private val bCryptPasswordEncoder: BCryptPasswordEncoder,
                     private val repository: UserRepository) {

    @PostMapping("signup")
    fun signUp(@RequestBody user: AppBaseUser): AppBaseUser {
        user.password = bCryptPasswordEncoder.encode(user.password)
        return repository.save(user)
    }

    @PostMapping("login")
    @Throws(BadCredentialsException::class,
            InternalAuthenticationServiceException::class)
    fun loginUser(@RequestBody loginRequest: LoginRequest): ResponseEntity<TokenResponse> {
        try {
            authenticationManager.authenticate(UsernamePasswordAuthenticationToken(loginRequest.username,
                    loginRequest.password))
        } catch (e: InternalAuthenticationServiceException) {
            throw InternalAuthenticationServiceException("No such user with this username.")
        } catch (e: BadCredentialsException) {
            throw BadCredentialsException("Username or Password is incorrect")
        }
        val user: AppBaseUser = repository.findByUsername(loginRequest.username)
        return ResponseEntity.ok(jwtUtil.generateAuthToken(user))
    }

    @GetMapping("users")
    fun getUsers(): MutableList<AppBaseUser> {
        return repository.findAll()
    }
}