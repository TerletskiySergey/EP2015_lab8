package EPAM2015_lab8;

import EPAM2015_lab8.checkers.SkiPassChecker;

import java.util.Date;

/**
 * Class is abstract representation of ski-pass card. The variety of possible ski-pass types
 * can be obtained by setting private properties "type" and "credit" by means of constructor
 * to varied values.
 */
public class SkiPass {

    private static long IDs;
    private long id;
    private Date expireDate;
    private SkiPassType type;
    // Depending on "type"-property "credit"-property can represent different values.
    // For example for type = SkiPassType.WD_1_DAY "credit" represents number of available days,
    // and for type = SkiPassType.WD_10_RIDES "credit" means number of remaining rides.
    private int credit;

    /**
     * Sets values of private properties to specific values. For some "type"-property "credit"
     * property is redundant and therefore can be set to any value (preferable 0).
     */
    public SkiPass(SkiPassType type, int credit) {
        this.id = ++SkiPass.IDs;
        // Property "expireDate" is set initially for all possible types of SkiPasses to the value of
        // season end, which is stored as a static field in SkiPassChecker class. Depending on
        // "type"-property "expireDate"-property can be later changed by SkiPassChecker instance,
        // when SkiPassGate instance-method: public boolean checkSkiPass(SkiPass toCheck) is called.
        this.expireDate = SkiPassChecker.getSeasonEnd();
        this.type = type;
        this.credit = credit;
    }

    public Long getId() {
        return id;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public SkiPassType getType() {
        return type;
    }

    public int getCredit() {
        return credit;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public void setType(SkiPassType type) {
        this.type = type;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }
}
