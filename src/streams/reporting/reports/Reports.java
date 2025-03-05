package streams.reporting.reports;

import streams.reporting.model.Sale;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class Reports {

    private static final NumberFormat CURRENCY_INSTANCE = DecimalFormat.getCurrencyInstance(Locale.US);
    public void runReportsOverData(List<Sale> sales) {
        salesGroupedByMonth(sales);
        salesByCustomerSearch(sales, "Ar");
        salesByCustomerSearch(sales, "Z");
        top10CustomersInPeriod(sales, LocalDate.of(2024, 1, 1), LocalDate.of(2024, 2, 1));
        top10CustomersInPeriod(sales, LocalDate.of(2024, 1, 1), LocalDate.of(2024, 6, 1));
        top10CustomersInPeriod(sales, LocalDate.of(2024, 6, 1), LocalDate.of(2024, 12, 1));
        top10CustomersInPeriod(sales, LocalDate.of(2024, 1, 1), LocalDate.of(2024, 12, 1));
    }

    private void salesGroupedByMonth(List<Sale> sales) {

        System.out.println("\n ========== SALES PER MONTH ========== ");
        System.out.println(" || MONTH     || TOTAL ");

        Map<Month, Double> groupedByMonth = sales.stream()
                .sorted(Comparator.comparing(Sale::getDate))
                .collect(Collectors.groupingBy(s -> s.getDate().getMonth(), Collectors.summingDouble(s -> s.getTotal().doubleValue())));
        groupedByMonth.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(keyEntry -> System.out.printf(" || %-9s || %s \n", keyEntry.getKey(), CURRENCY_INSTANCE.format(keyEntry.getValue())));

        Map<Month, Optional<Sale>> biggestPurchasePerMonth = sales.stream()
                .collect(Collectors.groupingBy(s -> s.getDate().getMonth(), Collectors.maxBy(Comparator.comparing(Sale::getTotal))));

        System.out.println("\n========== TOP 1 PURCHASE PER MONTH ========== ");
        System.out.println(" || MONTH     || SALE ");
        biggestPurchasePerMonth.keySet()
                .stream().sorted()
                .forEach(m -> {
                    System.out.printf(" || %-9s || %s \n", m, biggestPurchasePerMonth.get(m).orElseThrow());
                });

        System.out.println("\n========== MOST PROFITABLE MONTH ==========\n ");
        Map<Month, Double> totalPerMonth = sales.stream()
                .collect(Collectors.groupingBy(s -> s.getDate().getMonth(), Collectors.summingDouble(s -> s.getTotal().doubleValue())));

        Map.Entry<Month, Double> monthDoubleEntry = totalPerMonth.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .orElseThrow();

        System.out.printf(" MONTH: %s - %s \n\n", monthDoubleEntry.getKey().toString(), CURRENCY_INSTANCE.format(monthDoubleEntry.getValue()));
    }

    private void salesByCustomerSearch(List<Sale> sales, String searchKey) {

        System.out.printf("\n ========== SALES PER MONTH BY FILTER: %s ========== \n", searchKey);
        System.out.println(" || DATE          || CUSTOMER         || TOTAL ");

        sales.stream()
                .filter(sale -> containsKey(searchKey, sale))
                .sorted(Comparator.comparing(Sale::getDate))
                .forEach(s -> {
                    System.out.printf(" || %-13s || %-16s || %s \n", s.getDate(), s.getCustomer(), CURRENCY_INSTANCE.format(s.getTotal()));
                });

        BigDecimal sumTotal = sales.stream()
                .filter(sale -> containsKey(searchKey, sale))
                .map(Sale::getTotal)
                .reduce(BigDecimal::add)
                .orElseThrow();
        System.out.printf(" SUBTOTAL: %s\n\n", CURRENCY_INSTANCE.format(sumTotal));
    }

    private static boolean containsKey(String searchKey, Sale sale) {
        return sale.getCustomer().getName().toLowerCase().contains(searchKey.toLowerCase());
    }

    private void top10CustomersInPeriod(List<Sale> sales, LocalDate start, LocalDate end) {
        System.out.printf("\n ========== TOP 10 SALES PER PERIOD BY FILTER %s - %s  ========== \n",
                start, end);
        System.out.println(" || DATE          || CUSTOMER         || TOTAL ");
        sales
                .stream()
                .filter(sale -> isBetweenDates(sale.getDate(), start, end))
                .sorted(Comparator.comparing(Sale::getTotal).reversed().thenComparing(Sale::getDate))
                .limit(10)
                .forEach(s -> System.out.printf(" || %-13s || %-16s || %s \n", s.getDate(), s.getCustomer(), CURRENCY_INSTANCE.format(s.getTotal())));
    }


    private boolean isBetweenDates(LocalDate toBeCompared, LocalDate start, LocalDate end) {
        return (toBeCompared.isAfter(start) || toBeCompared.isEqual(start)) &&
                (toBeCompared.isBefore(end) || toBeCompared.isEqual(end));
    }
}
