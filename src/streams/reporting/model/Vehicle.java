package streams.reporting.model;

import java.math.BigDecimal;

public class Vehicle {
    private Integer year;
    private String make;
    private String model;
    private String engine;
    private BigDecimal price;

    public Vehicle(int year, String make, String model, String engine, BigDecimal price) {
        this.year = year;
        this.make = make;
        this.model = model;
        this.engine = engine;
        this.price = price;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
