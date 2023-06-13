package core.managers;

import shared.enteties.User;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LogManager {

    private final String datePattern = "[yyyy-MM-dd HH:mm:ss]";

    public void commandLog(User user, String command) {
        System.out.println(getDate() + " " + user.getName() + " : " + command);
    }

    private String getDate() {
        SimpleDateFormat formatter= new SimpleDateFormat(datePattern);
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }

}

