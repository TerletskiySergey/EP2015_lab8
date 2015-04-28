package EPAM2015_lab8.tables;

import EPAM2015_lab8.reports.AbstractReport;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Generates table representation for FullReport instance.
 * Prepares lists of input parameters required by super class Table:
 * - widths: List of integer values, each value represents width of corresponding table field (units: symbols quantity);
 * - header: List of string values, each value represents name of corresponding table field;
 * - data: List of lists of string values (2D String list), each sublist represents row of table.
 * Fields of table representation of FullReport instance are:
 *  - #: sequence number of current row;
 *  - SP_ID#: ski-pass id-number;
 *  - SP_TYPE: ski-pass type;
 *  - CHECK_DATE: date of ski-pass check;
 *  - GATE_ID#: ski-pass turnstile id-number, at which current ski-pass was checked;
 *  - CHECK_RESULT: result of ski-pass check ("ALLOWED" or "REJECTED");
 *  - REJECT_CAUSE: cause of reject (if CHECK_RESULT = ALLOWED -> REJECT_CAUSE = "-- ")
 */
public class FullReportTable extends Table {

    public FullReportTable(AbstractReport report) {
        super(null, null, null);
        List<Integer> widths = getWidths();
        List<String> header = getHeader();
        List<List<String>> data = getData(report.getReport());
        super.setWidths(widths);
        super.setHeader(header);
        super.setData(data);
        super.setTag("FULL REPORT");
        super.fitWidthsToContent(new int[]{0, 1, 2, 3, 5, 6});
    }

    private List<Integer> getWidths() {
        List<Integer> toReturn = new ArrayList<>();
        toReturn.add(1);
        toReturn.add(6);
        toReturn.add(7);
        toReturn.add(17);
        toReturn.add(8);
        toReturn.add(12);
        toReturn.add(12);
        return toReturn;
    }

    private List<String> getHeader() {
        List<String> toReturn = new ArrayList<>();
        toReturn.add("#");
        toReturn.add("SP_ID#");
        toReturn.add("SP_TYPE");
        toReturn.add("CHECK_DATE");
        toReturn.add("GATE_ID#");
        toReturn.add("CHECK_RESULT");
        toReturn.add("REJECT_CAUSE");
        return toReturn;
    }

    private List<List<String>> getData(List<? extends AbstractReport.ReportRecord> report) {
        List<List<String>> toReturn = new ArrayList<>();
        List<String> row;
        int counter = 0;
        for (AbstractReport.ReportRecord curRecord : report) {
            row = new ArrayList<>();
            row.add(String.valueOf(++counter));
            row.add(String.valueOf(curRecord.getSkiPassId()));
            row.add(curRecord.getSkiPassType().toString());
            SimpleDateFormat df = new SimpleDateFormat("dd.MM.yy HH:mm:ss");
            row.add(df.format(curRecord.getCheckDate()));
            row.add(String.valueOf(curRecord.getGateId()));
            row.add(curRecord.isAllowed() ? "ALLOWED" : "REJECTED");
            row.add(curRecord.getRejectCause().equals("") ? "-- " : curRecord.getRejectCause());
            toReturn.add(row);
        }
        return toReturn;
    }
}
