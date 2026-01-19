package assemble.api.member.infrastructure.email;

import assemble.api.apiPayload.handler.GeneralException;
import assemble.api.apiPayload.status.MemberErrorStatus;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class EmailSender {

    private final JavaMailSender javaMailSender;
    private final RedisTemplate<String, Object> redisTemplate;

    public void sendEmail(String email){
        // 인증 번호 생성
        int number = (int)(Math.random() * 90000) + 100000;

        // 메시지 생성 (html 파일)
        makeEmailMessage(email, number);

        // redis에 인증번호 저장
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        ops.set("EmailCode"+email, number+"", 180, TimeUnit.SECONDS);
    }

    public void makeEmailMessage(String email, int number){
        try{
            MimeMessage message = javaMailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setSubject("[ASSEMBLE] 이메일 인증 번호");
            helper.setTo(email);
            helper.setFrom(new InternetAddress("assemble@assemble.com", "assemble", "UTF-8"));

            String body = "";
            body += "<h3>" + "요청하신 인증번호입니다."+"</h3>";
            body += "<h1>" + number + "</h1>";
            body += "<h3>" + "화면으로 돌아가 인증번호를 입력해주세요" + "</h3>";
            body += "<h3>" + "감사합니다" + "</h3>";
            helper.setText(body, true);

            javaMailSender.send(message);
        } catch(MessagingException | UnsupportedEncodingException e){
            throw new RuntimeException(e);
        }
    }

    public void checkEmailCode(String email, String code){
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        String emailCode = (String) ops.get("EmailCode"+email);
        if(!code.equals(emailCode)){
            throw new GeneralException(MemberErrorStatus.WRONG_CODE);
        }
        ops.set("VerifiedEmail"+email, String.valueOf(System.currentTimeMillis()), 180, TimeUnit.SECONDS);
    }
}
