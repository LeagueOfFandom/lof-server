package com.lof.lofserver.filter;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;

@Component
@Slf4j
public class JsonWebToken {

    private final String secretKey;

    @Autowired
    public JsonWebToken(@Value("${JWT.signature}") String secretKey){
        this.secretKey = secretKey;
    }

    //** id로 JWT 토큰 생성 */
    public String createJsonWebTokenById(Long id) {
        Date now = new Date();

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE) // header type (jwt)
                .setIssuer("lof-server") // (iss setting)
                .setIssuedAt(now) // (iat setting)
                .setExpiration(new Date(now.getTime() + Duration.ofDays(30).toMillis())) // (exp setting)
                .claim("id", id) // (secret claim setting)
                .signWith(SignatureAlgorithm.HS256, secretKey) // (signature setting)
                .compact();
    }

    //** JWT 토큰을 파싱하여 Claims 객체를 반환한다. */
    public Claims getClaimsByToken(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * JWT 토큰의 유효성을 검사한다.
     * @param token - 검사할 JWT 토큰
     * @return expired, error, valid - 토큰의 유효성
     */
    public String checkJwtToken(String token){
        try{
            Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);
        }catch (ExpiredJwtException e){
            log.info("ExpiredJwtException");
            return "expired";
        }catch (Exception e){
            log.info("JWTException");
            return "error";
        }
        return "valid";
    }
}