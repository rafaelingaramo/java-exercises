package streams.reporting.model;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

public class Sale {
    private BigDecimal total;
    private Customer customer;
    private List<Vehicle> vehicles;
    private LocalDate date;

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "date=" + date +
                ", customer=" + customer +
                ", total=" + getFormattedTotal();
    }

    private String getFormattedTotal() {
        if (total == null) {
            return "$0.00";
        }

        NumberFormat instance = DecimalFormat.getCurrencyInstance(Locale.US);
        return instance.format(total);
    }
}
