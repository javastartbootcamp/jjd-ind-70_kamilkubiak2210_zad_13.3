package pl.javastart.task;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Products {
    private String name;
    private BigDecimal price;
    private String currencyName;
    private final String fileName = "src/main/resources/products.csv";

    public Products(String name, BigDecimal price, String currencyName) {
        this.name = name;
        this.price = price;
        this.currencyName = currencyName;
    }

    public Products() {
    }

    public List<Products> readAllProducts() throws IOException {
        List<Products> allProductsList = new ArrayList<>();

        BufferedReader bufferedReader;
        bufferedReader = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] strings = line.split(";");
            String name = strings[0];
            BigDecimal bigDecimal = new BigDecimal(strings[1]);
            String currency = strings[2];
            Products product = new Products(name, bigDecimal, currency);
            allProductsList.add(product);
        }
        if (allProductsList.isEmpty()) {
            throw new NullPointerException("Lista produktów jest pusta");
        }

        return allProductsList;
    }

    public void print(List<Products> allProductsList) throws IOException {
        Currencies currencies = new Currencies();
        BigDecimal sumOfAllProducts = new BigDecimal(0);
        BigDecimal averageAmountOfProduct = new BigDecimal(0);
        BigDecimal firstProductCurrency = currencies.searchCurrency(allProductsList.get(0).currencyName);

        BigDecimal theBiggestPrice = allProductsList.get(0).price.divide(firstProductCurrency, 2, RoundingMode.HALF_EVEN);
        BigDecimal theSmallestPrice = allProductsList.get(0).price.divide(firstProductCurrency, 2, RoundingMode.HALF_EVEN);

        String theBiggestPriceProduct = allProductsList.get(0).name + " " + allProductsList.get(0).price;

        String theSmallestPriceProduct = allProductsList.get(0).name + " " + allProductsList.get(0).price;

        for (Products product : allProductsList) {
            BigDecimal productsListSize = new BigDecimal(allProductsList.size());
            BigDecimal currencyPrice = currencies.searchCurrency(product.currencyName);
            BigDecimal priceInEuro = product.price.divide(currencyPrice, 2, RoundingMode.HALF_EVEN);

            sumOfAllProducts = sumOfAllProducts.add(priceInEuro);
            averageAmountOfProduct = sumOfAllProducts.divide(productsListSize, 2, RoundingMode.HALF_EVEN);

            if (priceInEuro.compareTo(theBiggestPrice) > 0) {
                theBiggestPriceProduct = product.name + " " + priceInEuro;
            }
            if (priceInEuro.compareTo(theSmallestPrice) < 0) {
                theSmallestPriceProduct = product.name + " " + priceInEuro;
            }
        }
        System.out.println("Suma wszystkich produktów w euro " + sumOfAllProducts);
        System.out.println("Średnia wartość produktu w euro " + averageAmountOfProduct);
        System.out.println("Najdroższy produkt w euro: " + theBiggestPriceProduct);
        System.out.println("Najtańszy produkt w euro: " + theSmallestPriceProduct);

    }

    @Override
    public String toString() {
        return name + " " + price + " " + currencyName;
    }
}
