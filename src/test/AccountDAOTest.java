/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package dal;

import model.Account;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 11
 */
public class AccountDAOTest {
    
    AccountDAO dao;
    public AccountDAOTest() {
        dao = new AccountDAO();
    }

    @Test
    public void testGetAccountByEmail() {
        Account expectedAccount = new Account();
        expectedAccount.setUserName("EasyQuiz455934");
        expectedAccount.setEmail("hanh113004@gmail.com");
        expectedAccount.setProfileImage("images/avatar/default.png");
        Account rs = dao.getAccountByEmail("hanh113004@gmail.com");
        assertEquals(expectedAccount.getUserName(), rs.getUserName());
        assertEquals(expectedAccount.getEmail(), rs.getEmail());
        assertEquals(expectedAccount.getProfileImage(), rs.getProfileImage());
    }

    @Test
    public void testCheckAuthen() {
        Account expectedAccount = new Account();
        expectedAccount.setUserName("12");
        expectedAccount.setEmail("ducafthnah@gmail.com");
        expectedAccount.setProfileImage("images/avatar/default.png");
        expectedAccount.setIsAdmin(false);
        Account rs = dao.checkAuthen("ducafthnah@gmail.com", "123");
        assertEquals(expectedAccount.getUserName(), rs.getUserName());
        assertEquals(expectedAccount.getEmail(), rs.getEmail());
        assertEquals(expectedAccount.getProfileImage(), rs.getProfileImage());
        assertEquals(expectedAccount.isIsAdmin(), rs.isIsAdmin());
    }

    @Test
    public void testCreateAccount() {
        Account exAccount = new Account();
        exAccount.setUserName("test");
        exAccount.setEmail("test@gmail.com");
        dao.createAccount("test","456", "test@gmail.com");
        Account rs = dao.getAccountByEmail("test@gmail.com");
        assertEquals(exAccount.getUserName(), rs.getUserName());
        assertEquals(exAccount.getEmail(), rs.getEmail());
    }

    @Test
    public void testCheckUsername() {
        boolean rs = dao.checkUsername("test");
        boolean exRs = true;
        assertEquals(exRs, rs);
    }

    @Test
    public void testUpdateProfile() {
        Account exAccount = new Account();
        exAccount.setUserName("test");
        exAccount.setProfileImage("images/avatar/test.png");
        dao.updateProfile("test", "images/avatar/test.png");
        Account rs = dao.getAccountByEmail("test@gmail.com");
        assertEquals(exAccount.getUserName(), rs.getUserName());
        assertEquals(exAccount.getProfileImage(), rs.getProfileImage());
    }



    
}
