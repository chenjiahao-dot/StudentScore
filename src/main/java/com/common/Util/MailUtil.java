package com.common.Util;


import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.time.Duration;

/**
 * @author Mr_Ja
 * 邮件发送工具
 */
@Controller
@Slf4j
@RequiredArgsConstructor
@Component
public class MailUtil {

    private final JavaMailSenderImpl mailSender;

    private final RedisTemplate<String, String> redisTemplate;

    public boolean send(String email) throws MessagingException {
        try {
            log.info("正在给邮箱号:{}的邮箱发送验证码", email);
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            //生成随机验证码
            String code = CodeUtil.generateCode(6);
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            //设置一个html邮件信息
            helper.setText("【系统】当前正在进行邮箱验证，您的验证码为：" + code + "。若非您本人操作，请及时联系我们。验证码5分钟内有效，如果超时请重新获取。", false);
            //设置邮件主题名
            helper.setSubject("【系统】邮箱验证码");
            //发给谁-》邮箱地址
            helper.setTo(email);
            //谁发的-》发送人邮箱
            helper.setFrom(mailSender.getUsername());
            //将邮箱验证码以邮件地址为key存入redis,5分钟过期
            redisTemplate.opsForValue().set("code:" + email, code, Duration.ofMinutes(5));
            mailSender.send(mimeMessage);
            log.info("验证码发送成功");
            return true;
        } catch (Exception e) {
            log.error("验证码发送失败,原因:{}", e.toString());
            throw e;
        }

    }
}
