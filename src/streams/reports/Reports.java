package streams.reports;

import streams.model.Sale;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class Reports {
    public void runReportsOverData(List<Sale> sales) {
        salesGroupedByMonth(sales);
//        salesByCustomerSearch(sales, "A");
//        salesByCustomerSearch(sales, "Z");
//        top10CustomersInPeriod(sales, LocalDate.of(2024, 1, 1), LocalDate.of(2024, 2, 1));
//        top10CustomersInPeriod(sales, LocalDate.of(2024, 1, 1), LocalDate.of(2024, 6, 1));
//        top10CustomersInPeriod(sales, LocalDate.of(2024, 6, 1), LocalDate.of(2024, 12, 1));
//        top10CustomersInPeriod(sales, LocalDate.of(2024, 1, 1), LocalDate.of(2024, 12, 1));
    }

    private void salesGroupedByMonth(List<Sale> sales) {

        System.out.println(" ========== SALES PER MONTH ========== ");
        System.out.println(" || MONTH     || TOTAL ");
        Map<Month, List<Sale>> groupedByMonth = sales.stream()
                .collect(Collectors.groupingBy(s -> s.getDate().getMonth()));
        Map<Month, Sale> biggestPurchasePerMonth = new HashMap<>();

        groupedByMonth.keySet()
                .stream().sorted()
                .forEach(m -> {
                    List<Sale> salesByMonth = groupedByMonth.get(m);
                    BigDecimal totalPerMonth = salesByMonth.stream()
                            .map(Sale::getTotal)
                            .reduce(BigDecimal::add)
                            .orElse(BigDecimal.ZERO);
                    Sale sale = salesByMonth.stream()
                            .max(Comparator.comparing(Sale::getTotal))
                            .orElse(null);
                    biggestPurchasePerMonth.put(m, sale);
                    System.out.printf(" || %-9s || %s \n", m.toString(), DecimalFormat.getCurrencyInstance(Locale.US).format(totalPerMonth));
                });
        System.out.println("\n========== TOP 1 PURCHASE PER MONTH ========== ");
        System.out.println(" || MONTH     || SALE ");
        biggestPurchasePerMonth.keySet()
                .stream().sorted()
                .forEach(m -> {
                    System.out.printf(" || %-9s || %s \n", m, biggestPurchasePerMonth.get(m));
                });
        System.out.println("\n========== MOST PROFITABLE MONTH ==========\n ");




    }

    private void salesByCustomerSearch(List<Sale> sales, String searchKey) {

    }

    private void top10CustomersInPeriod(List<Sale> sales, LocalDate start, LocalDate end) {

    }

}
