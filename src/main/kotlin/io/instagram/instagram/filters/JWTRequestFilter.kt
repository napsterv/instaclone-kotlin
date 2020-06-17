package io.instagram.instagram.filters

import io.instagram.instagram.UserDetailsServiceImpl
import io.instagram.instagram.auth.JwtUtil
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JWTRequestFilter(private val userDetailsServiceImpl: UserDetailsServiceImpl,
                       private val jwtUtil: JwtUtil) : OncePerRequestFilter() {
    @Throws(BadCredentialsException::class)
    override fun doFilterInternal(request: HttpServletRequest,
                                  response: HttpServletResponse,
                                  chain: FilterChain) {
        val header: String? = request.getHeader("Authorization")
        val username: String
        val jwt: String


        if (!header.isNullOrEmpty() && header.startsWith("Bearer")) {
            jwt = header.substring(7)
            username = jwtUtil.extractUsername(jwt)
            if (!username.isBlank() && SecurityContextHolder.getContext().authentication == null) {
                val userDetails = userDetailsServiceImpl.loadUserByUsername(username)
                val authenticationToken = UsernamePasswordAuthenticationToken(userDetails,
                        null,
                        userDetails.authorities)

                authenticationToken.details = WebAuthenticationDetailsSource().buildDetails(request)

                SecurityContextHolder.getContext().authentication = authenticationToken
            }
        }
        chain.doFilter(request, response)

    }
}
