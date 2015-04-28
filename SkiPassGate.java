package EPAM2015_lab8;

import EPAM2015_lab8.checkers.SkiPassChecker;

import java.util.Date;
import java.util.List;

import static EPAM2015_lab8.reports.AbstractReport.ReportRecord;

/**
 * Abstract representation of a ski-pass turnstile (gate). Instance of class encapsulates
 * SkiPassManager instance, which is transferred by means of class constructor. SkiPassManager
 * instance is used for requesting a list of blocked ski-passes
 * (SkiPassManager instance-method: public boolean isBlocked(long id))
 * and for sending report record about just checked SkiPass
 * (SkiPassManager instance-method: public void addReportRecord(Report.ReportRecord newRecord)).
 */
public class SkiPassGate {

    private static long IDs;

    private long id;
    private SkiPassManager manager;

    public Long getId() {
        return id;
    }

    public SkiPassGate(SkiPassManager manager) {
        this.manager = manager;
        this.id = ++IDs;
    }

    /**
     * Abstract representation of ski-pass card check-process at ski-pass turnstile (gate).
     * Method implements only one check itself - block check, which means to request the block
     * check at SkiPassManager instance (SkiPassManager instance-method: public boolean isBlocked(long id)).
     * Other checks are implemented by means of SkiPassChecker instances. The correspondence between
     * ski-pass type and the sequence of SkiPassChecker instances is defined in enum SkiPassType.
     * Method requests the sequence of checkers from enum SkiPassType depending on "type"-property of
     * SkiPass instance that must be checked. For every SkiPassChecker instance from obtained sequence
     * SkiPassChecker instance-method: public boolean check(SkiPass toCheck) is called.
     * As a last step of check-process report record about just checked SkiPass is generated
     * and is added by means of SkiPassManager instance-method
     * (public void addReportRecord(Report.ReportRecord newRecord)) to the
     * main report, which is stored in SkiPassManager instance.
     */
    public boolean checkSkiPass(SkiPass toCheck) {
        boolean toReturn = true;
        String rejectCause = "";
        if (manager.isBlocked(toCheck.getId())) {
            toReturn = false;
            rejectCause = "BLOCKED";
        } else {
            List<SkiPassChecker> skiPassCheckers = toCheck.getType().getCheckers();
            for (SkiPassChecker curChecker : skiPassCheckers) {
                if (!curChecker.check(toCheck)) {
                    toReturn = false;
                    rejectCause = curChecker.getRejectCause();
                    break;
                }
            }
        }
        ReportRecord newRecord = new ReportRecord(toCheck.getId(), toCheck.getType(),
                this.id, new Date(), toReturn, rejectCause);
        manager.addReportRecord(newRecord);
        return toReturn;
    }
}
