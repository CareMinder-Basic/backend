package com.careminder.backend.controller.account;

import com.careminder.backend.dto.account.MailRequest;
import com.careminder.backend.service.account.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RequestMapping("/api/mail")
@RestController
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;

    // 인증코드 메일 발송
    @PostMapping("/send")
    public String mailSend(@RequestBody MailRequest mailRequest){
        log.info("EmailController.mailSend()");
        mailService.sendEmail(mailRequest.mail());
        return "인증코드가 발송되었습니다.";
    }

    // 인증코드 인증
    @PostMapping("/verify")
    public String verify(@RequestBody MailRequest mailRequest) {
        log.info("EmailController.verify()");
        boolean isVerify = mailService.verifyEmailCode(mailRequest.mail(),mailRequest.verifyCode());
        return isVerify ? "인증이 완료되었습니다." : "인증 실패하셨습니다.";
    }
}
