//package io.instagram.instagram.user;
//
//import io.instagram.instagram.auth.JwtUtil;
//import io.instagram.instagram.auth.LoginRequest;
//import io.instagram.instagram.jwt.TokenResponse;
//import lombok.AllArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Optional;
//
//@AllArgsConstructor
//@RestController
//public class UserController {
//    private final AuthenticationManager authenticationManager;
//    private final AppBaseUserRepository appBaseUserRepository;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;
//    private final JwtUtil jwtUtil;
//
//    @PostMapping("signup")
//    public AppBaseUser signUp(@RequestBody AppBaseUser user) {
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        return appBaseUserRepository.save(user);
//    }
//
//    @PostMapping("login")
//    public ResponseEntity<TokenResponse> loginUser(@RequestBody
//                                                   LoginRequest loginRequest) throws BadCredentialsException {
//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
//                                                            loginRequest.getPassword()));
//        } catch (BadCredentialsException e) {
//            throw new BadCredentialsException("Username or Password is incorrect");
//        }
//
//        final Optional<AppBaseUser> user = appBaseUserRepository.findByUsername(loginRequest.getUsername());
//
//        if (user.isPresent()) {
//            final TokenResponse tokenResponse = jwtUtil.generateTokens(user.get());
//            return ResponseEntity.ok(tokenResponse);
//        } else {
//            throw new BadCredentialsException("Username or Password is incorrect");
//        }
//    }
//}