package EPAM2015_lab8.checkers;

import EPAM2015_lab8.SkiPass;

/**
 * Implements ski-pass check logic.
 * Logic includes following checks:
 *  - current "credit"-property of checked SkiPass is greater than 0.
 *  For RidesLimitChecker "credit"-property of SkiPass represents rides remaining quantity.
 */
public class RidesLimitChecker extends SkiPassChecker {

    private static final String rejectCause = "NO_AVAILABLE_RIDES_LEFT";

    @Override
    public boolean check(SkiPass toCheck) {
        Integer credit = toCheck.getCredit();
        if (credit > 0) {
            toCheck.setCredit(--credit);
            return true;
        }
        return false;
    }

    @Override
    public String getRejectCause() {
        return rejectCause;
    }
}
