package EPAM2015_lab8.tables;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Generates table representation according to the input-parameters:
 * - widths: List of integer values, each value represents width of corresponding table field (units: symbols quantity);
 * - header: List of string values, each value represents name of corresponding table field;
 * - data: List of lists of string values (2D String list), each sublist represents row of table.
 */
public class Table {

    private String tag;
    private List<Integer> widths;
    private List<String> header;
    private List<List<String>> data;
    private Set<Integer> fitSet;
    private StringBuffer strToPrint;

    public Table(List<Integer> widths, List<String> header,
                 ArrayList<List<String>> data) {
        this.widths = widths;
        this.header = header;
        this.data = data;
    }

    public void setData(List<List<String>> data) {
        this.data = data;
    }

    public void setHeader(List<String> header) {
        this.header = header;
    }

    public void setWidths(List<Integer> widths) {
        this.widths = widths;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void fitWidthsToContent(int[] fitArray) {
        if (fitSet == null) {
            fitSet = new HashSet<>();
        }
        for (Integer elem : fitArray) {
            if (elem < widths.size()) {
                fitSet.add(elem);
            }
        }
    }

    private void fitWidthsToContent() {
        if (fitSet == null) {
            return;
        }
        for (int i = 0; i < widths.size(); i++) {
            if (fitSet.contains(i)) {
                int columnWidth = widths.get(i);
                for (List<String> row : data) {
                    if (columnWidth < row.get(i).length()) {
                        columnWidth = row.get(i).length();
                    }
                }
                widths.set(i, columnWidth);
            }
        }
    }

    @Override

    public String toString() {
        if (data.isEmpty()) setEmptyData();
        fitWidthsToContent();
        strToPrint = new StringBuffer();
        printLine('-');
        if (tag != null && tag.length() != 0) printTag();
        printLine(header);
        printLine('-');
        for (List<String> al : data) {
            printLineSmart(al);
        }
        printLine('-');
        strToPrint.setLength(strToPrint.length() - 1);
        return strToPrint.toString();
    }

    private void setEmptyData() {
        ArrayList<String> row = new ArrayList<>();
        for (int i = 0; i < header.size(); i++) {
            row.add("");
        }
        data.add(row);
    }

    private static String getSymbolRow(char pat, int length) {
        StringBuilder toReturn = new StringBuilder();
        for (int i = 0; i < length; i++) toReturn.append(pat);
        return toReturn.toString();
    }

    private int getWordBegin(int currIndx, int idxOfPattMember, String strToSearchIn) {
        if (currIndx == 0) return currIndx;
        if ((currIndx < strToSearchIn.length() - 1) && (strToSearchIn.charAt(currIndx) == ' ')) {
            return currIndx + 1;
        }
        return currIndx;
    }

    private int getWordEnd(int currIndx, int idxOfPattMember, String strToSearchIn) {
        if (strToSearchIn.charAt(currIndx) == ' ') return currIndx - 1;
        if ((strToSearchIn.charAt(currIndx) != ' ') && (strToSearchIn.charAt(currIndx + 1) == ' ')) return currIndx;
        int counter = 0;
        while (counter < widths.get(idxOfPattMember) - 2) {
            if (strToSearchIn.charAt(currIndx - counter - 1) == ' ') {
                return currIndx - counter - 2;
            }
            counter++;
        }
        return currIndx;
    }

    private void printLine(char symb) {
        strToPrint.append("+");
        for (Integer num : widths) {
            for (int i = 0; i < num; i++) {
                strToPrint.append(symb);
            }
            strToPrint.append("+");
        }
        strToPrint.append("\n");
    }

    private void printLine(List<String> array) {

        StringBuilder buf = new StringBuilder();

        int lineCounter = 1;

        boolean isMoreLines = true;

        while (isMoreLines) {

            strToPrint.append("|");

            isMoreLines = false;

            for (int i = 0; i < array.size(); i++) {

                buf.setLength(0);

                int index1 = widths.get(i) != 0 ? (lineCounter - 1) * widths.get(i) : 0;

                int index2 = lineCounter * widths.get(i) - 1;

                int endOfWordIndex = array.get(i).length() - 1;

                if ((index1 > endOfWordIndex) || ((widths.get(i) == 0))) {

                    buf.setLength(widths.get(i));

                } else if ((index1 <= endOfWordIndex) && (index2 > endOfWordIndex)) {

                    int dif = index2 - endOfWordIndex;

                    buf.setLength(dif);

                    buf.append(array.get(i).substring(index1));

                } else if (index2 <= endOfWordIndex) {

                    buf.append(array.get(i).substring(index1, index2 + 1));

//                    isMoreLines = index2 != endOfWordIndex ? true : isMoreLines;
                    isMoreLines = index2 != endOfWordIndex || isMoreLines;
                }
                strToPrint.append(buf).append("|");
            }

            strToPrint.append("\n");

            lineCounter++;
        }
    }

    private void printLineSmart(List<String> array) {

        StringBuilder buf = new StringBuilder();

        int[] indexes = new int[array.size()];

        for (int i = 0; i < indexes.length; i++)
            indexes[i] = -1;

        int index1, index2;

        boolean isMoreLines = true;

        while (isMoreLines) {

            isMoreLines = false;

            strToPrint.append("|");

            for (int i = 0; i < array.size(); i++, strToPrint.append(buf)
                    .append("|")) {

                buf.setLength(0);

                index1 = getWordBegin(indexes[i] + 1, i, array.get(i));

                if ((index1 > array.get(i).length() - 1)
                        || ((widths.get(i) == 0))) {

                    buf.setLength(widths.get(i));

                    continue;

                }

                index2 = index1 + widths.get(i) - 1;

                if (index2 >= array.get(i).length() - 1) {

                    int dif = index2 - (array.get(i).length() - 1);

                    buf.setLength(dif);

                    buf.append(array.get(i).substring(index1,
                            array.get(i).length()));

                    indexes[i] = index2;

                } else if (index2 < array.get(i).length() - 1) {

                    isMoreLines = true;

                    int dif = index2 - (getWordEnd(index2, i, array.get(i)));

                    buf.setLength(dif);

                    buf.append(array.get(i).substring(index1, index2 + 1 - dif));

                    indexes[i] = getWordEnd(index2, i, array.get(i));

                }
            }

            strToPrint.append("\n");
        }
    }

    private void printTag() {

        StringBuilder buf = new StringBuilder();

        for (Integer val : widths) {

            for (int i = 0; i < val; i++) {

                buf.append(" ");
            }

            buf.append(" ");
        }

        if ((buf.length() - 2) < tag.length()) return;

        buf.setLength(buf.length() - 2 - tag.length());

        buf.insert(0, "|").append(tag).append(" |\n");

        strToPrint.append(buf);

        printLine('-');
    }

}
