package assemble.api.auth.jwt;

import assemble.api.apiPayload.handler.GeneralException;
import assemble.api.apiPayload.status.JwtErrorStatus;
import assemble.api.auth.domain.MemberDetail;
import assemble.api.auth.service.MemberDetailService;
import assemble.api.member.business.finder.MemberFinder;
import assemble.api.member.domain.Member;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider { // JWT 토큰 생성, 검증, 인증 객체 반환 등의 역할 수행

    private final MemberDetailService memberDetailService;
    private final MemberFinder memberFinder;

    @Value("${jwt.token.secret}")
    private String secret;

    @Value("${jwt.token.access-expiration}")
    private Long accessExpiration;

    @Value("${jwt.token.refresh-expiration}")
    private Long refreshExpiration;

    private Key secretKey;

    @PostConstruct
    public void init(){
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateAccessToken(Authentication authentication){
        MemberDetail memberDetail = (MemberDetail) authentication.getPrincipal();
        return generateToken(memberDetail.getUsername(), memberDetail.getMemberId(), memberDetail.getAuthorities(), accessExpiration);
    }

    public String generateRefreshToken(Authentication authentication){
        MemberDetail memberDetail = (MemberDetail) authentication.getPrincipal();
        return generateToken(memberDetail.getUsername(), memberDetail.getMemberId(), memberDetail.getAuthorities(), refreshExpiration);
    }

    public String generateToken(String subject, Long memberId, Collection<? extends GrantedAuthority> authorities, Long expiration){
        Claims claims = Jwts.claims().setSubject(null);
        claims.put("memberId", memberId);

        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + expiration))
                .signWith(secretKey)
                .compact();
    }

    public Long getRefreshExpiration(String token) {
        return parseClaims(token).getExpiration().getTime();
    }

    public String getEmail(String token){
        return parseClaims(token).getSubject();
    }

    public Claims parseClaims(String token) {
        try{
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
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

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);
        }
        return null;
    }

    public Authentication getAuthentication(String token) {
        Claims claims = parseClaims(token);
        Member member = memberFinder.findById(claims.get("memberId", Long.class));
        MemberDetail memberDetail = memberDetailService.loadUserByUsername(member.getEmail());
        return new UsernamePasswordAuthenticationToken(memberDetail, null, memberDetail.getAuthorities());
    }

}
