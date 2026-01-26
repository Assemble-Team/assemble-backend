package assemble.api.auth.service;

import assemble.api.apiPayload.handler.GeneralException;
import assemble.api.apiPayload.status.MemberErrorStatus;
import assemble.api.auth.converter.AuthConverter;
import assemble.api.auth.dto.AuthRequestDTO;
import assemble.api.auth.dto.AuthResponseDTO;
import assemble.api.auth.jwt.JwtTokenProvider;
import assemble.api.member.business.finder.MemberFinder;
import assemble.api.member.domain.Member;
import assemble.api.member.service.MemberService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberFinder memberFinder;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisTemplate<String, Object> redisTemplate;

    // 로그인
    public AuthResponseDTO.LoginResultDTO loginMember(AuthRequestDTO.LoginDTO loginDTO, HttpServletResponse response) {
        Member member = memberFinder.findByEmail(loginDTO.getEmail());
        // 비밀번호 맞는지 확인
        if(!passwordEncoder.matches(loginDTO.getPassword(), member.getPassword())){
            throw new GeneralException(MemberErrorStatus.NOT_MATCH_PASSWORD);
        }
        // 액세스, 리프레시 토큰 발급
        String access = jwtTokenProvider.createAccessToken(member.getEmail(), member.getId());
        String refresh = jwtTokenProvider.createRefreshToken(member.getEmail(), member.getId());

        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        Long expiration = jwtTokenProvider.getExpiration(refresh);
        ops.set("RefreshToken"+loginDTO.getEmail(), refresh, expiration, TimeUnit.MILLISECONDS);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.setHeader("Authorization", "Bearer " + access);

        return AuthConverter.toLoginResultDTO(member.getId(), refresh);
    }

}
