package assemble.api.auth.service;

import assemble.api.apiPayload.handler.GeneralException;
import assemble.api.apiPayload.status.MemberErrorStatus;
import assemble.api.auth.converter.AuthConverter;
import assemble.api.auth.domain.MemberDetail;
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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberDetailService memberDetailService;
    private final RedisTemplate<String, Object> redisTemplate;


    public AuthResponseDTO.LoginResultDTO loginMember(AuthRequestDTO.LoginDTO request, HttpServletResponse response) {
        // 회원 찾기
        MemberDetail memberDetail;
        try{
            memberDetail = memberDetailService.loadUserByUsername(request.getEmail());
        }catch(UsernameNotFoundException e){
            throw new GeneralException(MemberErrorStatus.NOT_EXIST_EMAIL);
        }

        // 비밀번호 확인
        if(!passwordEncoder.matches(request.getPassword(), memberDetail.getPassword())){
            throw new GeneralException(MemberErrorStatus.NOT_MATCH_PASSWORD);
        }

        // 인증 전 객채 생성
        Authentication authentication = new UsernamePasswordAuthenticationToken(memberDetail, null);

        // 액세스, 리프레시 토큰 발급
        String access = jwtTokenProvider.generateAccessToken(authentication);
        String refresh = jwtTokenProvider.generateRefreshToken(authentication);

        // 레디스에 리프레시 저장
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        Long expiration = jwtTokenProvider.getRefreshExpiration(refresh);  // 여기 메서드에 안에 값채우기
        ops.set("RefreshToken"+request.getEmail(), refresh, expiration, TimeUnit.MILLISECONDS);

        // 리스폰스 헤더에 액세스 추가
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.setHeader("Authorization", "Bearer " + access);

        // 반환
        return AuthConverter.toLoginResultDTO(memberDetail.getMemberId(), refresh);
    }

}
