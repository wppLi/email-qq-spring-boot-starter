package io.github.wppli.email.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@ComponentScan(basePackages = {"io.github.wppli"})
@EnableConfigurationProperties(MailProperties.class) // 启用EncryptProperties类的配置属性。
public class MailAutoConfig {

    @Bean
    public JavaMailSender getJavaMailSender(MailProperties mailProperties) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailProperties.getHost());
        mailSender.setPort(mailProperties.getPort());
        mailSender.setUsername(mailProperties.getUsername());
        mailSender.setPassword(mailProperties.getPassword());

        Properties props = mailSender.getJavaMailProperties();

        MailProperties.Properties properties = mailProperties.getProperties();
        props.put("mail.smtp.timeout", properties.getTimeout());
        props.put("mail.smtp.connectiontimeout", properties.getConnectionTimeout());
        props.put("mail.smtp.writetimeout", properties.getWriteTimeout());

        MailProperties.Properties.Mail mail = properties.getMail();
        props.put("mail.transport.protocol", mail.getTransportProtocol());

        MailProperties.Properties.Mail.Smtp smtp = mail.getSmtp();
        props.put("mail.smtp.auth", smtp.getAuth());
        props.put("mail.smtp.starttls.enable", smtp.getStarttlsEnable());
        props.put("mail.smtp.starttls.required", smtp.getStarttlsRequired());
        props.put("mail.smtp.socketFactory.class", smtp.getSocketFactoryClass());
        props.put("mail.debug", smtp.isDebug());

        return mailSender;
    }
}