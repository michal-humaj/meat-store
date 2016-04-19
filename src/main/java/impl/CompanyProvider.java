package impl;

import model.AppData;
import org.joda.time.LocalDate;

/**
 * Created by Rex on 19.4.2016.
 */
public class CompanyProvider {

    private static CompanyProvider instance = new CompanyProvider();

    private AppData appData;
    private LocalDate currentDate;

    private CompanyProvider() {
    }

    public static CompanyProvider getInstance() {
        return instance;
    }

    public AppData getAppData() {
        return appData;
    }

    public void setAppData(AppData appData) {
        this.appData = appData;
    }

    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(LocalDate currentDate) {
        this.currentDate = currentDate;
    }
}

