package api;

public interface WarehouseConfigurationService {

	/**
	 * Naètení init JSON s informacemi o firmì a dispozicích skladu a nastaví
	 * systémový datum na 19.04.2016
	 * 
	 * @param init.json
	 */
	public void initializateWarehouse(String inputJson);

	/**
	 * Posunutí systémového data o jeden den.
	 * 
	 */
	public void shiftWarehouseSystemDate();

}
