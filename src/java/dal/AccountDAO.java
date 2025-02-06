/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import model.Account;
import model.PasswordUtil;

/**
 *
 * @author 11
 */
public class AccountDAO extends DBContext{
//    public Account getAccountByEmail(String email) {
//        String sql = "Select * from Accounts where email = ?";
//        try {
//            PreparedStatement st = connection.prepareStatement(sql)
//            st.setString(1, email);
//            ResultSet rs = st.executeQuery();
//            if (rs.next()) {
//                Account account = new Account();
//                account.setUserName(rs.getString("username"));
//                account.setHashedPassword(rs.getString("HashedPassword"));
//                account.setCreatedDate(rs.getDate("createdDate"));
//                account.setProfileImage(rs.getString("profileImage"));
//                account.setRole(rs.getInt("role"));
//                account.setEmail(rs.getString("email"));
//                return account;
//            }
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
//        return null;
//    }
    public Account checkAuthen(String email, String password) {
        String sql = "Select * from Accounts where Email = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                if (PasswordUtil.verifyPassword(password, rs.getString("HashedPassword"))) {
                    Account account = new Account();
                    account.setUserName(rs.getString("username"));
                    account.setHashedPassword(rs.getString("HashedPassword"));
                    account.setCreatedDate(rs.getDate("createdDate"));
                    account.setProfileImage(rs.getString("profileImage"));
                    account.setRole(rs.getInt("role"));
                    account.setEmail(rs.getString("email"));
                    return account;
                }
            }
            return null;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    public void createAccount(String username, String password, String email) {
        String hashedPassword = PasswordUtil.hashPassword(password);
        String sql = "INSERT INTO Accounts(UserName, HashedPassword, role, email) values (?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, hashedPassword);
            st.setInt(3, 0);
            st.setString(4, email);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
   public void createAccountByEmail(String email){
        String prefix = "EasyQuiz";
        Random random = new Random();
        int randomNumber = 100000 + random.nextInt(900000); // Generates a 4-digit random number
        String username = prefix + randomNumber;
        while (this.checkUsername(username)) {
            randomNumber = 100000 + random.nextInt(900000);
            username = prefix + randomNumber;
        }
        this.createAccount(username, "", email);
   }

    public boolean checkUsername(String username) {
        String sql = "Select * from Accounts where username = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
    

    public int getNextAccountID() {
        String sql = "Select max(AccountID) from Accounts";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) + 1;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    public static void main(String[] args) {
        AccountDAO dao = new AccountDAO();
        // dao.createAccount("admin", "", "easiquiz@gmail.com");
        dao.createAccountByEmail("test12@gmail.com");            
    }

    public void updateProfile(String firstname, String lastname, String phoneNumber, String address, int accountID) {
        String sql = "Update Accounts set FirstName = ?, LastName = ?, phoneNumber = ?, address = ? where AccountID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, firstname);
            st.setString(2, lastname);
            st.setString(3, phoneNumber);
            st.setString(4, address);
            st.setInt(5, accountID);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public void banUser(int accountID) {
        String sql = "Update Accounts set role = 0 where AccountID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, accountID);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public void unbanUser(int accountID) {
        String sql = "Update Accounts set role = 1 where AccountID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, accountID);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
//    public List<Account> getAccountOfCustomerByAdmin(){
//        List<Account> accounts = new ArrayList<>();
//        String sql = "Select * from Accounts where role = 1 or role = 0";
//        try {
//            PreparedStatement st = connection.prepareStatement(sql);
//            ResultSet rs = st.executeQuery();
//            while (rs.next()) {
//                Account account = new Account();
//                account.setUserName(rs.getString("username"));
//                account.setHashedPassword(rs.getString("HashedPassword"));
//                account.setAccountID(rs.getInt("accountID"));
//                account.setCreatedDate(rs.getDate("createdDate"));
//                account.setProfileImage(rs.getString("profileImage"));
//                account.setRole(rs.getInt("role"));
//                account.setPhoneNumber(rs.getString("phoneNumber"));
//                account.setAddress(rs.getString("address"));
//                account.setEmail(rs.getString("email"));
//                account.setFirstName(rs.getString("firstName"));
//                account.setLastName(rs.getString("lastName"));
//                accounts.add(account);
//            }
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
//        return accounts;
//    }
//    public Account getAccountByID(int accountID) {
//        String sql = "Select * from Accounts where AccountID = ?";
//        try {
//            PreparedStatement st = connection.prepareStatement(sql);
//            st.setInt(1, accountID);
//            ResultSet rs = st.executeQuery();
//            if (rs.next()) {
//                Account account = new Account();
//                account.setUserName(rs.getString("username"));
//                account.setHashedPassword(rs.getString("HashedPassword"));
//                account.setAccountID(rs.getInt("accountID"));
//                account.setCreatedDate(rs.getDate("createdDate"));
//                account.setProfileImage(rs.getString("profileImage"));
//                account.setRole(rs.getInt("role"));
//                account.setPhoneNumber(rs.getString("phoneNumber"));
//                account.setAddress(rs.getString("address"));
//                account.setEmail(rs.getString("email"));
//                account.setFirstName(rs.getString("firstName"));
//                account.setLastName(rs.getString("lastName"));
//                return account;
//            }
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
//        return null;
//    }

    public int getAccountIDByUsername(String username) {
        String sql = "Select AccountID from Accounts where username = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 1;
    }

    public boolean changePassword(String username, String oldPassword, String newPassword) {
        Account account = checkAuthen(username, oldPassword);
        if (account == null) {
            return false;
        }
        String hashedPassword = PasswordUtil.hashPassword(newPassword);
        String sql = "Update Accounts set HashedPassword = ? where username = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, hashedPassword);
            st.setString(2, username);
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

//    public boolean updateProfileImage(InputStream inputStream, Part filePart, int accountID) {
//        String sql = "Update Accounts set profileImage = ? where AccountID = ?";
//        try {
//            PreparedStatement st = connection.prepareStatement(sql);
//            if (inputStream != null) {
//                // Check if the size is within the int range
//                if (filePart.getSize() <= Integer.MAX_VALUE) {
//                    st.setBinaryStream(1, inputStream, (int) filePart.getSize());
//                } else {
//                    return false;
//                }
//            }
//            st.setInt(2, accountID);
//
//            // Execute the SQL statement
//            int row = st.executeUpdate();
//            if (row > 0) {
//                return true;
//            }
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
//        return false;
//    }

   public Account checkEmail(String email) {
       String sql = "Select * from Accounts where email = ?";
       try {
           PreparedStatement st = connection.prepareStatement(sql);
           st.setString(1, email);
           ResultSet rs = st.executeQuery();
           if (rs.next()) {
               Account account = new Account();
               account.setUserName(rs.getString("username"));
               account.setHashedPassword(rs.getString("HashedPassword"));
               account.setCreatedDate(rs.getDate("createdDate"));
               account.setProfileImage(rs.getString("profileImage"));
               account.setRole(rs.getInt("role"));
               account.setEmail(rs.getString("email"));
               return account;
           }
       } catch (SQLException e) {
           System.out.println(e);
       }
       return null;
   }
    public void resetPassword(String email, String newPassword) {
        String hashedPassword = PasswordUtil.hashPassword(newPassword);
        String sql = "Update Accounts set HashedPassword = ? where email = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, hashedPassword);
            st.setString(2, email);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
