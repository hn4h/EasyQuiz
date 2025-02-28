/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package dal;

import model.TokenForgetPassword;
import org.junit.Test;
import static org.junit.Assert.*;
import service.ResetService;

/**
 *
 * @author 11
 */
public class TokenForgetDAOTest {

    TokenForgetDAO dao;
    public TokenForgetDAOTest() {
        dao = new TokenForgetDAO();
    }
    

    @Test
    public void testInsertTokenForget() {
        ResetService service = new ResetService();
        TokenForgetPassword newTokenForget = new TokenForgetPassword(
                "admin", false, service.generateToken(), service.expireDateTime());
        boolean expectedResult = false;
        boolean rs = dao.insertTokenForget(newTokenForget);
        assertEquals(expectedResult, this);
        
    }

    @Test
    public void testGetTokenPassword() {
    }

    @Test
    public void testUpdateStatus() {
    }
    
}
