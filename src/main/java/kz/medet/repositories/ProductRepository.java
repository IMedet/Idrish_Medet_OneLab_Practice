package kz.medet.repositories;

import kz.medet.model.Product;

public interface ProductRepository {
    Product findProductById(Long id);
    Product addProduct(String name, double price);
    void saveProduct(Product product);
}
