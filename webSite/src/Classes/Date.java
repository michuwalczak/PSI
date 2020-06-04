package Classes;

import java.sql.Timestamp;
import java.util.Calendar;

public final class Date {
    public static Timestamp get(){
        return new java.sql.Timestamp(new java.util.Date().getTime());
    }

    public static boolean isTimeUp(Timestamp time, int minutes){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time.getTime());
        calendar.add(Calendar.MINUTE, minutes);
        Timestamp timeLimit = new Timestamp(calendar.getTimeInMillis());

        return Date.get().after(timeLimit);
    }
}
