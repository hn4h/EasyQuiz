/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.time.LocalDateTime;
import java.util.Properties;
import java.util.UUID;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.eclipse.jdt.internal.compiler.batch.Main;

/**
 *
 * @author 11
 */
public class OTPService {
    private final int LIMIT_MINUS = 5;
    static final String from = "easiquiz@gmail.com";
    static final String password = "zgwt iour dxlk dqzk";
    
   public String generateOTP() {
    return String.format("%06d", (int) (Math.random() * 1000000));     
    } 
    
    public LocalDateTime expireDateTime() {
        return LocalDateTime.now().plusMinutes(LIMIT_MINUS);
    }
    
    public boolean isExpireTime(LocalDateTime time) {
        return LocalDateTime.now().isAfter(time);
    }
    
    
    public boolean sendEmail(String to, String otp) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        
        
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        };
        
        Session session = Session.getInstance(props, auth);
        
        MimeMessage msg = new MimeMessage(session);
        
        try {
            msg.addHeader("Content-type", "text/html; charset=UTF-8");
            msg.setFrom(from);
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
             msg.setSubject("Your OTP is: " + otp, "UTF-8");
            String content = "<p>Don't share your code with anyone.</p>";
            msg.setContent(content, "text/html; charset=UTF-8");
            Transport.send(msg);
            System.out.println("Send successfully");
            return true;
        } catch (Exception e) {
            System.out.println("Send error");
            System.out.println(e);
            return false;
        }
    }
    public static void main(String[] args) {
       OTPService s = new OTPService();
       s.sendEmail("huannhhe180923@fpt.edu.vn", s.generateOTP());
              s.sendEmail("huannhhe180923@fpt.edu.vn", s.generateOTP());

                     s.sendEmail("huannhhe180923@fpt.edu.vn", s.generateOTP());

                            s.sendEmail("huannhhe180923@fpt.edu.vn", s.generateOTP());

                                   s.sendEmail("huannhhe180923@fpt.edu.vn", s.generateOTP());
       s.sendEmail("huannhhe180923@fpt.edu.vn", s.generateOTP());

              s.sendEmail("huannhhe180923@fpt.edu.vn", s.generateOTP());

                     s.sendEmail("huannhhe180923@fpt.edu.vn", s.generateOTP());

                            s.sendEmail("huannhhe180923@fpt.edu.vn", s.generateOTP());
       s.sendEmail("huannhhe180923@fpt.edu.vn", s.generateOTP());
       s.sendEmail("huannhhe180923@fpt.edu.vn", s.generateOTP());
       s.sendEmail("huannhhe180923@fpt.edu.vn", s.generateOTP());
       s.sendEmail("huannhhe180923@fpt.edu.vn", s.generateOTP());
       s.sendEmail("huannhhe180923@fpt.edu.vn", s.generateOTP());

       
       
       
       
                            
                                   
    }
}
