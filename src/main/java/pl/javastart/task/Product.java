package pl.javastart.task;

import java.math.BigDecimal;

public class Product {
    private final String name;
    private final BigDecimal price;
    private final String currencyName;

    public Product(String name, BigDecimal price, String currencyName) {
        this.name = name;
        this.price = price;
        this.currencyName = currencyName;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    @Override
    public String toString() {
        return name + " " + price + " " + currencyName;
    }
}

