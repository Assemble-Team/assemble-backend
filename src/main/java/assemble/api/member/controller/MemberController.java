package assemble.api.member.controller;

import assemble.api.apiPayload.CommonResponse;
import assemble.api.member.dto.MemberRequestDTO;
import assemble.api.member.dto.MemberResponseDTO;
import assemble.api.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    @Operation(
            summary = "회원가입 API",
            description = "회원 정보로 회원가입하는 API"
    )
    public ResponseEntity<CommonResponse<MemberResponseDTO.MemberCreateDTO>> signUp(@RequestBody @Valid MemberRequestDTO.SignUpDTO signUpDTO) {
        System.out.println(signUpDTO.getCategories());
        MemberResponseDTO.MemberCreateDTO result = memberService.signUpMember(signUpDTO);
        return new ResponseEntity<>(CommonResponse.created(result), HttpStatus.CREATED);
    }

    @PostMapping("/email")
    @Operation(
            summary = "이메일 인증번호 전송 API",
            description = "가입할 이메일로 인증번호를 전송하는 API"
    )
    public ResponseEntity<CommonResponse<?>> sendEmail(@RequestBody @Valid MemberRequestDTO.SendEmailDTO emailDTO){
        memberService.sendEmailCode(emailDTO);
        return ResponseEntity.ok().body(CommonResponse.onSuccess(null));
    }

    @PostMapping("/email/check")
    @Operation(
            summary =  "이메일 인증번호 확인 API",
            description = "전송된 인증번호와 일치 여부를 확인하는 API"
    )
    public ResponseEntity<CommonResponse<?>> checkEmail(@RequestBody @Valid MemberRequestDTO.CheckEmailDTO emailDTO){
        memberService.checkEmailCode(emailDTO);
        return ResponseEntity.ok().body(CommonResponse.onSuccess(null));
    }
}
