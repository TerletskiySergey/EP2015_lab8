package EPAM2015_lab8.checkers;

import EPAM2015_lab8.SkiPass;

import java.util.Date;

/**
 * Implements ski-pass check logic.
 * Logic includes following checks:
 *  - current moment is within day time limits, which are determined by SkiPassChecker static fields;
 *  - current moment is within season time limits, which are determined by SkiPassChecker static fields;
 *  - expire date of SkiPass, that has to be checked, lies within season time limits, which are determined by SkiPassChecker static fields.
 */
public class AllowedTimeChecker extends SkiPassChecker {

    private static final String rejectCause = "NOT_IN_ALLOWED_TIME";

    private Date curMoment;

    private boolean withinDayTime() {
        Date dayBegin = getDayBegin();
        Date dayEnd = getDayEnd();
        return curMoment.before(dayEnd)
                && (curMoment.after(dayBegin) || curMoment.equals(dayBegin));
    }

    private boolean withinSeasonTime(SkiPass toCheck) {
        Date seasonBegin = getSeasonBegin();
        Date seasonEnd = getSeasonEnd();
        Date expireDate = toCheck.getExpireDate();
        return curMoment.before(seasonEnd)
                && (curMoment.after(seasonBegin) || curMoment.equals(seasonBegin))
                && expireDate.after(seasonBegin)
                && (expireDate.before(seasonEnd) || expireDate.equals(seasonEnd));
    }

    @Override
    public boolean check(SkiPass toCheck) {
        curMoment = new Date();
        return withinDayTime() && withinSeasonTime(toCheck);
    }

    @Override
    public String getRejectCause() {
        return rejectCause;
    }
}
