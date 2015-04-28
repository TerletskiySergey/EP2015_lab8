package EPAM2015_lab8.checkers;

import EPAM2015_lab8.SkiPass;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Implements ski-pass check logic.
 * Logic includes following checks:
 * - current day is weekend;
 */

public class WeekendChecker extends SkiPassChecker {

    private static final String rejectCause = "NOT_ON_WEEKEND";

    @Override
    public boolean check(SkiPass toCheck) {
        GregorianCalendar calendar = new GregorianCalendar();
        int curDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return curDayOfWeek == Calendar.SATURDAY || curDayOfWeek == Calendar.SUNDAY;
    }

    @Override
    public String getRejectCause() {
        return rejectCause;
    }
}
