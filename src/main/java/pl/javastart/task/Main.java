package pl.javastart.task;

import java.io.UncheckedIOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        ProductReader productReader = new ProductReader();
        try {
            List<Product> products = productReader.readAllProducts("src/main/resources/products.csv");
            productReader.printInfoAboutProductsInEuro(products, "src/main/resources/currencies.csv");
        } catch (UncheckedIOException e) {
            System.err.println(e.getMessage());
        }

    }
}
