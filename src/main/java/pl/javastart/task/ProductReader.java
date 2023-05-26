package pl.javastart.task;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class ProductReader {

    public List<Product> readAllProducts() throws IOException {
        List<Product> allProductList = new ArrayList<>();

        BufferedReader bufferedReader;
        String fileName = "src/main/resources/products.csv";
        bufferedReader = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] strings = line.split(";");
            String name = strings[0];
            BigDecimal bigDecimal = new BigDecimal(strings[1]);
            String currency = strings[2];
            Product product = new Product(name, bigDecimal, currency);
            allProductList.add(product);
        }
        return allProductList;
    }

    public void printInfoAboutProductsInEuro(List<Product> allProductList, CurrencyReader currencyReader) throws IOException {
        List<Currency> currencies = currencyReader.readAllCurrencies();

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
        theMostExpensiveProduct.setCurrencyName("EUR");
        theCheapestProduct.setCurrencyName("EUR");
        generalInfo(allProductList, sumOfAllProducts, theMostExpensiveProduct, theCheapestProduct);
    }

    private static void generalInfo(List<Product> allProductList, BigDecimal sumOfAllProducts, Product theMostExpensiveProduct, Product theCheapestProduct) {
        BigDecimal averageAmountOfProduct = sumOfAllProducts.divide(BigDecimal.valueOf(allProductList.size()), 2, RoundingMode.HALF_EVEN);
        System.out.println("Suma wszystkich produktów w euro " + sumOfAllProducts);
        System.out.println("Średnia wartość produktu w euro " + averageAmountOfProduct);
        System.out.println("Najdroższy produkt w euro: " + theMostExpensiveProduct);
        System.out.println("Najtańszy produkt w euro: " + theCheapestProduct);
    }

}