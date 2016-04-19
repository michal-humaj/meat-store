import api.WarehouseConfigurationService;
import api.WarehouseManageService;
import impl.CompanyProvider;
import impl.WarehouseConfigurationServiceImpl;
import impl.WarehouseManagerServiceImpl;
import model.Meat;
import model.Warehouse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Rex on 19.4.2016.
 */
public class Main {

    public static void main(String[] args) {
        WarehouseManageService warehouseManageService = new WarehouseManagerServiceImpl();
        WarehouseConfigurationService warehouseConfigurationService = new WarehouseConfigurationServiceImpl();

        try {
            String jsonContent = new String(Files.readAllBytes(Paths.get("init1.json")));
            warehouseConfigurationService.initializateWarehouse(jsonContent);

            Warehouse warehouse = CompanyProvider.getInstance().getAppData().getWarehouse();
            Meat meat1 = new Meat();
            Meat meat2 = new Meat();
            Meat meat3 = new Meat();
            String jsonPickItem = new String(Files.readAllBytes(Paths.get("pickItemFromWarehouse.json")));
            String out = warehouseManageService.getPickingItemFromWarehouseByMeatType(jsonPickItem);



        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to load input json");
        }
    }
}
