package pl.javastart.task;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Products products = new Products();
        List<Products> allProducts;
        try {
            allProducts = products.readAllProducts();
        } catch (IOException e) {
            throw new RuntimeException("Nie udało się wczytać listy produktów");
        }
        try {
            products.print(allProducts);
        } catch (IOException e) {
            throw new RuntimeException("Nie udało się wczytać listy walut");
        }
    }
}
