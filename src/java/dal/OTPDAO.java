package dal;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OTPDAO extends DBContext{
    public String getFormatDate(LocalDateTime myDateObj) {
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);
        return formattedDate;
    }

}
