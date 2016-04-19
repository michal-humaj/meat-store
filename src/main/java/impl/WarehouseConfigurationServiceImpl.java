package impl;

import api.WarehouseConfigurationService;
import com.google.gson.Gson;
import model.AppData;
import org.joda.time.LocalDate;

/**
 * Created by Rex on 19.4.2016.
 */
public class WarehouseConfigurationServiceImpl implements WarehouseConfigurationService {

    @Override
    public void initializateWarehouse(String inputJson) {
        Gson gson = new Gson();
        AppData appData = gson.fromJson(inputJson, AppData.class);

        CompanyProvider.getInstance().setAppData(appData);
        CompanyProvider.getInstance().setCurrentDate(LocalDate.now());
    }

    @Override
    public void shiftWarehouseSystemDate() {
        LocalDate date = CompanyProvider.getInstance().getCurrentDate();
        CompanyProvider.getInstance().setCurrentDate(date.plusDays(1));

        String report = Reports.generateCsvReport(CompanyProvider.getInstance().getAppData().getWarehouse());
        //send report to the manager
    }
}
