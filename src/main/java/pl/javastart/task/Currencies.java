package pl.javastart.task;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Currencies {
    private String name;
    private BigDecimal price;
    private final String fileName = "src/main/resources/currencies.csv";

    public Currencies(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public Currencies() {
    }

    public List<Currencies> readAllCurrencies() throws IOException {
        List<Currencies> allCurrenciesList = new ArrayList<>();

        BufferedReader bufferedReader;
        bufferedReader = new BufferedReader(new FileReader(fileName));

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] strings = line.split(";");
            String name = strings[0];
            BigDecimal price = new BigDecimal(strings[1]);
            Currencies currience = new Currencies(name, price);
            allCurrenciesList.add(currience);
        }
        if (allCurrenciesList.isEmpty()) {
            throw new NullPointerException("Lista walut jest pusta");
        }
        return allCurrenciesList;
    }

    public BigDecimal searchCurrency(String currencySearchName) throws IOException {
        List<Currencies> currencies = readAllCurrencies();
        BigDecimal priceOfTheSearchedCurrency;
        priceOfTheSearchedCurrency = null;
        for (Currencies currency : currencies) {
            if (currencySearchName.equalsIgnoreCase(currency.name)) {
                priceOfTheSearchedCurrency = currency.price;
            }
        }
        return priceOfTheSearchedCurrency;
    }

    @Override
    public String toString() {
        return name + " " + price;
    }
}
