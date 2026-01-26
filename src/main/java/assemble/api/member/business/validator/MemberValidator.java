package assemble.api.member.business.validator;

import assemble.api.apiPayload.handler.GeneralException;
import assemble.api.apiPayload.status.MemberErrorStatus;
import assemble.api.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberValidator {

    private final MemberRepository memberRepository;
    private final RedisTemplate<String, Object> redisTemplate;

    public void checkEmailDuplicate(String email) {
        if(memberRepository.existsByEmail(email))
            throw new GeneralException(MemberErrorStatus.EXIST_EMAIL);
    }

    public void checkEmailVerified(String email) {
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        String verify = (String) ops.get("VerifiedEmail"+email);
        if(verify == null){
            throw new GeneralException(MemberErrorStatus.NOT_EMAIL_VERIFY);
        }
    }
}
