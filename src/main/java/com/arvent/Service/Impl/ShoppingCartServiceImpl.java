package com.arvent.Service.Impl;

import com.arvent.DTO.ShoppingCartDTO;
import com.arvent.DTO.ShoppingCartItemListDTO;
import com.arvent.Entity.Customer;
import com.arvent.Entity.Product;
import com.arvent.Entity.ShoppingCart;
import com.arvent.Exception.CustomerException.CustomerNotFoundException;
import com.arvent.Exception.ShoppingCartException.OutOfStockException;
import com.arvent.Repository.ProductRepository;
import com.arvent.Repository.ShoppingCartRepository;
import com.arvent.Service.CustomerService;
import com.arvent.Service.ProductService;
import com.arvent.Service.ShoppingCartService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private ShoppingCartRepository shoppingCartRepository;
    private CustomerService customerService;

    @Override
    public ShoppingCart addItemToCart(Customer customer, Product product, int quantity)  {


        ShoppingCart shoppingCart = new ShoppingCart(customer, product, quantity);
        shoppingCartRepository.save(shoppingCart);
        return shoppingCart;
    }

    @Override
    public List<Product> getItemList(Long id) throws CustomerNotFoundException {

        List<ShoppingCart> shoppingCartList = shoppingCartRepository.findByCustomer(customerService.findCustomerById(id));

        List<Product> productList = shoppingCartList.stream().map(t-> t.getProduct()).collect(Collectors.toList());

        return productList;
    }

    public ShoppingCartDTO getItemList2(Long id) throws CustomerNotFoundException {

        List<ShoppingCart> shoppingCartList = shoppingCartRepository.findByCustomer(customerService.findCustomerById(id));

        //List<Product> productList = shoppingCartList.stream().map(t-> t.getProduct()).collect(Collectors.toList());

        Double totalCost = 0.0; //= shoppingCartList.stream().mapToDouble(t-> t.getTotalCost()).sum();

        List<ShoppingCartItemListDTO> shoppingCartItemListDTOS = new ArrayList<>();


        for (ShoppingCart item:shoppingCartList
             ) {
            shoppingCartItemListDTOS.add(ShoppingCartItemListDTO.builder()
                    .productId(item.getProduct().getId())
                    .productBrand(item.getProduct().getProductBrand())
                    .productUrl(item.getProduct().getProductImageLink())
                    .quantity(item.getQuantity())
                    .subTotal(item.getQuantity()*item.getProduct().getProductPrice())
                    .productPrice(item.getProduct().getProductPrice())
                    .orderId(item.getId())
                    .build());
            totalCost += (item.getQuantity()*item.getProduct().getProductPrice());
        }
        /*

        for (Product product: productList
             ) {
            shoppingCartItemListDTOS.add(ShoppingCartItemListDTO.builder()
                    .isAvailable(product.isAvailable())
                    .productBrand(product.getProductBrand())
                    .productId(product.getId())
                    .productUrl(product.getProductImageLink())
                    .subCost()


                    .build());
        }

           */
        ShoppingCartDTO shoppingCartDTO = ShoppingCartDTO.builder()
                .customerName(shoppingCartList.get(0).getCustomer().getUserName())
                .customerId(shoppingCartList.get(0).getCustomer().getId())
                .totalCost(totalCost)
                .itemList(shoppingCartItemListDTOS)
                .build();

        return shoppingCartDTO;
    }

    @Override
    @Transactional
    public void deleteItemsByShoppingCartId(List<ShoppingCartItemListDTO> itemList) {
        List<Long> itemIdList = itemList.stream().map(t->t.getOrderId()).collect(Collectors.toList());
        List<ShoppingCart> shoppingCartList = shoppingCartRepository.findAllById(itemIdList);
        shoppingCartRepository.deleteInBatch(shoppingCartList);
    }

    @Override
    public List<ShoppingCart> getShoppingCartByCustomerId(Long id) throws CustomerNotFoundException {

        Customer customer = customerService.findCustomerById(id);

        return shoppingCartRepository.findByCustomer(customer);
    }
}
