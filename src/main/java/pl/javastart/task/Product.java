package pl.javastart.task;

import java.math.BigDecimal;

public class Product {
    private final String name;
    private final BigDecimal price;
    private String currencyName;

    public Product(String name, BigDecimal price, String currencyName) {
        this.name = name;
        this.price = price;
        this.currencyName = currencyName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    @Override
    public String toString() {
        return name + " " + price + " " + currencyName;
    }
}

