package EPAM2015_lab8;

import EPAM2015_lab8.reports.FullReport;
import EPAM2015_lab8.reports.AbstractReport;
import EPAM2015_lab8.reports.GroupedBySkiPassTypeReport;
import EPAM2015_lab8.tables.FullReportTable;
import EPAM2015_lab8.tables.GroupedByTypeReportTable;
import EPAM2015_lab8.tables.Table;

import java.util.HashSet;
import java.util.Set;

/**
 * Abstract representation of ski-passes accounting system.
 */
public class SkiPassManager {
    // Set of ski-pass IDs, that assigned as blocked.
    private Set<Long> blocked;
    // Store of ReportRecord instances, that constantly updates by means of SkiPassGate instances,
    // for which "this" instance was transferred as a constructor parameter
    // (SkiPassGate class constructor: public SkiPassGate(SkiPassManager manager)).
    private AbstractReport report;

    public SkiPassManager() {
        this.blocked = new HashSet<>();
        this.report = new FullReport();
    }

    /**
     * Returns SkiPass instance, which was constructed by SkiPassType instance-method
     * (public SkiPass getSkiPassInstance()). All required data for constructing
     * SkiPass instance are encapsulated in correspondent SkiPassType instance.
     */
    public SkiPass issueSkiPass(SkiPassType type) {
        return type.getSkiPassInstance();
    }

    public void blockSkiPass(long id) {
        blocked.add(id);
    }

    public boolean isBlocked(long skiPassId) {
        return blocked.contains(skiPassId);
    }

    public void addReportRecord(AbstractReport.ReportRecord record) {
        report.addRecord(record);
    }

    /**
     * Sends to standard output stream a table representation of full ski-pass accounting report.
     * All required data for table representation are taken from FullReport instance,
     * which "this" instance encapsulates.
     * Table representation of FullReport instance is defined in FullReportTable class.
     */
    public void showFullReport() {
        Table tb = new FullReportTable(report);
        System.out.println(tb);
    }

    /**
     * Sends to standard output stream a table representation of grouped by ski-pass type
     * accounting report. All required data for table representation are taken from
     * GroupedBySkiPassTypeReport, which is constructed on the basis of FullReport instance,
     * which "this" instance encapsulates.
     * Table representation of GroupedBySkiPassTypeReport instance is defined in
     * GroupedByTypeReportTable class.
     */
    public void showGroupedBySkiPassTypeReport() {
        GroupedBySkiPassTypeReport groupedReport = new GroupedBySkiPassTypeReport(report);
        Table tb = new GroupedByTypeReportTable(groupedReport);
        System.out.println(tb);
    }
}
