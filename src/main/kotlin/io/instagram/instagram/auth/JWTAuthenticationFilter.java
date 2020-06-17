//package io.instagram.instagram.auth;
//
//import io.instagram.instagram.jwt.Access;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.sql.Date;
//import java.time.LocalDate;
//
//public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//    private final AuthenticationManager authenticationManager;
//    private final AuthenticationRequest authenticationRequest;
//
//    public JWTAuthenticationFilter(AuthenticationManager authenticationManager,
//                                   AuthenticationRequest authenticationRequest) {
//        this.authenticationManager = authenticationManager;
//        this.authenticationRequest = authenticationRequest;
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request,
//                                                HttpServletResponse response) throws AuthenticationException {
//
//        Authentication authentication = new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
//                                                                                authenticationRequest.getPassword());
//
//        return authenticationManager.authenticate(authentication);
//    }
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request,
//                                            HttpServletResponse response,
//                                            FilterChain chain,
//                                            Authentication authResult) throws IOException, ServletException {
//        byte[] key = "SecureSecureSecureSecureSecureSecureSecureSecureSecureSecureSecureSecure".getBytes();
//        String token = Jwts.builder()
//                           .setSubject(authResult.getName())
//                           .claim("authorities", authResult.getAuthorities())
//                           .setIssuedAt(Date.valueOf(LocalDate.now()
//                                                              .plusWeeks(2)))
//                           .signWith(Keys.hmacShaKeyFor(key))
//                           .compact();
//        return new Access(token);
//    }
//}