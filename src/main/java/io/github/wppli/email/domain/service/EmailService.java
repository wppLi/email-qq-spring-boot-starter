package io.github.wppli.email.domain.service;

import cn.hutool.core.collection.CollectionUtil;
import io.github.wppli.email.domain.IEmailService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.InternetAddress;
import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author li--jiaqiang 2024−12−20
 */
@Service
public class EmailService implements IEmailService, Serializable {

    private final static long serialVersionUID = 1L;

    private final static String EMAIL_NAME = "登录验证码";
    private final static String REGEX = ",";

    private final JavaMailSender javaMailSender;//注入邮件工具类
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void send(String name, String form, String to, String subject, String content, Boolean isHtml, String cc, String bcc, List<File> files) {

        if (StringUtils.isAnyBlank(form, to, subject, content)) {
            throw new IllegalArgumentException("发送人,接收人,主题,内容均不可为空");
        }
        try {
            //true表示支持复杂类型
            MimeMessageHelper messageHelper = new MimeMessageHelper(javaMailSender.createMimeMessage(), true);
            //邮件发信人
            messageHelper.setFrom(new InternetAddress(name + "<" + form + ">"));
            //邮件收信人
            messageHelper.setTo(to.split(REGEX));
            //邮件主题
            messageHelper.setSubject(subject);
            //邮件内容
            messageHelper.setText(content, isHtml);
            //抄送
            if (!StringUtils.isEmpty(cc)) {
                messageHelper.setCc(cc.split(REGEX));
            }
            //密送
            if (!StringUtils.isEmpty(bcc)) {
                messageHelper.setCc(bcc.split(REGEX));
            }
            //添加邮件附件
            if (CollectionUtil.isNotEmpty(files)) {
                for (File file : files) {
                    messageHelper.addAttachment(file.getName(), file);
                }
            }
            // 邮件发送时间
            messageHelper.setSentDate(new Date());
            //正式发送邮件
            javaMailSender.send(messageHelper.getMimeMessage());
        } catch (Exception e) {
            throw new RuntimeException("邮件发送失败", e);
        }
    }


    @Override
    public void sendText(String form, String to, String subject, String content) {
        this.send(EMAIL_NAME, form, to, subject, content, false, null, null, null);
    }

    @Override
    public void sendText(String name, String form, String to, String subject, String content) {
        this.send(name, form, to, subject, content, false, null, null, null);
    }

    @Override
    public void sendHtml(String form, String to, String subject, String content) {
        this.send(EMAIL_NAME, form, to, subject, content, true, null, null, null);
    }


}