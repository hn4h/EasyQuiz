package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import service.OTPService;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OTPServiceTest {
    private OTPService otpService;

    @BeforeEach
    void setUp() {
        otpService = new OTPService();
    }

    @Test
    void testGenerateOTP_Length() {
        String otp = otpService.generateOTP();
        assertNotNull(otp);
        assertEquals(6, otp.length());
        assertTrue(otp.matches("\\d{6}"));
    }

    @Test
    void testExpireDateTime() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiry = otpService.expireDateTime();

        assertNotNull(expiry);
        assertTrue(expiry.isAfter(now));
        assertTrue(expiry.isBefore(now.plusMinutes(6))); // Chỉ lệch tối đa 5 phút
    }

    @Test
    void testIsExpireTime_Expired() {
        LocalDateTime pastTime = LocalDateTime.now().minusMinutes(10);
        assertTrue(otpService.isExpireTime(pastTime));
    }

    @Test
    void testIsExpireTime_NotExpired() {
        LocalDateTime futureTime = LocalDateTime.now().plusMinutes(2);
        assertFalse(otpService.isExpireTime(futureTime));
    }

    @Test
    void testSendEmail_Success() throws Exception {
        // Mock các thành phần JavaMail
        Properties props = new Properties();
        Session session = Session.getInstance(props);
        MimeMessage mockMessage = spy(new MimeMessage(session));

        Transport mockTransport = mock(Transport.class);

        // Dùng PowerMockito để thay thế Transport.send()
        try (MockedStatic<Transport> mockedTransport = mockStatic(Transport.class)) {
            mockedTransport.when(() -> Transport.send(any(MimeMessage.class))).thenAnswer(invocation -> {
                System.out.println("Mock email sent successfully!");
                return null;
            });

            boolean result = otpService.sendEmail("test@example.com", "123456");
            assertTrue(result);
        }
    }

    @Test
    void testSendEmail_Failure() throws Exception {
        try (MockedStatic<Transport> mockedTransport = mockStatic(Transport.class)) {
            mockedTransport.when(() -> Transport.send(any(MimeMessage.class)))
                    .thenThrow(new MessagingException("SMTP error"));

            boolean result = otpService.sendEmail("invalid_email", "123456");
            assertFalse(result);
        }
    }
}