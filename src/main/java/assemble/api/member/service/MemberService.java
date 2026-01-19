package assemble.api.member.service;

import assemble.api.member.business.factory.MemberFactory;
import assemble.api.member.business.validator.MemberValidator;
import assemble.api.member.converter.MemberConverter;
import assemble.api.member.domain.Member;
import assemble.api.member.dto.MemberRequestDTO;
import assemble.api.member.dto.MemberResponseDTO;
import assemble.api.member.infrastructure.email.EmailSender;
import assemble.api.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final EmailSender emailSender;
    private final MemberValidator memberValidator;
    private final MemberFactory memberFactory;
    private final MemberRepository memberRepository;

    // 회원가입
    public MemberResponseDTO.MemberCreateDTO signUpMember(MemberRequestDTO.SignUpDTO signUpDTO) {
        memberValidator.checkEmailVerified(signUpDTO.getEmail());
        Member member = memberFactory.create(signUpDTO);
        memberRepository.save(member);
        return MemberConverter.toMemberCreateDTO(member);
    }

    // 이메일 인증번호 전송
    public void sendEmailCode(MemberRequestDTO.SendEmailDTO sendEmailDTO) {
        emailSender.sendEmail(sendEmailDTO.getEmail());
    }

    // 이메일 인증번호 확인
    public void checkEmailCode(MemberRequestDTO.CheckEmailDTO checkEmailDTO) {
        memberValidator.checkEmailDuplicate(checkEmailDTO.getEmail());
        emailSender.checkEmailCode(checkEmailDTO.getEmail(), checkEmailDTO.getCode());
    }

}
