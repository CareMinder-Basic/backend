package com.careminder.backend.service.account;

import com.careminder.backend.global.error.exception.MessagingException;
import com.careminder.backend.global.redis.RedisUtil;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;
    private final RedisUtil redisUtil;
    private static final String senderEmail = "dlwjdals7073@gmail.com";

    private String createCode() {
        int leftLimit = 48; // number '0'
        int rightLimit = 122; // alphabet 'z'
        int targetStringLength = 6;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 | i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    // 이메일 내용 초기화
    private String setContext(String code) {
//        Context context = new Context();
//        TemplateEngine templateEngine = new TemplateEngine();
//        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
//
//        context.setVariable("code", code);
//
//        templateResolver.setPrefix("templates/");
//        templateResolver.setSuffix(".html");
//        templateResolver.setTemplateMode(TemplateMode.HTML);
//        templateResolver.setCacheable(false);
//
//        templateEngine.setTemplateResolver(templateResolver);
//
//        return templateEngine.process("mail", context);
        String body = "";
        body += "<h3>" + "요청하신 인증 번호입니다." + "</h3>";
        body += "<h1>" + code + "</h1>";
        body += "<h3>" + "감사합니다." + "</h3>";
        return body;
    }

    // 이메일 폼 생성
    private MimeMessage createMailForm(String mail){
        String authCode = createCode();

        MimeMessage message = javaMailSender.createMimeMessage();
        try{
            message.addRecipients(MimeMessage.RecipientType.TO, mail);
            message.setSubject("안녕하세요. 인증번호입니다.");
            message.setFrom(senderEmail);
            message.setText(setContext(authCode), "utf-8", "html");
        } catch (MessagingException | jakarta.mail.MessagingException e) {
            e.printStackTrace();
        }
        // Redis 에 해당 인증코드 인증 시간 설정
        redisUtil.setDataExpire(mail, authCode, 60 * 30L);

        return message;
    }

    // 인증코드 이메일 발송
    public void sendEmail(String toEmail) throws MessagingException {
        if (redisUtil.existData(toEmail)) {
            redisUtil.deleteData(toEmail);
        }
        // 이메일 폼 생성
        MimeMessage emailForm = createMailForm(toEmail);
        // 이메일 발송
        javaMailSender.send(emailForm);
    }

    // 코드 검증
    public Boolean verifyEmailCode(String email, String code) {
        String codeFoundByEmail = redisUtil.getData(email);
        log.info("code found by email: " + codeFoundByEmail);
        if (codeFoundByEmail == null) {
            return false;
        }
        return codeFoundByEmail.equals(code);
    }
}