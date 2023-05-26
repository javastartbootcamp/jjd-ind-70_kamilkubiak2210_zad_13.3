package pl.javastart.task;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        ProductReader productReader = new ProductReader();
        List<Product> products = null;
        try {
            products = productReader.readAllProducts();
        } catch (IOException e) {
            System.err.println("Nie udało się wczytać pliku");
        }
        try {
            productReader.printInfoAboutProductsInEuro(products, new CurrencyReader());
        } catch (IOException e) {
            System.err.println("Nie udało się wyświetlić informacji");
        }

    }
}
