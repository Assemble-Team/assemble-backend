package assemble.api.auth.jwt;

import assemble.api.apiPayload.handler.GeneralException;
import assemble.api.apiPayload.status.JwtErrorStatus;
import assemble.api.auth.domain.MemberDetail;
import assemble.api.auth.service.MemberDetailService;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final MemberDetailService memberDetailService;

    @Value("${jwt.token.secret}")
    private String secret;

    @Value("${jwt.token.access-expiration}")
    private Long accessExpiration;

    @Value("${jwt.token.refresh-expiration}")
    private Long refreshExpiration;

    public String createAccessToken(String email, Long memberId){
        return createToken(email, memberId, accessExpiration);
    }

    public String createRefreshToken(String email, Long memberId){
        return createToken(email, memberId, refreshExpiration);
    }

    public String createToken(String email, Long memberId, Long expiration){
        Claims claims = Jwts.claims().setSubject(email);
        claims.put("memberId", memberId);
        claims.put("email", email);
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + expiration))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public Claims parseClaims(String token){
        try{
            return Jwts.parserBuilder()
                    .setSigningKey(secret)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT token: {}", e.getMessage());
            //throw new GeneralException(JwtErrorStatus.EXPIRED_TOKEN);
        }
        return null;
    }

    public Long getExpiration(String token){
        return parseClaims(token).getExpiration().getTime();
    }

    public String getEmail(String token){
        return parseClaims(token).get("email", String.class);
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) {
        try{
            Jwts.parserBuilder()
                    .setSigningKey(secret)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
            throw new GeneralException(JwtErrorStatus.WRONG_TYPE_SIGNATURE);
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
            throw new GeneralException(JwtErrorStatus.TOKEN_EXPIRED);
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
            throw new GeneralException(JwtErrorStatus.WRONG_TYPE_TOKEN);
        } catch (IllegalArgumentException e) {
            log.info("JWT 토큰이 잘못되었습니다.");
            throw new GeneralException(JwtErrorStatus.NOT_VALID_TOKEN);
        }
    }

    public Authentication getAuthentication(String token) {
        MemberDetail memberDetail = memberDetailService.loadUserByUsername(this.getEmail(token));
        return new UsernamePasswordAuthenticationToken(memberDetail, null, memberDetail.getAuthorities());
    }

}
