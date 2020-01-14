package com.arvent.Repository;

import com.arvent.Entity.Product;

public interface CustomProductRepository {

    void saveBulkNewProducts(Iterable<Product> products);

    void saveBulkNewProductsHeightWidth(Iterable<Product> products, int startId);

    void refreshInformationSchema();
}
