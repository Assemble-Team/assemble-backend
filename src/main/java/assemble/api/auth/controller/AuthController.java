package assemble.api.auth.controller;

import assemble.api.apiPayload.CommonResponse;
import assemble.api.auth.dto.AuthRequestDTO;
import assemble.api.auth.dto.AuthResponseDTO;
import assemble.api.auth.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
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
    public ResponseEntity<CommonResponse<AuthResponseDTO.LoginResultDTO>> login(@RequestBody @Valid AuthRequestDTO.LoginDTO loginDTO, HttpServletResponse response){
        AuthResponseDTO.LoginResultDTO result = authService.loginMember(loginDTO, response);
        return ResponseEntity.ok().body(CommonResponse.onSuccess(result));
    }
}
