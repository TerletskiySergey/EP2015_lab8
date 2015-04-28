package EPAM2015_lab8.tables;

import EPAM2015_lab8.reports.AbstractReport;

import java.util.ArrayList;
import java.util.List;

import static EPAM2015_lab8.reports.AbstractReport.ReportRecord;

/**
 * Generates table representation for GroupedByTypeReport instance.
 * Prepares lists of input parameters required by super class Table:
 * - widths: List of integer values, each value represents width of corresponding table field (units: symbols quantity);
 * - header: List of string values, each value represents name of corresponding table field;
 * - data: List of lists of string values (2D String list), each sublist represents row of table.
 * Fields of table representation of GroupedByTypeReport instance are:
 *  - #: sequence number of current row;
 *  - SP_TYPE: ski-pass type;
 *  - ALLOWED: total quantity of allowed-checks for current ski-pass type;
 *  - REJECTED: total quantity of rejected-checks for current ski-pass type;
 */
public class GroupedByTypeReportTable extends Table {

    public GroupedByTypeReportTable(AbstractReport report) {
        super(null, null, null);
        List<Integer> widths = getWidths();
        List<String> header = getHeader();
        List<List<String>> data = getData(report.getReport());
        super.setWidths(widths);
        super.setHeader(header);
        super.setData(data);
        super.setTag("GROUPED REPORT");
        super.fitWidthsToContent(new int[]{0, 1});
    }

    private List<Integer> getWidths() {
        List<Integer> toReturn = new ArrayList<>();
        toReturn.add(1);
        toReturn.add(7);
        toReturn.add(7);
        toReturn.add(8);
        return toReturn;
    }

    private List<String> getHeader() {
        List<String> toReturn = new ArrayList<>();
        toReturn.add("#");
        toReturn.add("SP_TYPE");
        toReturn.add("ALLOWED");
        toReturn.add("REJECTED");
        return toReturn;
    }

    private List<List<String>> getData(List<ReportRecord> records) {
        List<List<String>> toReturn = new ArrayList<>();
        List<String> row;
        int counter = 0;
        for (ReportRecord curRecord : records) {
            row = new ArrayList<>();
            row.add(String.valueOf(++counter));
            row.add(curRecord.getSkiPassType().toString());
            row.add(String.valueOf(curRecord.getAdditionalFields().get(0)));
            row.add(String.valueOf(curRecord.getAdditionalFields().get(1)));
            toReturn.add(row);
        }
        return toReturn;
    }
}
