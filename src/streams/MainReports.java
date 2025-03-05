package streams;

import streams.model.Sale;
import streams.reports.Reports;

import java.util.List;

public class MainReports {
    public static void main(String[] args) {
        List<Sale> sales = DataLoader.mixedSalesOnTheYear();

        Reports r = new Reports();
        r.runReportsOverData(sales);
    }
}
