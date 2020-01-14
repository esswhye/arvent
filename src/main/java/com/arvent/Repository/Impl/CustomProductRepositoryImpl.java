package com.arvent.Repository.Impl;

import com.arvent.Entity.Product;
import com.arvent.Repository.CustomProductRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

@Repository
@Transactional
public class CustomProductRepositoryImpl implements CustomProductRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void saveBulkNewProducts(Iterable<Product> products) {


        StringBuilder queryProducts = new StringBuilder();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        queryProducts.append("INSERT INTO products (product_name, product_brand, product_price, product_discount, product_image_link, available_quantity,block_quantity, created_date, last_modified_date) VALUES ");
        Iterator<Product> productIterator = products.iterator();

        while(productIterator.hasNext()) {

            Product product = productIterator.next();
            queryProducts.append("(");
            queryProducts.append("\'" + product.getProductName()+ "\'");
            queryProducts.append(", ");
            queryProducts.append("\'" + product.getProductBrand()+ "\'");
            queryProducts.append(", ");
            queryProducts.append("\'" + product.getProductPrice() + "\'");
            queryProducts.append(", ");
            queryProducts.append("\'" + product.getProductDiscount() + "\'");
            queryProducts.append(", ");
            queryProducts.append("\'" + product.getProductImageLink()+ "\'");
            queryProducts.append(", ");
            queryProducts.append("\'" + product.getAvailableQuantity()+ "\'");
            queryProducts.append(", ");
            queryProducts.append("\'" + product.getBlockQuantity() + "\'");
            queryProducts.append(", ");
            LocalDateTime now = LocalDateTime.now();
            queryProducts.append("\'" + dtf.format(now) + "\'");
            queryProducts.append(", ");
            queryProducts.append("\'" + dtf.format(now) + "\'");
            queryProducts.append(")");

            if (productIterator.hasNext()){
                queryProducts.append(", ");
            }
        }
        Query query = entityManager.createNativeQuery(queryProducts.toString());
        entityManager.joinTransaction();
        query.executeUpdate();
    }

    @Override
    public void saveBulkNewProductsHeightWidth(Iterable<Product> products, int passedStartId) {

        int startId = passedStartId;
        StringBuilder queryProductHeightWidths = new StringBuilder();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        queryProductHeightWidths.append("INSERT INTO product_height_width ( product_height, product_width, product_id, created_date, last_modified_date) values");

        Iterator<Product> productIterator = products.iterator();

        while(productIterator.hasNext()) {

            Product product = productIterator.next();
            queryProductHeightWidths.append("(");
            queryProductHeightWidths.append("\'" + product.getProductHeightWidth().getProductHeight()+ "\'");
            queryProductHeightWidths.append(", ");
            queryProductHeightWidths.append("\'" + product.getProductHeightWidth().getProductWidth()+ "\'");
            queryProductHeightWidths.append(", ");
            queryProductHeightWidths.append("\'" + startId++ + "\'");
            queryProductHeightWidths.append(", ");
            LocalDateTime now = LocalDateTime.now();
            queryProductHeightWidths.append("\'" + dtf.format(now) + "\'");
            queryProductHeightWidths.append(", ");
            queryProductHeightWidths.append("\'" + dtf.format(now) + "\'");
            queryProductHeightWidths.append(")");

            if (productIterator.hasNext()){
                queryProductHeightWidths.append(", ");
            }
        }

        Query query = entityManager.createNativeQuery(queryProductHeightWidths.toString());
        entityManager.joinTransaction();
        query.executeUpdate();

    }

    @Override
    //InnoDB holds the auto_increment value in memory, and doesn't persist that to disk.
    public void refreshInformationSchema() {
        String refreshInformation = "SET @@SESSION.information_schema_stats_expiry = 0;";
        Query query = entityManager.createNativeQuery(refreshInformation);
        entityManager.joinTransaction();
        query.executeUpdate();
    }
/*
    public void updateProductQuantity(int count, int quantity, List<Long> productIdList)
    {
        StringBuilder queryUpdateProductQuantity = new StringBuilder();



    }
*/
}