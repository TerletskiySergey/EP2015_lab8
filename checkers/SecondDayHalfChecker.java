package EPAM2015_lab8.checkers;

import EPAM2015_lab8.SkiPass;

import java.util.Date;

/**
 * Implements ski-pass check logic.
 * Logic includes following checks:
 *  - current moment is within second day half, which limits are determined by SkiPassChecker static fields;
 */
public class SecondDayHalfChecker extends SkiPassChecker {

    private static final String rejectCause = "NOT_ON_SECOND_DAY_HALF";

    @Override
    public boolean check(SkiPass toCheck) {
        Date curMoment = new Date();
        Date midday = getMidday();
        Date dayEnd = getDayEnd();
        return (curMoment.after(midday) || curMoment.equals(midday))
                && curMoment.before(dayEnd);
    }

    @Override
    public String getRejectCause() {
        return rejectCause;
    }
}