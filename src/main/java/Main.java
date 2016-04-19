import api.WarehouseConfigurationService;
import api.WarehouseManageService;
import impl.CompanyProvider;
import impl.WarehouseConfigurationServiceImpl;
import impl.WarehouseManagerServiceImpl;
import model.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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

            Warehouse w = CompanyProvider.getInstance().getAppData().getWarehouse();
            putItemInStock();

            receiveShipments();

            addMeats();
            String jsonPickItem = new String(Files.readAllBytes(Paths.get("pickItemFromWarehouse.json")));
            String out = warehouseManageService.getPickingItemFromWarehouseByMeatType(jsonPickItem);
            System.out.printf("getPickingItemFromWarhouse" + out);

            String json = new String(Files.readAllBytes(Paths.get("meatOrderPlace.json")));
            String oout = warehouseManageService.preparationShipmentOfMeat(json);
            System.out.printf("getPickingItemFromWarhouse" + oout);

            String locationOfItemInWarehouse = warehouseManageService.getLocationOfItemInWarehouse("{\"type\":\"CHICKEN\",\"cooling-type\":\"COOLING\"}");
            System.out.println("Item location: " + locationOfItemInWarehouse);

            System.out.println("REPORT " + new String(warehouseManageService.generateReportOnCurrentState()));

            System.out.println("ejection " + warehouseManageService.ejectionItems());

            locationOfItemInWarehouse = warehouseManageService.getLocationOfItemInWarehouse("{\"type\":\"CHICKEN\",\"cooling-type\":\"COOLING\"}");
            System.out.println("Item location: " + locationOfItemInWarehouse);
            System.out.println("REPORT " + new String(warehouseManageService.generateReportOnCurrentState()));



        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to load input json");
        }


    }

    public static void addMeats() {
        Warehouse warehouse = CompanyProvider.getInstance().getAppData().getWarehouse();
        Meat meat1 = new Meat();
        Meat meat2 = new Meat();
        Meat meat3 = new Meat();
        final Meat meat4 = new Meat();

        meat1.setCount(2);
        meat1.setDate("08.08.2016");
        meat1.setFrozen(false);
        meat1.setMeatType(MeatType.CHICKEN);

        meat2.setCount(10);
        meat2.setDate("07.08.2015");
        meat2.setFrozen(false);
        meat2.setMeatType(MeatType.SALMON);

        meat3.setCount(2);
        meat3.setDate("08.03.2016");
        meat3.setFrozen(false);
        meat3.setMeatType(MeatType.CHICKEN);

        meat4.setCount(12);
        meat4.setDate("08.04.2015");
        meat4.setFrozen(false);
        meat4.setMeatType(MeatType.CHICKEN);

        List<Meat> meats = new ArrayList<>();
        meats.add(meat1);
        meats.add(meat2);
        meats.add(meat3);
        warehouse.getCoolingBoxes().get(0).getShelves().get(0).setMeat(meats);
        warehouse.getCoolingBoxes().get(1).getShelves().get(1).setMeat(new ArrayList<Meat>() {{
            add(meat4);
        }});

        for (CoolingBox coolingBox : warehouse.getCoolingBoxes()) {
            for(Shelf shelf : coolingBox.getShelves()) {
                shelf.setCoolingBox(coolingBox);
                for(Meat meat : shelf.getMeat()) {
                    meat.setShelf(shelf);
                }
            }
        }
    }

    public static void receiveShipments() throws IOException {
        WarehouseManageService warehouseManageService = new WarehouseManagerServiceImpl();

        String receivingShipments = new String(Files.readAllBytes(Paths.get("receivingShipments.json")));
        String out = warehouseManageService.receivingShipments(receivingShipments);
        System.out.println("receiving shipments" + out);
    }


    public static void putItemInStock() throws IOException {
        WarehouseManageService warehouseManageService = new WarehouseManagerServiceImpl();

        String addMeat = new String(Files.readAllBytes(Paths.get("meat.json")));
        String out = warehouseManageService.putItemInStock(addMeat);
        System.out.println("Add meat" + out);
    }

}
