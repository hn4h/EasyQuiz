/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.Profile;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author admin
 */
public class DateUtil {
    public static String formatQuizSetDate(Date createdDate) {
        Calendar calendar = Calendar.getInstance();
        Calendar createdCalendar = Calendar.getInstance();
        createdCalendar.setTime(createdDate);

        // Lấy ngày hôm nay
        int todayYear = calendar.get(Calendar.YEAR);
        int todayMonth = calendar.get(Calendar.MONTH);
        int todayDay = calendar.get(Calendar.DAY_OF_MONTH);

        // Lấy ngày của QuizSet
        int createdYear = createdCalendar.get(Calendar.YEAR);
        int createdMonth = createdCalendar.get(Calendar.MONTH);
        int createdDay = createdCalendar.get(Calendar.DAY_OF_MONTH);

        if (todayYear == createdYear && todayMonth == createdMonth && todayDay == createdDay) {
            return "Today";
        } else if (todayYear == createdYear && todayMonth == createdMonth && todayDay - 1 == createdDay) {
            return "Yesterday";
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("'In' MMMM yyyy"); // Ví dụ: "In October 2024"
            return dateFormat.format(createdDate);
        }
    }
}
