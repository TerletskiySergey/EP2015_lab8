package EPAM2015_lab8.reports;

import EPAM2015_lab8.SkiPassType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Abstract representation of ski-pass check-report as a table, which rows are instances of ReportRecord.
 * Encapsulates list of ReportRecord instances. Allows inheritors in different ways to implement
 * report generation by providing abstract method: public abstract List<ReportRecord> getReport().
 */

public abstract class AbstractReport {

    /**
     * Abstract representation of table row. Depending on the AbstractReport
     * inheritors, in which instances of ReportRecord are encapsulated, the meaning of
     * ReportRecord instance-fields can vary.
     */
    public static class ReportRecord implements Comparable<ReportRecord> {
        // Represents ski-pass id-number
        private Long skiPassId;
        // Represents ski-pass type
        private SkiPassType skiPassType;
        // Represents ski-pass turnstile (gate) id-number
        private Long gateId;
        // Represents ski-pass check date
        private Date checkDate;
        // Represents result of sli-pass check-procedure
        private Boolean allowed;
        // Represents cause of reject in case if "allowed"-property = false
        private String rejectCause;
        // List of additional parameters, which ca be used by inheritors of AbstractReport
        private List addFields;

        public ReportRecord(Long skiPassId, SkiPassType skiPassType, Long gateId,
                            Date checkDate, Boolean allowed, String rejectCause, Object... addFields) {
            this.skiPassId = skiPassId;
            this.skiPassType = skiPassType;
            this.gateId = gateId;
            this.checkDate = checkDate;
            this.allowed = allowed;
            this.rejectCause = rejectCause;
            if (addFields != null && addFields.length != 0) {
                this.addFields = new ArrayList<>();
                Collections.addAll(this.addFields, addFields);
            }
        }

        public Long getSkiPassId() {
            return skiPassId;
        }

        public SkiPassType getSkiPassType() {
            return skiPassType;
        }

        public Long getGateId() {
            return gateId;
        }

        public Date getCheckDate() {
            return new Date(checkDate.getTime());
        }

        public Boolean isAllowed() {
            return allowed;
        }

        public String getRejectCause() {
            return rejectCause;
        }

        public List getAdditionalFields() {
            return addFields;
        }

        @Override
        public int compareTo(ReportRecord o) {
            return (int) Math.signum(this.skiPassId - o.skiPassId);
        }
    }

    protected List<ReportRecord> records;

    public AbstractReport() {
        this.records = new ArrayList<>();
    }

    public AbstractReport(List<ReportRecord> records) {
        this.records = records;
    }

    public void addRecord(AbstractReport.ReportRecord record) {
        this.records.add(record);
    }

    public abstract List<ReportRecord> getReport();
}