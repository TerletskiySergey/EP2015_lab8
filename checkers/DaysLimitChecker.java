package EPAM2015_lab8.checkers;

import EPAM2015_lab8.SkiPass;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Implements ski-pass check logic.
 * Logic includes following checks:
 *  - current check is first check for current SkiPass. If current check is first one,
 *  expire date of SkiPass is set to the new value. New value is a time moment of day end,
 *  on which current SkiPass expires. Day, on which current SkiPass expires, calculates on the basis
 *  of "credit"-property of SkiPass. For DayLimitChecker "credit"-property of SkiPass
 *  represents quantity of remaining days. First day starts from first allowed-check.
 *  - current moment is before expire date of checking SkiPass;
 */

public class DaysLimitChecker extends SkiPassChecker {

    private static final String rejectCause = "NO_AVAILABLE_DAYS_LEFT";

    @Override
    public boolean check(SkiPass toCheck) {
        checkFirstPass(toCheck);
        Date curMoment = new Date();
        Date expireDate = toCheck.getExpireDate();
        return curMoment.before(expireDate);
    }

    @Override
    public String getRejectCause() {
        return rejectCause;
    }

    private void checkFirstPass(SkiPass toCheck) {
        Integer credit = toCheck.getCredit();
        if (credit != 0) {
            setNewExpireDate(toCheck);
        }
    }

    private void setNewExpireDate(SkiPass toCheck) {
        Integer credit = toCheck.getCredit();
        GregorianCalendar newExpireDate = new GregorianCalendar();
        newExpireDate.setTime(getDayEnd());
        newExpireDate.add(Calendar.DAY_OF_MONTH, credit - 1);
        if (newExpireDate.getTime().after(getSeasonEnd())) {
            newExpireDate.setTime(getSeasonEnd());
        }
        toCheck.setExpireDate(newExpireDate.getTime());
        toCheck.setCredit(0);
    }
}
