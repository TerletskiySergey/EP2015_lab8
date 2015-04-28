package EPAM2015_lab8;

import EPAM2015_lab8.checkers.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Contains all available for issue (by means of SkiPassManager instance-method: public SkiPass issueSkiPass(SkiPassType type))
 * ski-pass types assignments. "WD" and "WE" assignments-parts mean correspondingly "weekday" and "weekend".
 * Each enum instance contains:
 * - sequence of SkiPassChecker instances, which are used for ski-pass
 * check-process, which is implemented by SkiPassManager instance-method: public boolean checkSkiPass(SkiPass toCheck).
 * - integer parameter "credit", which is necessary for SkiPass instance constructing
 * (SkiPass class-constructor: public SkiPass(SkiPassType type, int credit)).
 */

public enum SkiPassType {

    WD_FIRST_HALF(new SkiPassChecker[]{new AllowedTimeChecker(), new WeekdayChecker(), new FirstDayHalfChecker(), new DaysLimitChecker()}, 1),
    WD_SECOND_HALF(new SkiPassChecker[]{new AllowedTimeChecker(), new WeekdayChecker(), new SecondDayHalfChecker(), new DaysLimitChecker()}, 1),
    WD_1_DAY(new SkiPassChecker[]{new AllowedTimeChecker(), new WeekdayChecker(), new DaysLimitChecker()}, 1),
    WD_2_DAYS(new SkiPassChecker[]{new AllowedTimeChecker(), new WeekdayChecker(), new DaysLimitChecker()}, 2),
    WD_3_DAYS(new SkiPassChecker[]{new AllowedTimeChecker(), new WeekdayChecker(), new DaysLimitChecker()}, 3),
    WD_4_DAYS(new SkiPassChecker[]{new AllowedTimeChecker(), new WeekdayChecker(), new DaysLimitChecker()}, 4),
    WD_5_DAYS(new SkiPassChecker[]{new AllowedTimeChecker(), new WeekdayChecker(), new DaysLimitChecker()}, 5),
    WD_10_RIDES(new SkiPassChecker[]{new AllowedTimeChecker(), new WeekdayChecker(), new RidesLimitChecker()}, 10),
    WD_20_RIDES(new SkiPassChecker[]{new AllowedTimeChecker(), new WeekdayChecker(), new RidesLimitChecker()}, 20),
    WD_50_RIDES(new SkiPassChecker[]{new AllowedTimeChecker(), new WeekdayChecker(), new RidesLimitChecker()}, 50),
    WD_100_RIDES(new SkiPassChecker[]{new AllowedTimeChecker(), new WeekdayChecker(), new RidesLimitChecker()}, 100),
    WE_FIRST_HALF(new SkiPassChecker[]{new AllowedTimeChecker(), new WeekendChecker(), new FirstDayHalfChecker(), new DaysLimitChecker()}, 1),
    WE_SECOND_HALF(new SkiPassChecker[]{new AllowedTimeChecker(), new WeekendChecker(), new SecondDayHalfChecker(), new DaysLimitChecker()}, 1),
    WE_1_DAY(new SkiPassChecker[]{new AllowedTimeChecker(), new WeekendChecker(), new DaysLimitChecker()}, 1),
    WE_2_DAY(new SkiPassChecker[]{new AllowedTimeChecker(), new WeekendChecker(), new DaysLimitChecker()}, 2),
    WE_10_RIDES(new SkiPassChecker[]{new AllowedTimeChecker(), new WeekendChecker(), new RidesLimitChecker()}, 10),
    WE_20_RIDES(new SkiPassChecker[]{new AllowedTimeChecker(), new WeekendChecker(), new RidesLimitChecker()}, 20),
    WE_50_RIDES(new SkiPassChecker[]{new AllowedTimeChecker(), new WeekendChecker(), new RidesLimitChecker()}, 50),
    WE_100_RIDES(new SkiPassChecker[]{new AllowedTimeChecker(), new WeekendChecker(), new RidesLimitChecker()}, 100),
    SEASON(new SkiPassChecker[]{new AllowedTimeChecker()}, 0);

    private List<SkiPassChecker> checkers;
    private int credit;

    private SkiPassType(SkiPassChecker[] checkers, int credit) {
        this.checkers = new ArrayList<>();
        Collections.addAll(this.checkers, checkers);
        this.credit = credit;
    }

    /**
     * Returns a list of SkiPassChecker instances, that is requested for ski-pass check process
     * by SkiPassGate instance-method: public boolean checkSkiPass(SkiPass toCheck).
     */
    public List<SkiPassChecker> getCheckers() {
        return new ArrayList<>(checkers);
    }

    /**
     * Constructs and returns SkiPass instance. As parameters for constructing SkiPass instance
     * are passed "this" SkiPassType instance and integer value, that "this" SkiPassType instance
     * encapsulates (SkiPass class-constructor: public SkiPass(SkiPassType type, int credit)).
     */
    public SkiPass getSkiPassInstance() {
        return new SkiPass(this, credit);
    }
}
