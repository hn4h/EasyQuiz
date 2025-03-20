/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import model.Account;
import model.Creator;
import model.PasswordUtil;

/**
 *
 * @author 11
 */
public class AccountDAO extends DBContext {

    public Account getAccountByEmail(String email) {
        String sql = "Select * from Accounts where email = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Account account = new Account();
                account.setUserName(rs.getString("username"));
                account.setProfileImage(rs.getString("profileImage"));
                account.setEmail(rs.getString("email"));
                return account;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

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
                    boolean isAdmin = rs.getInt("is_admin") == 1;
                    account.setIsAdmin(isAdmin);
                    account.setExpiredDate(rs.getDate("expiredDate"));
                    account.setEmail(rs.getString("email"));
                    if (rs.getInt("is_deleted") == 1) {
                        return null;
                    } else {
                        return account;
                    }
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
        Random random = new Random();
        int randomNumber = random.nextInt(14) + 1;
        String imageLink = "./images/avatar/avt" + randomNumber + ".jpg";
        String sql = "INSERT INTO Accounts(UserName, HashedPassword, email, profileImage) values (?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, hashedPassword);
            st.setString(3, email);
            st.setString(4, imageLink);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void createAccountWithGoogle(String username, String password, String email, String image) {
        String hashedPassword = PasswordUtil.hashPassword(password);
        String sql = "INSERT INTO Accounts(UserName, HashedPassword, email, profileImage) values (?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, hashedPassword);
            st.setString(3, email);
            st.setString(4, image);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void createAccountByEmail(String email, String image) {
        String prefix = "EasyQuiz";
        Random random = new Random();
        int randomNumber = 100000 + random.nextInt(900000); // Generates a 4-digit random number
        String username = prefix + randomNumber;
        while (this.checkUsername(username)) {
            randomNumber = 100000 + random.nextInt(900000);
            username = prefix + randomNumber;
        }
        this.createAccountWithGoogle(username, "", email, image);
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

    public void updateProfile(String userName, String profileImage) {
        String sql = "Update Accounts set userName = ?, profileImage = ? where userName = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, userName);
            st.setString(2, profileImage);
            st.setString(3, userName);
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

    public Account getAccountByUserName(String userName) {
        try {
            String sql = "Select * from Accounts where UserName = ? AND is_deleted = 0";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, userName);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Account account = new Account();
                account.setUserName(rs.getString("username"));
                account.setCreatedDate(rs.getDate("createdDate"));
                account.setProfileImage(rs.getString("profileImage"));
                boolean isAdmin = rs.getInt("is_admin") == 1;
                account.setIsAdmin(isAdmin);
                account.setEmail(rs.getString("email"));
                return account;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public boolean updateProfileImage(String userName, String profileImage) {
        String sql = "UPDATE Accounts SET ProfileImage = ? WHERE UserName = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, profileImage);
            st.setString(2, userName);
            System.out.println("Executing SQL: " + sql);
            System.out.println("ProfileImage: " + profileImage + ", UserName: " + userName);
            int rowsAffected = st.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error updating profile image: " + e.getMessage());
            return false;
        }
    }

    public boolean updateUsername(String oldUserName, String newUserName) {
        String sql = "UPDATE Accounts SET UserName = ? WHERE UserName = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, newUserName);
            st.setString(2, oldUserName);
            System.out.println("Executing SQL: " + sql);
            System.out.println("New UserName: " + newUserName + ", Old UserName: " + oldUserName);
            int rowsAffected = st.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error updating username: " + e.getMessage());
            return false;
        }
    }

    public boolean changePassword(String email, String oldPassword, String newPassword) {
        Account account = checkAuthen(email, oldPassword);
        if (account == null) {
            return false;
        }
        String hashedPassword = PasswordUtil.hashPassword(newPassword);
        String sql = "Update Accounts set HashedPassword = ? where username = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, hashedPassword);
            st.setString(2, account.getUserName());
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    //return false if expiredDate < GETDATE()
    public boolean checkPremium(String userName) {
        String sql = "Select * from Accounts where expiredDate < GETDATE() and username = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, userName);
            ResultSet rs = st.executeQuery();
            return !rs.next();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return true;
    }

    public void updateExpiredDate(String userName, int value) {
        String sql = "Update Accounts set expiredDate = ";
        try {

            if (this.checkPremium(userName)) {
                sql += "DATEADD(DAY," + value + ", expiredDate)";
            } else {
                sql += "DATEADD(DAY," + value + ", GETDATE())";
            }
            sql += "where username = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, userName);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

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
                boolean isAdmin = rs.getInt("is_admin") == 1;
                account.setIsAdmin(isAdmin);
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

    public List<Creator> getTopQuizSetCreator() {
        List<Creator> list = new ArrayList<>();
        String sql = "SELECT Top(9) a.UserName, a.ProfileImage, count(qs.Quiz_Set_ID) as NumberOfQuizSet\n"
                + "FROM Accounts a\n"
                + "join Quiz_Set qs on qs.Author = a.UserName\n"
                + "group by a.UserName, a.ProfileImage\n"
                + "order by count(qs.Quiz_Set_ID) desc";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account a = new Account();
                a.setUserName(rs.getString("UserName"));
                a.setProfileImage(rs.getString("ProfileImage"));
                Creator c = new Creator();
                c.setAccount(a);
                c.setNumberOfQuizSet(rs.getInt("NumberOfQuizSet"));
                list.add(c);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Creator> searchAllCreator(String input) {
        List<Creator> list = new ArrayList<>();
        String sql = "SELECT a.UserName, a.ProfileImage, count(qs.Quiz_Set_ID) as NumberOfQuizSet\n"
                + "FROM Accounts a\n"
                + "left join Quiz_Set qs on qs.Author = a.UserName\n"
                + "where UserName like '%' + ? + '%'"
                + "group by a.UserName, a.ProfileImage\n"
                + "order by count(qs.Quiz_Set_ID) desc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, input);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Account a = new Account();
                a.setUserName(rs.getString("UserName"));
                a.setProfileImage(rs.getString("ProfileImage"));
                Creator c = new Creator();
                c.setAccount(a);
                c.setNumberOfQuizSet(rs.getInt("NumberOfQuizSet"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public void deleteAccount(String userName) {
        String sql = "Update Accounts set is_deleted = 1 where username = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, userName);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        AccountDAO d = new AccountDAO();
        System.out.println(d.checkLimitTest("haha"));
    }

    public boolean checkLimitLearn(String userName) {
        String sql = "SELECT * from Accounts_Limit where UserName = ? and [Date] = CAST(GETDATE() AS DATE)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, userName);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                // Nếu có kết quả từ SELECT, chỉ thực thi UPDATE
                int learnLimit = rs.getInt("Learn_limit");
                if (learnLimit == 0) {
                    return false;
                }
                String updateSql = "UPDATE Accounts_Limit SET Learn_limit = Learn_limit - 1 WHERE UserName = ? and [Date] = CAST(GETDATE() AS DATE)";
                st = connection.prepareStatement(updateSql);
                st.setString(1, userName);
                st.executeUpdate();
            } else {
                // Nếu không có kết quả, thực thi INSERT
                String insertSql = "INSERT INTO Accounts_Limit(UserName, Date, Learn_limit, Test_limit) VALUES (?, GETDATE(), 4, 5)";
                st = connection.prepareStatement(insertSql);
                st.setString(1, userName);
                st.executeUpdate();
            }
            return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean checkLimitTest(String userName) {
        String sql = "SELECT * from Accounts_Limit where UserName = ? and [Date] = CAST(GETDATE() AS DATE)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, userName);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                // Nếu có kết quả từ SELECT, chỉ thực thi UPDATE
                int learnLimit = rs.getInt("Test_limit");
                if (learnLimit == 0) {
                    return false;
                }
                String updateSql = "UPDATE Accounts_Limit SET Test_limit = Test_limit - 1 WHERE UserName = ? and [Date] = CAST(GETDATE() AS DATE)";
                st = connection.prepareStatement(updateSql);
                st.setString(1, userName);
                st.executeUpdate();
            } else {
                // Nếu không có kết quả, thực thi INSERT
                String insertSql = "INSERT INTO Accounts_Limit(UserName, Date, Learn_limit, Test_limit) VALUES (?, GETDATE(), 5, 4)";
                st = connection.prepareStatement(insertSql);
                st.setString(1, userName);
                st.executeUpdate();
            }
            return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
}
