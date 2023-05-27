package pl.javastart.task;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class ProductReader {

    public List<Product> readAllProducts(String fileName) {
        List<Product> allProductList = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] strings = line.split(";");
                String name = strings[0];
                BigDecimal bigDecimal = new BigDecimal(strings[1]);
                String currency = strings[2];
                Product product = new Product(name, bigDecimal, currency);
                allProductList.add(product);
            }
        } catch (IOException e) {
            throw new UncheckedIOException("Nie udało się odczytać pliku " + fileName, e);
        }
        return allProductList;
    }

    public void printInfoAboutProductsInEuro(List<Product> allProductList, String currencyFileName) {
        CurrencyReader currencyReader = new CurrencyReader();
        List<Currency> currencies;
        try {
            currencies = currencyReader.readAllCurrencies(currencyFileName);
        } catch (IOException e) {
            throw new UncheckedIOException("Nie udało się odczytać pliku " + currencyFileName, e);
        }
        if (currencies.isEmpty()) {
            System.err.println("Pusta lista walut");
            return;
        } else if (allProductList.isEmpty()) {
            System.err.println("Pusta lista produktów");
            return;
        }

        BigDecimal sumOfAllProducts = new BigDecimal(0);
        BigDecimal firstProductCurrency = currencyReader.searchCurrency(allProductList.get(0).getCurrencyName(), currencies);

        BigDecimal theBiggestPrice = allProductList.get(0).getPrice().divide(firstProductCurrency, 2, RoundingMode.HALF_EVEN);
        BigDecimal theSmallestPrice = allProductList.get(0).getPrice().divide(firstProductCurrency, 2, RoundingMode.HALF_EVEN);

        Product theMostExpensiveProduct = allProductList.get(0);

        Product theCheapestProduct = allProductList.get(0);

        for (Product product : allProductList) {
            BigDecimal currencyPrice = currencyReader.searchCurrency(product.getCurrencyName(), currencies);
            BigDecimal priceInEuro = product.getPrice().divide(currencyPrice, 2, RoundingMode.HALF_EVEN);

            sumOfAllProducts = sumOfAllProducts.add(priceInEuro);

            if (priceInEuro.compareTo(theBiggestPrice) > 0) {
                theMostExpensiveProduct = product;
                theBiggestPrice = priceInEuro;
            }
            if (priceInEuro.compareTo(theSmallestPrice) < 0) {
                theCheapestProduct = product;
                theSmallestPrice = priceInEuro;
            }
        }
        theCheapestProduct = new Product(theCheapestProduct.getName(), theSmallestPrice, "EUR");
        theMostExpensiveProduct = new Product(theMostExpensiveProduct.getName(), theBiggestPrice, "EUR");
        BigDecimal averageAmountOfProduct = sumOfAllProducts.divide(BigDecimal.valueOf(allProductList.size()), 2, RoundingMode.HALF_EVEN);
        generalInfo(averageAmountOfProduct, sumOfAllProducts, theMostExpensiveProduct, theCheapestProduct);
    }

    private static void generalInfo(BigDecimal averageAmountOfProduct, BigDecimal sumOfAllProducts,
                                    Product theMostExpensiveProduct, Product theCheapestProduct) {
        System.out.println("Suma wszystkich produktów w euro " + sumOfAllProducts);
        System.out.println("Średnia wartość produktu w euro " + averageAmountOfProduct);
        System.out.println("Najdroższy produkt w euro: " + theMostExpensiveProduct);
        System.out.println("Najtańszy produkt w euro: " + theCheapestProduct);
    }

}