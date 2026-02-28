package assemble.api.auth.controller;

import assemble.api.apiPayload.CommonResponse;
import assemble.api.auth.dto.AuthRequestDTO;
import assemble.api.auth.dto.AuthResponseDTO;
import assemble.api.auth.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    @Operation(
            summary = "로그인 API",
            description = "이메일, 비밀번호를 통해 로그인하는 API"
    )
    public ResponseEntity<CommonResponse<AuthResponseDTO.LoginResultDTO>> login(@RequestBody @Valid AuthRequestDTO.LoginDTO request,
                                                   HttpServletResponse response){
        AuthResponseDTO.LoginResultDTO result = authService.loginMember(request, response);
        return ResponseEntity.ok().body(CommonResponse.onSuccess(result));
    }

    @PostMapping("/reissue")
    @Operation(
            summary = "토큰 재발급 API",
            description = "액세스 토큰이 만료된 경우 리프레시 토큰을 통해서 액세스 토큰을 재발급 하는 API"
    )
    public ResponseEntity<CommonResponse<?>> reissue(@RequestBody @Valid AuthRequestDTO.ReissueDTO request, HttpServletResponse response){
        authService.reissueToken(request, response);
        return ResponseEntity.ok().body(CommonResponse.onSuccess(null));
    }

    @PostMapping("/logout")
    @Operation(
            summary = "로그아웃 API",
            description = "로그아웃하는 API"
    )
    public ResponseEntity<CommonResponse<?>> logout(HttpServletRequest request){
        authService.logoutMember(request);
        return ResponseEntity.ok().body(CommonResponse.onSuccess(null));
    }


}
