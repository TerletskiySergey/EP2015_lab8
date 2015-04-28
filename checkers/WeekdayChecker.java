package EPAM2015_lab8.checkers;

import EPAM2015_lab8.SkiPass;

/**
 * Implements ski-pass check logic.
 * Logic includes following checks:
 * - current day is weekday;
 */

public class WeekdayChecker extends SkiPassChecker {

    private static final String rejectCause = "NOT_ON_WEEKDAY";

    @Override
    public boolean check(SkiPass toCheck) {
        return !new WeekendChecker().check(toCheck);
    }

    @Override
    public String getRejectCause() {
        return rejectCause;
    }
}
