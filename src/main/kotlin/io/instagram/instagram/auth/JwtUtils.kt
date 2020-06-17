package io.instagram.instagram.auth

import io.instagram.instagram.jwt.Token
import io.instagram.instagram.jwt.TokenResponse
import io.instagram.instagram.user.AppBaseUser
import io.instagram.instagram.user.UserRepository
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Service
import java.sql.Date
import java.time.LocalDate


@Service
class JwtUtil(private val appBaseUserRepository: UserRepository) {

    private val secret: String = "mZq3t6w9z\$C&F)J@NcRfUjXn2r5u7x!A%D*G-KaPdSgVkYp3s6v9y/B?E(H+MbQe"

    fun generateAuthToken(user: AppBaseUser): TokenResponse {
        val claims: Map<String, Any?> = getClaims(user)
        return createToken(claims, user)
    }

    private fun createToken(claims: Map<String, Any?>, user: AppBaseUser): TokenResponse {
        val token: String = Jwts.builder()
                .setClaims(claims)
                .setSubject(user.username)
                .setIssuedAt(Date.valueOf(LocalDate.now()))
                .setExpiration(Date.valueOf(LocalDate.now()
                        .plusWeeks(1)))
                .signWith(Keys.hmacShaKeyFor(secret.toByteArray()))
                .setHeader(getHeaders())
                .compact()
        return TokenResponse(Token(token,
                token), user)
    }

    private fun getHeaders(): Map<String, String> {
        return mapOf("typ" to "JWT")
    }

    private fun getClaims(user: AppBaseUser): Map<String, Any?> {
        return mapOf<String, Any?>("user_id" to user.id)
    }

    private fun getClaims(token: String): Claims {
        return Jwts.parserBuilder()
                .setSigningKey(secret.toByteArray())
                .build()
                .parseClaimsJws(token).body
    }

    fun extractUsername(token: String): String {
        val claims = getClaims(token)
        return claims.subject


    }
}