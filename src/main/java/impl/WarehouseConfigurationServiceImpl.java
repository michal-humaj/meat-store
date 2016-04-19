package impl;

import api.WarehouseConfigurationService;
import com.google.gson.Gson;
import model.AppData;
import model.CoolingBox;
import model.Meat;
import model.Shelf;
import org.joda.time.LocalDate;

/**
 * Created by Rex on 19.4.2016.
 */
public class WarehouseConfigurationServiceImpl implements WarehouseConfigurationService {

    @Override
    public void initializateWarehouse(String inputJson) {
        Gson gson = new Gson();
        AppData appData = gson.fromJson(inputJson, AppData.class);

        for (CoolingBox coolingBox : appData.getWarehouse().getCoolingBoxes()) {
            for (Shelf shelf : coolingBox.getShelves()) {
                for (Meat meat : shelf.getMeat()) {
                    meat.setShelf(shelf);
                }
            }
        }

        CompanyProvider.getInstance().setAppData(appData);
        CompanyProvider.getInstance().setCurrentDate(LocalDate.now());
    }

    @Override
    public void shiftWarehouseSystemDate() {
        LocalDate date = CompanyProvider.getInstance().getCurrentDate();
        CompanyProvider.getInstance().setCurrentDate(date.plusDays(1));

        String report = Reports.generateCsvReportForMeatWithIncomingExpiryDate(CompanyProvider.getInstance().getAppData().getWarehouse());
        //send report to the manager
    }
}
