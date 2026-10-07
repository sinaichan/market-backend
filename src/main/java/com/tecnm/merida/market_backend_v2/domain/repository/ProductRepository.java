package com.tecnm.merida.market_backend_v2.domain.repository;
import com.tecnm.merida.market_backend_v2.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    List<Product>getAll();
    Optional<List<Product>> getByCategory(int categoryId);
    Optional<Product> getScareProducts(int quantity);
    Product save (Product product);
    Product delete(Product product);

}
