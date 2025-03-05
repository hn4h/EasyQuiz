/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.UserStatis;

/**
 *
 * @author 11
 */
public class StatisDAO extends DBContext {

    public int getNumberOfQuiz() {
        String sql = "select count(*) from Quiz_Set";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    public int getNumberOfUser() {
        try {
            String sql = "select count(*) from Accounts";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    public int getNumberOfTransaction() {
        try {
            String sql = "select count(*) from Transaction_History";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    public double getTotalRevenue() {
        try {
            String sql = "SELECT sum(t.Transaction_Amount)\n"
                    + "  FROM [EasyQuiz].[dbo].[Transaction_History] h\n"
                    + "  join [Transaction] t on t.Transaction_ID = h.Transaction_ID";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    public List<Account> getNewCreatedUser() {
        List<Account> list = new ArrayList<>();
        try {
            String sql = "select top (6) UserName, profileImage from Accounts order by CreatedDate desc";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Account a = new Account();
                a.setUserName(rs.getString("UserName"));
                a.setProfileImage(rs.getString("profileImage"));
                list.add(a);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<UserStatis> getAllUserStatistics() {
        List<UserStatis> list = new ArrayList<>();
        try {
            String sql = "select a.UserName, a.ProfileImage, a.Email, Count(f.Folder_ID) as NumOfFolder, Count(b.Blog_ID) as NumOfBlog, COUNT(qs.Quiz_Set_ID) as NumOfQuiz, COUNT(c.Comment_ID) as NumOfComment, COUNT(fb.Feedback_ID) as NumOfFeedBack from Accounts a\n"
                    + "left join Folder f on a.UserName = f.UserName\n"
                    + "left join Blog b on a.UserName = b.Author\n"
                    + "left join Quiz_Set qs on a.UserName = qs.Author\n"
                    + "left join Comment c on a.UserName = c.UserName\n"
                    + "left join Feedback fb on a.UserName = fb.UserName\n"
                    + "group by a.UserName, a.ProfileImage, a.Email";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                UserStatis u = new UserStatis();
                u.setUserName(rs.getString("UserName"));
                u.setEmail(rs.getString("Email"));
                u.setImageProfile(rs.getString("ProfileImage"));
                u.setNumBlog(rs.getInt("NumOfBlog"));
                u.setNumComment(rs.getInt("NumOfComment"));
                u.setNumFeedBack(rs.getInt("NumOfFeedBack"));
                u.setNumFolder(rs.getInt("NumOfFolder"));
                u.setNumQuizSet(rs.getInt("NumOfQuiz"));
                list.add(u);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    // public static void main(String[] args) {
    // StatisDAO d = new StatisDAO();
    // for (ChartColumn c : d.getRevenueLast7Days()) {
    // System.out.println(c.getColumnName() + " " + c.getValue());
    // }
    // }
    //
    // public List<ChartColumn> getRevenueThisYear() {
    // List<ChartColumn> list = new ArrayList<>();
    // try {
    // String sql = "select MONTH(o.OrderedDate) as Month, sum(c.quantity * i.price)
    // as Revenue\n"
    // + "from Orders o\n"
    // + "join OrderItems i on o.OrderID = i.OrderID\n"
    // + "join CartItems c on c.CartItemID = i.CartItemID \n"
    // + "where YEAR (o.OrderedDate) = YEAR(GETDATE()) and o.status =
    // 'Successful'\n"
    // + "group by MONTH(o.OrderedDate)";
    // PreparedStatement st = connection.prepareStatement(sql);
    // ResultSet rs = st.executeQuery();
    // while (rs.next()) {
    // ChartColumn c = new ChartColumn(String.valueOf(rs.getInt(1)), rs.getInt(2));
    // list.add(c);
    // }
    // } catch (SQLException e) {
    // System.out.println(e);
    // }
    // for (int i = 1; i <= 12; i++) {
    // boolean check = false;
    // for (ChartColumn c : list) {
    // if (Integer.parseInt(c.getColumnName()) == i) {
    // check = true;
    // break;
    // }
    // }
    // if (!check) {
    // ChartColumn c = new ChartColumn(String.valueOf(i), 0);
    // list.add(c);
    // }
    // }
    // list.sort((a, b) -> Integer.parseInt(a.getColumnName()) -
    // Integer.parseInt(b.getColumnName()));
    // return list;
    // }
    //
    // public List<ChartColumn> getRevenueLast7Days() {
    // List<ChartColumn> list = new ArrayList<>();
    // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    //
    // try {
    // String sql = "select DATEDIFF(DAY, o.OrderedDate, GETDATE()) as DaysAgo, "
    // + "sum(c.quantity * i.price) as Revenue "
    // + "from Orders o "
    // + "join OrderItems i on o.OrderID = i.OrderID "
    // + "join CartItems c on c.CartItemID = i.CartItemID "
    // + "where DATEDIFF(DAY, o.OrderedDate, GETDATE()) <= 6 and o.status =
    // 'Successful' "
    // + "group by DATEDIFF(DAY, o.OrderedDate, GETDATE())";
    // PreparedStatement st = connection.prepareStatement(sql);
    // ResultSet rs = st.executeQuery();
    // while (rs.next()) {
    // int daysAgo = rs.getInt("DaysAgo");
    // String columnName = LocalDate.now().minusDays(daysAgo).format(formatter);
    // ChartColumn c = new ChartColumn(columnName, rs.getInt("Revenue"));
    // list.add(c);
    // }
    // } catch (SQLException e) {
    // System.out.println(e);
    // }
    //
    // // Fill in any missing days in the last 7 days
    // for (int i = 0; i <= 6; i++) { // Representing 0 to 6 days ago
    // String columnName = LocalDate.now().minusDays(i).format(formatter);
    // boolean check = false;
    // for (ChartColumn c : list) {
    // if (c.getColumnName().equals(columnName)) {
    // check = true;
    // break;
    // }
    // }
    // if (!check) {
    // ChartColumn c = new ChartColumn(columnName, 0);
    // list.add(c);
    // }
    // }
    //
    // // Sort the list by date in ascending order
    // list.sort((a, b) -> LocalDate.parse(a.getColumnName(), formatter)
    // .compareTo(LocalDate.parse(b.getColumnName(), formatter)));i
    //
    // return list;
    // }
    // public List<ChartColumn> getRevenueLast30Days() {
    // List<ChartColumn> list = new ArrayList<>();
    // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    //
    // try {
    // String sql = "select DATEDIFF(DAY, o.OrderedDate, GETDATE()) as DaysAgo, "
    // + "sum(c.quantity * i.price) as Revenue "
    // + "from Orders o "
    // + "join OrderItems i on o.OrderID = i.OrderID "
    // + "join CartItems c on c.CartItemID = i.CartItemID "
    // + "where DATEDIFF(DAY, o.OrderedDate, GETDATE()) <= 29 and o.status =
    // 'Successful' "
    // + "group by DATEDIFF(DAY, o.OrderedDate, GETDATE())";
    // PreparedStatement st = connection.prepareStatement(sql);
    // ResultSet rs = st.executeQuery();
    // while (rs.next()) {
    // int daysAgo = rs.getInt("DaysAgo");
    // String columnName = LocalDate.now().minusDays(daysAgo).format(formatter);
    // ChartColumn c = new ChartColumn(columnName, rs.getInt("Revenue"));
    // list.add(c);
    // }
    // } catch (SQLException e) {
    // System.out.println(e);
    // }
    //
    // // Fill in any missing days in the last 30 days
    // for (int i = 0; i <= 29; i++) { // Representing 0 to 29 days ago
    // String columnName = LocalDate.now().minusDays(i).format(formatter);
    // boolean check = false;
    // for (ChartColumn c : list) {
    // if (c.getColumnName().equals(columnName)) {
    // check = true;
    // break;
    // }
    // }
    // if (!check) {
    // ChartColumn c = new ChartColumn(columnName, 0);
    // list.add(c);
    // }
    // }
    //
    // // Sort the list by date in ascending order
    // list.sort((a, b) -> LocalDate.parse(a.getColumnName(), formatter)
    // .compareTo(LocalDate.parse(b.getColumnName(), formatter)));
    //
    // return list;
    // }
    // public List<ChartColumn> getRevenueLast90Days() {
    // List<ChartColumn> list = new ArrayList<>();
    // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    //
    // try {
    // String sql = "select DATEDIFF(DAY, o.OrderedDate, GETDATE()) as DaysAgo, "
    // + "sum(c.quantity * i.price) as Revenue "
    // + "from Orders o "
    // + "join OrderItems i on o.OrderID = i.OrderID "
    // + "join CartItems c on c.CartItemID = i.CartItemID "
    // + "where DATEDIFF(DAY, o.OrderedDate, GETDATE()) <= 89 and o.status =
    // 'Successful' "
    // + "group by DATEDIFF(DAY, o.OrderedDate, GETDATE())";
    // PreparedStatement st = connection.prepareStatement(sql);
    // ResultSet rs = st.executeQuery();
    // while (rs.next()) {
    // int daysAgo = rs.getInt("DaysAgo");
    // String columnName = LocalDate.now().minusDays(daysAgo).format(formatter);
    // ChartColumn c = new ChartColumn(columnName, rs.getInt("Revenue"));
    // list.add(c);
    // }
    // } catch (SQLException e) {
    // System.out.println(e);
    // }
    //
    // // Fill in any missing days in the last 90 days
    // for (int i = 0; i <= 89; i++) { // Representing 0 to 89 days ago
    // String columnName = LocalDate.now().minusDays(i).format(formatter);
    // boolean check = false;
    // for (ChartColumn c : list) {
    // if (c.getColumnName().equals(columnName)) {
    // check = true;
    // break;
    // }
    // }
    // if (!check) {
    // ChartColumn c = new ChartColumn(columnName, 0);
    // list.add(c);
    // }
    // }
    //
    // // Sort the list by date in ascending order
    // list.sort((a, b) -> LocalDate.parse(a.getColumnName(), formatter)
    // .compareTo(LocalDate.parse(b.getColumnName(), formatter)));
    //
    // return list;
    // }
    // public List<Account> getPotentialCustomers() {
    // List<Account> list = new ArrayList<>();
    // try {
    // String sql = "select top 3 sum(c.quantity * i.price) as Revenue, a.UserName ,
    // a.FirstName, a.LastName, a.ProfileImage from Orders o join OrderItems i on
    // o.OrderID = i.OrderID join CartItems c on c.CartItemID = i.CartItemID join
    // Accounts a on a.AccountID = o.AccountID where o.status = 'Successful' group
    // by a.UserName, a.FirstName, a.LastName, a.ProfileImage order by Revenue
    // desc";
    // PreparedStatement st = connection.prepareStatement(sql);
    // ResultSet rs = st.executeQuery();
    // while (rs.next()) {
    // Account a = new Account();
    // a.setUserName(rs.getString("Username"));
    // a.setFirstName(rs.getString("FirstName"));
    // a.setLastName(rs.getString("LastName"));
    // a.setProfileImage(rs.getString("ProfileImage"));
    // list.add(a);
    // }
    // } catch (SQLException e) {
    // System.out.println(e);
    // }
    // return list;
    // }
}
