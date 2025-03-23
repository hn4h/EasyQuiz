/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.ChartColumn;
import model.Payment;
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
    
    public int getNumberOfFeedback() {
        String sql = "select count(*) from Feedback";
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

    public long getTotalRevenue() {
        try {
            String sql = "select sum(Amount) from Transaction_History\n"
                    + "where Status = 'PAID'";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getLong(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    public List<Account> getNewCreatedUser() {
        List<Account> list = new ArrayList<>();
        try {
            String sql = "select top (5) UserName, profileImage from Accounts order by CreatedDate desc";
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

    public List<Payment> getNewPayments() {
        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT Top(6) * FROM Transaction_History where Status = 'PAID' ORDER BY Created_Date DESC";
        try (
                PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                payments.add(mapPayment(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving payments: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error retrieving payments: " + e.getMessage(), e);
        }
        return payments;
    }

    private Payment mapPayment(ResultSet rs) throws SQLException {
        Payment payment = new Payment();
        payment.setTransactionId(rs.getString("transaction_id"));
        payment.setOrderCode(rs.getString("order_code"));
        payment.setAmount(rs.getInt("amount"));
        payment.setStatus(rs.getString("status"));
        payment.setDescription(rs.getString("description"));
        payment.setUserName(rs.getString("UserName"));
        payment.setCreatedDate(rs.getDate("Created_Date"));
        return payment;
    }

    public List<ChartColumn> getRevenueThisYear() {
        List<ChartColumn> list = new ArrayList<>();
        try {
            String sql = "select MONTH(Created_Date) as Month, sum(Amount)as Revenue\n"
                    + "from Transaction_History\n"
                    + "where YEAR (Created_Date) = YEAR(GETDATE()) and status = 'PAID'\n"
                    + "group by MONTH(Created_Date)";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ChartColumn c = new ChartColumn(String.valueOf(rs.getInt(1)), rs.getInt(2));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        for (int i = 1; i <= 12; i++) {
            boolean check = false;
            for (ChartColumn c : list) {
                if (Integer.parseInt(c.getColumnName()) == i) {
                    check = true;
                    break;
                }
            }
            if (!check) {
                ChartColumn c = new ChartColumn(String.valueOf(i), 0);
                list.add(c);
            }
        }
        list.sort((a, b) -> Integer.parseInt(a.getColumnName())
                - Integer.parseInt(b.getColumnName()));
        return list;
    }

    public List<ChartColumn> getRevenueLast7Days() {
        List<ChartColumn> list = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            String sql = "select DATEDIFF(DAY, Created_Date, GETDATE()) as DaysAgo, \n"
                    + "sum(Amount) as Revenue \n"
                    + "from Transaction_History\n"
                    + "where DATEDIFF(DAY, Created_Date, GETDATE()) <= 6 and status = 'PAID' \n"
                    + "group by DATEDIFF(DAY, Created_Date, GETDATE())";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int daysAgo = rs.getInt("DaysAgo");
                String columnName = LocalDate.now().minusDays(daysAgo).format(formatter);
                ChartColumn c = new ChartColumn(columnName, rs.getInt("Revenue"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        // Fill in any missing days in the last 7 days
        for (int i = 0; i <= 6; i++) { // Representing 0 to 6 days ago
            String columnName = LocalDate.now().minusDays(i).format(formatter);
            boolean check = false;
            for (ChartColumn c : list) {
                if (c.getColumnName().equals(columnName)) {
                    check = true;
                    break;
                }
            }
            if (!check) {
                ChartColumn c = new ChartColumn(columnName, 0);
                list.add(c);
            }
        }

        // Sort the list by date in ascending order
        list.sort((a, b) -> LocalDate.parse(a.getColumnName(), formatter)
                .compareTo(LocalDate.parse(b.getColumnName(), formatter)));
        return list;
    }

    public List<ChartColumn> getRevenueLast30Days() {
        List<ChartColumn> list = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            String sql = "select DATEDIFF(DAY, Created_Date, GETDATE()) as DaysAgo, \n"
                    + "                    sum(Amount) as Revenue \n"
                    + "                    from Transaction_History\n"
                    + "                    where DATEDIFF(DAY, Created_Date, GETDATE()) <= 29 and status = 'PAID' \n"
                    + "                    group by DATEDIFF(DAY, Created_Date, GETDATE())";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int daysAgo = rs.getInt("DaysAgo");
                String columnName = LocalDate.now().minusDays(daysAgo).format(formatter);
                ChartColumn c = new ChartColumn(columnName, rs.getInt("Revenue"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        // Fill in any missing days in the last 30 days
        for (int i = 0; i <= 29; i++) { // Representing 0 to 29 days ago
            String columnName = LocalDate.now().minusDays(i).format(formatter);
            boolean check = false;
            for (ChartColumn c : list) {
                if (c.getColumnName().equals(columnName)) {
                    check = true;
                    break;
                }
            }
            if (!check) {
                ChartColumn c = new ChartColumn(columnName, 0);
                list.add(c);
            }
        }

        // Sort the list by date in ascending order
        list.sort((a, b) -> LocalDate.parse(a.getColumnName(), formatter)
                .compareTo(LocalDate.parse(b.getColumnName(), formatter)));

        return list;
    }

//    public List<ChartColumn> getRevenueLast90Days() {
//        List<ChartColumn> list = new ArrayList<>();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//
//        try {
//            String sql = "SELECT \n"
//                    + "    FLOOR(DATEDIFF(DAY, Created_Date, GETDATE()) / 7) AS DaysAgo,\n"
//                    + "    SUM(Amount) AS Revenue\n"
//                    + "FROM Transaction_History\n"
//                    + "WHERE DATEDIFF(DAY, Created_Date, GETDATE()) <= 89 AND status = 'PAID'\n"
//                    + "GROUP BY FLOOR(DATEDIFF(DAY, Created_Date, GETDATE()) / 7)\n"
//                    + "ORDER BY DaysAgo;";
//            PreparedStatement st = connection.prepareStatement(sql);
//            ResultSet rs = st.executeQuery();
//            while (rs.next()) {
//                int daysAgo = rs.getInt("DaysAgo");
//                String columnName = LocalDate.now().minusDays(daysAgo).format(formatter);
//                ChartColumn c = new ChartColumn(columnName, rs.getInt("Revenue"));
//                list.add(c);
//            }
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
//
//        // Fill in any missing days in the last 90 days
//        for (int i = 0; i <= 89; i++) { // Representing 0 to 89 days ago
//            String columnName = LocalDate.now().minusDays(i).format(formatter);
//            boolean check = false;
//            for (ChartColumn c : list) {
//                if (c.getColumnName().equals(columnName)) {
//                    check = true;
//                    break;
//                }
//            }
//            if (!check) {
//                ChartColumn c = new ChartColumn(columnName, 0);
//                list.add(c);
//            }
//        }
//
//        // Sort the list by date in ascending order
//        list.sort((a, b) -> LocalDate.parse(a.getColumnName(), formatter)
//                .compareTo(LocalDate.parse(b.getColumnName(), formatter)));
//
//        return list;
//    }
    public List<ChartColumn> getRevenueLast90Days() {
    List<ChartColumn> list = new ArrayList<>();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    try {
        // SQL query để lấy ngày đầu tuần và tổng doanh thu
        String sql = "SELECT \n"
                + "    DATEADD(DAY, -DATEDIFF(DAY, '1970-01-05', Created_Date) % 7, Created_Date) AS WeekStart,\n"
                + "    SUM(Amount) AS Revenue\n"
                + "FROM Transaction_History\n"
                + "WHERE DATEDIFF(DAY, Created_Date, GETDATE()) <= 89 AND Status = 'PAID'\n"
                + "GROUP BY DATEADD(DAY, -DATEDIFF(DAY, '1970-01-05', Created_Date) % 7, Created_Date)\n"
                + "ORDER BY WeekStart;";
        PreparedStatement st = connection.prepareStatement(sql);
        ResultSet rs = st.executeQuery();

        // Thêm dữ liệu từ database vào danh sách
        while (rs.next()) {
            LocalDate weekStart = rs.getDate("WeekStart").toLocalDate();
            String columnName = weekStart.format(formatter);
            ChartColumn c = new ChartColumn(columnName, rs.getInt("Revenue"));
            list.add(c);
        }
    } catch (SQLException e) {
        System.out.println(e);
    }

    // Tạo danh sách đầy đủ 12 tuần gần nhất và điền giá trị mặc định 0 nếu tuần không có dữ liệu
    LocalDate today = LocalDate.now();
    LocalDate startDate = today.minusDays(83); // 12 tuần = 84 ngày
    LocalDate firstMonday = startDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));

    List<ChartColumn> fullList = new ArrayList<>();
    for (LocalDate date = firstMonday; date.isBefore(today.plusDays(1)); date = date.plusWeeks(1)) {
        String columnName = date.format(formatter);
        boolean found = false;
        for (ChartColumn c : list) {
            if (c.getColumnName().equals(columnName)) {
                fullList.add(c);
                found = true;
                break;
            }
        }
        if (!found) {
            fullList.add(new ChartColumn(columnName, 0));
        }
    }

    // Sắp xếp lại theo ngày tăng dần
    fullList.sort((a, b) -> LocalDate.parse(a.getColumnName(), formatter)
            .compareTo(LocalDate.parse(b.getColumnName(), formatter)));

    return fullList;
}
}
