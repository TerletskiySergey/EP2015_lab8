package EPAM2015_lab8.reports;

import EPAM2015_lab8.SkiPassType;

import java.util.*;

/**
 * Abstract representation of ski-pass check-report as a table, which rows are instances of ReportRecord.
 * Encapsulates list of ReportRecord instances. In this variant of AbstractReport inheritors
 * ReportRecord instance-properties have the following meaning:
 * - skiPassId - not used;
 * - skiPassType - ski-pass type;
 * - gateId - not used;
 * - checkDate - not used;
 * - allowed - not used;
 * - rejectCause - not used;
 * - addFields.get(0) - total quantity of allowed-checks for current ski-pass type;
 * - addFields.get(1) - total quantity of rejected-checks for current ski-pass type.
 */

public class GroupedBySkiPassTypeReport extends AbstractReport {

    public GroupedBySkiPassTypeReport() {
    }

    public GroupedBySkiPassTypeReport(AbstractReport report) {
        super(report.records);
    }

    /**
     * Generates ski-pass check-report as a list of ReportRecord instances.
     * Each row of report (of ReportRecord instance) uses the following ReportRecord property:
     *  - type (SkiPassType) - ski-pass type;
     *  - addFields.get(0) (Integer) - total quantity of allowed-checks for current ski-pass type;
     *  - addFields.get(1) (Integer) - total quantity of rejected-checks for current ski-pass type.
     */
    @Override
    public List<ReportRecord> getReport() {
        Map<SkiPassType, ReportRecord> map = new HashMap<>();
        for (ReportRecord curRecord : this.records) {
            SkiPassType curSkiPassType = curRecord.getSkiPassType();
            ReportRecord recordToRefresh;
            if (map.containsKey(curSkiPassType)) {
                recordToRefresh = map.get(curSkiPassType);
            } else {
                recordToRefresh = new ReportRecord(null, curSkiPassType, null, null, null, null, 0, 0);
                map.put(curSkiPassType, recordToRefresh);
            }
            if (curRecord.isAllowed()) {
                Integer curAllowedQuant = (Integer) recordToRefresh.getAdditionalFields().get(0);
                recordToRefresh.getAdditionalFields().set(0, ++curAllowedQuant);
            } else {
                Integer curRejectedQuant = (Integer) recordToRefresh.getAdditionalFields().get(1);
                recordToRefresh.getAdditionalFields().set(1, ++curRejectedQuant);
            }
        }
        List<ReportRecord> toReturn = new ArrayList<>(map.values());
        Collections.sort(toReturn, (o1, o2) -> o1.getSkiPassType().compareTo(o2.getSkiPassType()));
        return toReturn;
    }
}
