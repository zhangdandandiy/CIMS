package com.ruoyi.common.utils.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;

/**
 * 邮件发送工具类
 *
 * @author Dandan
 * @date 2023/10/2 16:27
 **/

@Component
public class EmailUtils {

    // 发送方邮件地址
    // @Value("${spring.mail.from}")
    private static String from = "3303850545@qq.com";

    @Autowired
    private static JavaMailSender mailSender;

    /**
     * 发送纯文本邮件信息
     *
     * @param to      接收方
     * @param subject 邮件主题
     * @param content 邮件内容(发送内容)
     */
    public static void sendMessage(String to, String subject, String content) {
        // 创建一个邮件对象
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        // 设置发送方
        mailMessage.setFrom(from);
        // 设置是接收方
        mailMessage.setTo(to);
        // 设置邮件主题
        mailMessage.setSubject(subject);
        // 设置邮件内容
        mailMessage.setText(content);
        mailMessage.setReplyTo("3303850545@qq.com");
        mailMessage.setSentDate(new Date());
        // 发送邮件
        mailSender.send(mailMessage);
    }

    /**
     * 发送带附件的邮件信息
     *
     * @param to      接收方
     * @param subject 邮件主题
     * @param content 邮件内容
     * @param files   文件数组(可发送多个附件)
     */
    public static void sendMessageCarryFiles(String to, String subject, String content, File[] files) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            // 设置发送方
            helper.setFrom(from);
            // 设置接收方
            helper.setTo(to);
            // 设置邮件主题
            helper.setSubject(subject);
            // 设置邮件内容
            helper.setText(content);
            // 添加附件(多个)
            if (files != null && files.length > 0) {
                for (File file : files) {
                    helper.addAttachment(file.getName(), file);
                }
            }

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        // 发送邮件
        mailSender.send(mimeMessage);
    }

    /**
     * 发送带附件的邮件信息
     *
     * @param to      接收方
     * @param subject 邮件主题
     * @param content 邮件内容（发送内容）
     * @param file    单个文件
     */
    public static void sendMessageCarryFile(String to, String subject, String content, File file) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            // 设置发送方
            helper.setFrom(from);
            // 设置接收方
            helper.setTo(to);
            // 设置邮件主题
            helper.setSubject(subject);
            // 设置邮件内容
            helper.setText(content);
            // 单个附件
            helper.addAttachment(file.getName(), file);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        // 发送邮件
        mailSender.send(mimeMessage);
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

}
