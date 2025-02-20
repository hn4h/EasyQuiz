/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import model.OTP;
import model.TokenForgetPassword;

/**
 *
 * @author 11
 */
public class OTPDAO extends DBContext{
   public String getFormatDate(LocalDateTime myDateObj) {
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);
        return formattedDate;
    } 
   public boolean insertOTP(OTP otp) {
        String sql = "INSERT INTO [dbo].[OTP] "
                + "([OTP_Code], [ExpiredTime], [isUsed], [userName]) "
                + "VALUES(?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, otp.getOtp());
            ps.setTimestamp(2, Timestamp.valueOf(otp.getExpiredTime())); // Direct conversion
            ps.setBoolean(3, otp.isUsed());
            ps.setString(4, otp.getUserName());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
}
