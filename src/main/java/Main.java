import api.WarehouseConfigurationService;
import api.WarehouseManageService;
import impl.WarehouseConfigurationServiceImpl;
import impl.WarehouseManagerServiceImpl;

/**
 * Created by Rex on 19.4.2016.
 */
public class Main {

    public static void main(String[] args) {
        WarehouseManageService warehouseManageService = new WarehouseManagerServiceImpl();
        WarehouseConfigurationService warehouseConfigurationService = new WarehouseConfigurationServiceImpl();
    }
}
