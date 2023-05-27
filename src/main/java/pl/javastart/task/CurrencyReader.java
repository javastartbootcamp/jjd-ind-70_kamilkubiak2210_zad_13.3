package pl.javastart.task;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CurrencyReader {

    public List<Currency> readAllCurrencies(String fileName) throws IOException {
        List<Currency> allCurrencyList = new ArrayList<>();

        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] strings = line.split(";");
            String name = strings[0];
            BigDecimal price = new BigDecimal(strings[1]);
            Currency currience = new Currency(name, price);
            allCurrencyList.add(currience);
        }
        return allCurrencyList;
    }

    public BigDecimal searchCurrency(String currencySearchName, List<Currency> currencies) {
        for (Currency currency : currencies) {
            if (currencySearchName.equalsIgnoreCase(currency.getName())) {
                return currency.getPrice();
            }
        }
        return null;
    }
}
