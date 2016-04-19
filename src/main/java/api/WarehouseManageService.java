package api;

public interface WarehouseManageService {

	/** 1) Zjištìní umístìní položky
	 * 
	 * kde se nachází maso požadovaného typu a typu uskladnìní
	 * 
	 * @param { "type" : "CHUCKER", "cooling-type" : "FREEZING" }
	 * @return { "item-place" : [
	 *  	{"box-number" : "41", "shelf-number" : "k23", "count" : 102, "date-of-expiration" : "23.07.2016" },
	 *  	{"box-number" : "49", "shelf-number" : "e6", "count" : 44, "date-of-expiration" : "28.09.2016" }
	 *   ] }
	 */
	public String getLocationOfItemInWarehouse(String inputJson);

	
	/** 2) Vyskladnìní dle druhu masa
	 * 
	 * vyskladnuje maso ze skladu, jenž jde k zákazníkovy. Tedy položka pøestává existovat ve skladu.
	 * Skladník zadá druh masa, typ uskladnìní a množství v jednotkách. 
	 * Nepovinné zadání poèet dní, jak dlouho má maso ještì alespoò vydržet
	 * 
	 * @param {"type" : "CHICKEN", "count" : 8, "cooling-type" : "COOLING", "days-durability": 7 }

	 * @return { "item-place" : [
	 * 		{ "box-number" : "15", "shelf-number" : "m5", "count" : 2, "date-of-expiration" : "10.07.2016" },
	 * 		{ "box-number" : "54", "shelf-number" : "d2", "count" : 6, "date-of-expiration" : "30.06.2016" }
	 * 	] }
	 */
	public String getPickingItemFromWarehouseByMeatType(String inputJson);
	
	/** 3) Pøíprava a odeslání zásilky
	 * 
	 * vyskladneni vice polozek najednou
	 * 
	 * @param {"meat-order" : [ 
	 * 		{ "type" : "OPOSSUM", "count" : 126, "cooling-type" : "FREEZING", "days-durabilit" : 14 },
	 * 		{ "type" : "ALLIGATOR", "count" : 75, "cooling-type" : "FREEZING", "days-durabilit" : 14 },
	 * 		{ "type" : "SWEETBREADS", "count" : 132, "cooling-type" : "COOLING"} 
	 *  ] }
	 *  
	 * @return  { "meat-order-place" : [ 
	 * 		{ "box-number" : "64", "shelf-number" : "b19", "type" : "OPOSSUM", "count" : 126, "date-of-expiration" : "12.08.2016" },
	 * 		{ "box-number" : "77", "shelf-number" : "n10", "type" : "ALLIGATOR", "count" : 25, "date-of-expiration" : "20.10.2016" },
	 * 		{ "box-number" : "82", "shelf-number" : "x15", "type" : "ALLIGATOR", "count" : 50, "date-of-expiration" : "20.10.2016" },
	 * 		{ "box-number" : "12", "shelf-number" : "l23", "type" : "SWEETBREADS", "count" : 132, "date-of-expiration" : "26.04.2016" }
	 * 	] }
	 * 
	 */
	public String preparationShipmentOfMeat(String inputJson);

	/** 4) Pøíprava a odeslání zásilky
	 * 	
	 * Skladník má v ruce pøepravku s masem a chce ji umístit do skladu.
	 * Aplikace nalezne volné místo a vrátí umístìní, kam maso zaøadil.

	 * @param { "type" : "PORK", "count" : 84, "date-of-slaughter" : "25.02.2016", "is-frozen" : false }
	 * @return { "item-place" : [ 
	 * 		{ "box-number" : "92","shelf-number" : "f6","count" : 84 }
	 * 	] }
	 * 
	 */
	public String putItemInStock(String inputJson);
	
	/** 5) Pøijmutí zásilky - nepovinná
	 * 
	 * Pøed skladem stojí dodávka plná masa a skladník chce naskladnit všechny položky najednou.
	 * 
	 * @param { "meat-item" : [ 
	 * 		{ "type" : "PUFFIN", "count" : 90, "date-of-slaughter" : "22.01.2016", "is-frozen" : false },
	 * 		{ "type" : "CRAB", "count" : 12, "date-of-slaughter" : "19.01.2016", "is-frozen" : false }
	 *  ] }
	 * @return { "item-place" : [ 
	 *  	{ "type" : "PUFFIN", "box-number" : "58", "shelf-number" : "h5", "count" : 90 },
	 *  	{ "type" : "CRAB", "box-number" : "75", "shelf-number" : "m27", "count" : 1 },
	 *  	{ "type" : "CRAB", "box-number" : "81", "shelf-number" : "y9", "count" : 11 }
	 *   ] }

	 */
	public String receivingShipments(String inputJson);
		
	/** 7) Generování reportù o aktuálním stavu
	 * 
	 * Øiditel chce sem tam vìdìl aktuální stav skladu, 
	 * aby vìdìl co mu dochází a má získat od dodavatelù 
	 * nebo naopak co už nemá brát.
	 * 
	 * @return cvs
	 * V reportu bude ve formátu csv a pro každé maso: 
	 * 	druh masa, 
	 * 	kolik pøepravek, 
	 *  kdy konèí trvanlivost,
	 *  je-åi mražené
	 * 
	 */
	public byte[] generateReportOnCurrentState();
	
	/** 8) Vyhození položek
	 * 	
	 * Jde o vyhození prošlého masa ze skladu. 
	 * Skladník dostane seznam všech položek s jejich umístìním, 
	 * které jsou k dnešnímu datu prošlé a položky pøestanou existovat ve skladu.
	 * 
	 * @return { "item-place" : [ 
	 * 		{ "box-number" : "39", "shelf-number" : "g24", "count" : 42  }, 
	 * 		{ "box-number" : "78", "shelf-number" : "g1", "count" : 84  },
	 * 		{ "box-number" : "71", "shelf-number" : "d13", "count" : 66  },	
	 * 		{ "box-number" : "31", "shelf-number" : "p6", "count" : 142 }
	 * 	] }
	 * 
	 */
	public String ejectionItems();

	/** 9) Pøeskladnìní položky
	 * 
	 * Skladník zadá typ masa, datum expirace a množství, jeho souèasné umístìní a budoucí umístìní. 
	 * Systém bude považovat položku za pøesunutou na nové umístìní.
	 * 
	 * @param { 
	 * 		"type" : "SWEETBREADS", "count" : 27,  "date-of-expiration" : "27.05.2016",
	 * 		"current-item-place" : { "box-number" : "11","shelf-number" : "p28"},
	 * 		"new-item-place" : { "box-number" : "30", "shelf-number" : "a12" }
	 * 	}

	 */
	public void moveItem(String inputJson);
	
	/** 10) Vyprázdnìní místnosti pro vyèistìní
	 * 
	 * Sem tam je potøeba vyèistit nebo opravit místnost pro skladovaní. 
	 * Proto je nutné všechny položky v místnosti pøesunout do jiných místností.
	 * Skladník zadá èíslo místnosti
	 * Systém vrátí seznam všech položek v místnosti:
	 * 		typ masa, datum expirace a množství, jeho souèasné umístìní a budoucí umístìní 
	 * Systém bude nadále považovat položky za pøesunuté.
	 * 
	 * @param { "boxNumber" : "10" }
	 */
	public String emptyCoolingBoxForCleaning(String inputJson);
	
}
