package EPAM2015_lab8;

import EPAM2015_lab8.checkers.SkiPassChecker;

import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Main {
    private static SkiPassManager manager;
    private static SkiPassGate[] gates;
    private static SkiPassType[] types;
    private static SkiPass[] skiPasses;

    private static void setSeasonBegin() {
        GregorianCalendar seasonBegin = new GregorianCalendar();
        seasonBegin.set(Calendar.HOUR_OF_DAY, 0);
        seasonBegin.set(Calendar.MINUTE, 0);
        seasonBegin.set(Calendar.SECOND, 0);
        seasonBegin.set(Calendar.MILLISECOND, 0);
        SkiPassChecker.setSeasonBegin(seasonBegin.getTime());
    }

    private static void setSeasonEnd() {
        GregorianCalendar seasonEnd = new GregorianCalendar();
        seasonEnd.set(Calendar.HOUR_OF_DAY, 23);
        seasonEnd.set(Calendar.MINUTE, 59);
        seasonEnd.set(Calendar.SECOND, 59);
        seasonEnd.set(Calendar.MILLISECOND, 999);
        SkiPassChecker.setSeasonEnd(seasonEnd.getTime());
    }

    private static void setDayBegin() {
        SkiPassChecker.setDayBegin(new int[]{0, 0});
    }

    private static void setDayEnd() {
        SkiPassChecker.setDayEnd(new int[]{23, 59});
    }

    private static void initGates(int quant) {
        gates = new SkiPassGate[quant];
        for (int i = 0; i < quant; i++) {
            gates[i] = new SkiPassGate(manager);
        }
    }

    private static void initTypes() {
        types = SkiPassType.values();
    }

    private static void initSkiPasses(int quant){
        skiPasses = new SkiPass[quant];
        for (int i = 0; i < quant; i++) {
            int typeRandomIndex = (int)(Math.random() * types.length);
            skiPasses[i] = manager.issueSkiPass(types[typeRandomIndex]);
        }
    }

    private static void startIterations(int iterQuantity){
        for (int i = 0; i < iterQuantity; i++) {
            if (i == (int)(Math.random() * iterQuantity)){
                manager.blockSkiPass(skiPasses[(int)(Math.random() * skiPasses.length)].getId());
            }
            SkiPass curSkiPass = skiPasses[(int) (Math.random() * skiPasses.length)];
            SkiPassGate curGate = gates[(int) (Math.random() * gates.length)];
            curGate.checkSkiPass(curSkiPass);
        }
    }

    public static void main(String[] args) throws ParseException {
        setSeasonBegin();
        setSeasonEnd();
        setDayBegin();
        setDayEnd();

        manager = new SkiPassManager();

        int gatesNumber = 5;
        initGates(gatesNumber);

        initTypes();

        int skiPassNumber = 20;
        initSkiPasses(skiPassNumber);

        int iterQuantity = 100;
        startIterations(iterQuantity);

        manager.showFullReport();
        manager.showGroupedBySkiPassTypeReport();
    }
}
