package kz.medet.repositories.impl;

import kz.medet.model.Product;
import kz.medet.repositories.ProductRepository;

import java.util.HashMap;
import java.util.Map;

public class ProductRepositoryImpl implements ProductRepository {

    private final Map<Long, Product> productMap = new HashMap<>();

    private Long init_Id = 1L;

    @Override
    public Product findProductById(Long id) {
        return productMap.get(id);
    }

    @Override
    public Product addProduct(String name, double price) {
        Product product = Product.builder()
                .id(init_Id)
                .name(name)
                .price(price)
                .build();
        productMap.put(init_Id, product);

        init_Id++;

        return product;
    }

    @Override
    public void saveProduct(Product product) {
        productMap.put(product.getId(), product);
    }
}
