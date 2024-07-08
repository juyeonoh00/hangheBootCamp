//package myselectshop.jwt;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.algorithms.Algorithm;
//import lombok.RequiredArgsConstructor;
//import myselectshop.dto.TokenDto;
//import myselectshop.security.UserDetailsImpl;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.core.Authentication;
//
//import java.util.Date;
//
//@Configuration
//@RequiredArgsConstructor
//public class JwtTokenService {
//    @Value("${jwt.secret}")
//    private String secretKey = "7ae96706cd6e6aade43a3b843bb3317f823ab927b64beb8d45558b3ac29f079dd36afe40f1646af31c334ccbb568cfe64b9e4e54a47aa5a5077796eca1e58075";
//    private final long TOKEN_TIME = 60 * 60 * 1000L; // 60분
//    public TokenDto generateToken(Authentication authentication){
//        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();
//        String accessToken = JWT.create()
//                .withSubject(userDetailsImpl.getUsername())
//                .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_TIME))
//                .withClaim("username", userDetailsImpl.getUsername())
//                .withClaim("password", userDetailsImpl.getPassword())
//                .sign(Algorithm.HMAC512(secretKey));
//        return new TokenDto(accessToken);
//    }
//}
