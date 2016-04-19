package impl;

import dto.MeatGroup;
import model.Warehouse;
import org.joda.time.Days;
import util.DateConverter;

import java.util.Map;

/**
 * Created by Rex on 19.4.2016.
 */
public class Reports {

    public static String generateCsvReport(Warehouse warehouse) {
        return generateCsvReport(warehouse, -1);
    }

    public static String generateCsvReportForMeatWithIncomingExpiryDate(Warehouse warehouse) {
        return generateCsvReport(warehouse, 10);
    }

    private static String generateCsvReport(Warehouse warehouse, int daysToExpiry) {
        StringBuilder csv = new StringBuilder();
        addReportHeader(csv);

        for (Map.Entry<MeatGroup, Integer> meatGroupCount :
                WarehouseTools.getMeatCountByType(warehouse).entrySet()) {

            MeatGroup meatGroup = meatGroupCount.getKey();

            Days dayDif = Days.daysBetween(CompanyProvider.getInstance().getCurrentDate().toDateTimeAtStartOfDay(), DateConverter.toDateTimeDots(meatGroup.getExpiryDate()));
            if (daysToExpiry < 0 || dayDif.getDays() <= daysToExpiry) {
                int count = meatGroupCount.getValue();
                addReportLine(csv, meatGroup, count);
            }

        }

        return csv.toString();
    }


    private static void addReportLine(StringBuilder csv, MeatGroup meatGroup, int count) {
        csv.append(String.format("%s,%s,%s,%s", meatGroup.getMeatType(), count, meatGroup.getExpiryDate(), meatGroup.getBoxType()));
        csv.append(System.lineSeparator());
    }

    private static void addReportHeader(StringBuilder csv) {
        csv.append("meat-type,count,expiry-date,freezed" + System.lineSeparator());
    }
}
