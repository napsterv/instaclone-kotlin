//package io.instagram.instagram.auth;
//
//import io.instagram.instagram.jwt.Token;
//import io.instagram.instagram.jwt.TokenResponse;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Service;
//
//import java.sql.Date;
//import java.time.LocalDate;
//import java.util.HashMap;
//import java.util.Map;
//
//@Service
//public class JwtUtil {
//    public TokenResponse generateTokens(UserDetails userDetails) {
//        Map<String, Object> claims = new HashMap<>();
//        return createToken(claims, userDetails.getUsername());
//    }
//
//    private TokenResponse createToken(Map<String, Object> claims,
//                                      String username) {
//        Map<String, Object> headerClaims = new HashMap<>();
//        headerClaims.put("typ", "JWT");
//        String token = Jwts.builder()
//                           .setClaims(claims)
//                           .setSubject(username)
//                           .setIssuedAt(Date.valueOf(LocalDate.now()))
//                           .setExpiration(Date.valueOf(LocalDate.now()
//                                                                .plusWeeks(1)))
//                           .signWith(Keys.hmacShaKeyFor(
//                                   "mZq3t6w9z$C&F)J@NcRfUjXn2r5u7x!A%D*G-KaPdSgVkYp3s6v9y/B?E(H+MbQe".getBytes()))
//                           .setHeader(headerClaims)
//                           .compact();
//        return new TokenResponse(new Token(token, token));
//    }
//}
