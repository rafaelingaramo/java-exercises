package streams.reporting;

import streams.reporting.model.Customer;
import streams.reporting.model.Sale;
import streams.reporting.model.Vehicle;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataLoader {
    private static final String[] CUSTOMER_NAMES = {
            "Aria Montgomery",
            "Finn Donovan",
            "Elena Bishop",
            "Mason Harper",
            "Zoe Carter",
            "Liam Kensington",
            "Ava Sinclair",
            "Theo Bennett",
            "Isla Cooper",
            "Nathaniel Brooks"
    };

    private static final Vehicle[] VEHICLES = {
            new Vehicle(2021, "Toyota", "Camry", "2.5L I4", new BigDecimal("24500.00")),
            new Vehicle(2020, "Ford", "Mustang", "5.0L V8", new BigDecimal("43000.00")),
            new Vehicle(2018, "Chevrolet", "Silverado", "6.2L V8", new BigDecimal("55000.00")),
            new Vehicle(2022, "Tesla", "Model 3", "Electric", new BigDecimal("39999.00")),
            new Vehicle(2019, "Honda", "Civic", "1.5L I4", new BigDecimal("22000.00")),
            new Vehicle(2021, "BMW", "X5", "3.0L I6", new BigDecimal("62000.00")),
            new Vehicle(2020, "Nissan", "Altima", "2.5L I4", new BigDecimal("25000.00")),
            new Vehicle(2017, "Audi", "A4", "2.0L I4 Turbo", new BigDecimal("35000.00")),
            new Vehicle(2023, "Mercedes-Benz", "E-Class", "3.0L V6 Turbo", new BigDecimal("68000.00")),
            new Vehicle(2022, "Jeep", "Wrangler", "3.6L V6", new BigDecimal("48000.00"))
    };

    public static List<Sale> mixedSalesOnTheYear() {
        ArrayList<Sale> sales = new ArrayList<>();
        for (int d=0; d<100; d++) {
            Sale sale = new Sale();
            sale.setCustomer(getRandomCustomerBasedOnIndex(d));
            sale.setDate(getDateBasedOnIndex(d));
            sale.setVehicles(getRandomVehiclesBasedOnIndex(d));
            sale.setTotal(getTotalCalculatedBasedOnVehicles(sale.getVehicles()));
            sales.add(sale);
        }

        return sales;
    }

    private static BigDecimal getTotalCalculatedBasedOnVehicles(List<Vehicle> vehicles) {
        return vehicles.stream()
                .map(Vehicle::getPrice)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    private static List<Vehicle> getRandomVehiclesBasedOnIndex(int index) {
        int multiplier = index < 10 ? index : index % 10;

        return new ArrayList<>(Arrays.asList(VEHICLES).subList(0, multiplier + 1));
    }


    private static LocalDate getDateBasedOnIndex(int index) {
        int dayOfMonth = index + 1 < 28 ? index + 1 : (index % 28) + 1;
        return LocalDate.of(2024, index + 1 < 12 ? index + 1 : (index % 12) + 1, dayOfMonth);
    }

    private static Customer getRandomCustomerBasedOnIndex(int index) {
        Customer c = new Customer();
        c.setName(CUSTOMER_NAMES[index < 10 ? index : index % 10]);
        return c;
    }
}
