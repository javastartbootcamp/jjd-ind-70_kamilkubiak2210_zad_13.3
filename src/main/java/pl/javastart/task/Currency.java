package pl.javastart.task;

import java.math.BigDecimal;

public class Currency {
    private final String name;
    private final BigDecimal price;

    public Currency(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + " " + price;
    }
}
