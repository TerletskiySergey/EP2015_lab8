package EPAM2015_lab8.reports;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Abstract representation of ski-pass check-report as a table, which rows are instances of ReportRecord.
 * Encapsulates list of ReportRecord instances. In this variant of AbstractReport inheritors
 * ReportRecord instance-fields have the same meaning as they have in AbstractReport.
 */
public class FullReport extends AbstractReport {

    public FullReport() {
    }

    public FullReport(AbstractReport report) {
        super(report.records);
    }

    /**
     * Generates ski-pass check-report as a list of ReportRecord instances.
     * Each row of report (of ReportRecord instance) has following values:
     *  - ski-pass id-number (long);
     *  - ski-pass type (SkiPassType);
     *  - date of ski-pass check (Date);
     *  - ski-pass turnstile id-number, at which current ski-pass was checked (long);
     *  - result of ski-pass check (boolean);
     *  - cause of reject (String).
     */
    @Override
    public List<ReportRecord> getReport() {
        List<ReportRecord> toReturn = new ArrayList<>();
        toReturn.addAll(this.records);
        // Sort by ski-pass check-date
        toReturn.sort((o1, o2) -> o1.getCheckDate().compareTo(o2.getCheckDate()));
        // Sort by ski-pass id-number
        Collections.sort(toReturn);
        return toReturn;
    }
}
