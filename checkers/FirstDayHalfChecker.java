package EPAM2015_lab8.checkers;

import EPAM2015_lab8.SkiPass;

import java.util.Date;

/**
 * Implements ski-pass check logic.
 * Logic includes following checks:
 *  - current moment is within first day half, which limits are determined by SkiPassChecker static fields;
 */

public class FirstDayHalfChecker extends SkiPassChecker {

    private static final String rejectCause = "NOT_ON_FIRST_DAY_HALF";

    @Override
    public boolean check(SkiPass toCheck) {
        Date curMoment = new Date();
        Date midday = getMidday();
        Date dayBegin = getDayBegin();
        return (curMoment.after(dayBegin) || curMoment.equals(dayBegin))
                && curMoment.before(midday);
    }

    @Override
    public String getRejectCause() {
        return rejectCause;
    }
}
