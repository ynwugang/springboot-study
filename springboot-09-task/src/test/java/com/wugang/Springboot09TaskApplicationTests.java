package com.wugang;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@SpringBootTest
class Springboot09TaskApplicationTests {

    @Autowired
    JavaMailSenderImpl mailSender;

    @Test
    void contextLoads() {
        //一个简单的邮件
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setSubject("张三你好~");
        mailMessage.setText("springb课程");

        mailMessage.setTo("996528491@qq.com");
        mailMessage.setFrom("996528491@qq.com");

        mailSender.send(mailMessage);
    }


    @Test
    void contextLoads1() throws MessagingException {
        //一个复杂的邮件
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        //组装
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        //主题，正文
        helper.setSubject("小四四，你好呀！");
        helper.setText("<p style='color:red'>老婆老婆我爱你，就像老鼠爱大米</p>", true);

        //附件
        helper.addAttachment("1.png", new File("C:\\Users\\admin\\Desktop\\1.png"));
        helper.addAttachment("2.jpg", new File("C:\\Users\\admin\\Desktop\\1.png"));

        helper.setTo("2423549221@qq.com");
        helper.setFrom("996528491@qq.com");

        mailSender.send(mimeMessage);
    }

    /**
     *
     * @param html
     * @param subject
     * @param text
     * @throws MessagingException
     */
    public void sendMail(Boolean html, String subject, String text) throws MessagingException {
        //一个复杂的邮件
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        //组装
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        //主题，正文
        helper.setSubject("小四四，你好呀！");
        helper.setText("<p style='color:red'>老婆老婆我爱你，就像老鼠爱大米</p>", true);

        //附件
        helper.addAttachment("1.png", new File("C:\\Users\\admin\\Desktop\\1.png"));
        helper.addAttachment("2.jpg", new File("C:\\Users\\admin\\Desktop\\1.png"));

        helper.setTo("2423549221@qq.com");
        helper.setFrom("996528491@qq.com");

        mailSender.send(mimeMessage);
    }
}
