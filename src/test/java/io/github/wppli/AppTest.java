//package io.github.wppli;
//
//
//import io.github.wppli.email.Application;
//import io.github.wppli.email.config.MailProperties;
//import io.github.wppli.email.domain.service.EmailService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.test.context.junit4.SpringRunner;
//
///**
// * Unit test for simple App.
// */
//@SpringBootTest(classes = Application.class)
//@ComponentScan(basePackages = {"io.github.wppli"})
//@RunWith(SpringRunner.class)
//public class AppTest {
//
//    @Autowired
//    private EmailService emailService;
//
//    @Test
//    public void test_email() {
//        MailProperties properties = new MailProperties();
//        emailService.sendText("登录验证码", properties.getUsername(), "2777843397@qq.com", "学习提醒", "快学习！！");
//    }
//
//}
