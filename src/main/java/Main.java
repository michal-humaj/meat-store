import api.WarehouseConfigurationService;
import api.WarehouseManageService;
import impl.CompanyProvider;
import impl.WarehouseConfigurationServiceImpl;
import impl.WarehouseManagerServiceImpl;
import model.AppData;
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

            String out = warehouseManageService
                    .putItemInStock("{ \"type\" : \"PORK\", \"count\" : 14, \"date-of-slaughter\" : \"25.02.2016\", \"is-frozen\" : false }");

            Warehouse w = CompanyProvider.getInstance().getAppData().getWarehouse();
            System.out.printf(out);

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to load input json");
        }


    }
}
