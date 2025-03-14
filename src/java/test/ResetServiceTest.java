package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import service.ResetService;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ResetServiceTest {
    private ResetService resetService;

    @BeforeEach
    void setUp() {
        resetService = new ResetService();
    }

    @Test
    void testGenerateToken() {
        String token1 = resetService.generateToken();
        String token2 = resetService.generateToken();

        assertNotNull(token1);
        assertNotNull(token2);
        assertNotEquals(token1, token2); // Token phải là duy nhất
    }

    @Test
    void testExpireDateTime() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiry = resetService.expireDateTime();

        assertNotNull(expiry);
        assertTrue(expiry.isAfter(now));
        assertTrue(expiry.isBefore(now.plusMinutes(6))); // Chỉ lệch tối đa 5 phút
    }

    @Test
    void testIsExpireTime_Expired() {
        LocalDateTime pastTime = LocalDateTime.now().minusMinutes(10);
        assertTrue(resetService.isExpireTime(pastTime));
    }

    @Test
    void testIsExpireTime_NotExpired() {
        LocalDateTime futureTime = LocalDateTime.now().plusMinutes(2);
        assertFalse(resetService.isExpireTime(futureTime));
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

            boolean result = resetService.sendEmail("test@example.com", "http://resetlink.com", "John Doe");
            assertTrue(result);
        }
    }

    @Test
    void testSendEmail_Failure() throws Exception {
        try (MockedStatic<Transport> mockedTransport = mockStatic(Transport.class)) {
            mockedTransport.when(() -> Transport.send(any(MimeMessage.class)))
                    .thenThrow(new MessagingException("SMTP error"));

            boolean result = resetService.sendEmail("invalid_email", "http://resetlink.com", "John Doe");
            assertFalse(result);
        }
    }
}