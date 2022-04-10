package utils;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtil {

    public String getCurrentDate(String dateFormat){
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        String date = sdf.format(new Date());
        return date;
    }
    
}
