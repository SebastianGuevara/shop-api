package com.shopapi.shopapi.service;

import com.shopapi.shopapi.data.Product;
import com.shopapi.shopapi.repository.IShopRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Service
public class ShopService implements IShopService {
    private final IShopRepository shopRepository;

    @Override
    public Product addProduct(Product product) {
        if (0 > product.getCode())
            throw new RuntimeException("No se puede crear un producto con stock negativo");
        return shopRepository.save(product);
    }

    @Override
    public String sellProducts(List<Product> products) {
        Map<String, Integer> soldProducts = new HashMap<>();
        float totalPrice = 0;

        for (Product product : products) {
            Product stockProduct = shopRepository.findById(product.getCode()).get();
            if (0 <= stockProduct.getStock() - product.getStock()) {
                stockProduct.setStock(stockProduct.getStock() - product.getStock());
                totalPrice += stockProduct.getUnitPrice();
                soldProducts.put(stockProduct.getName(), product.getStock());
                shopRepository.save(stockProduct);
            } else {
                throw new RuntimeException(String.format("You can't buy %d of %s because it only has %d units available.", product.getStock(), stockProduct.getName(), stockProduct.getStock()));
            }
        }
        return String.format("You sold: %s. And the total price was %f", soldProducts, totalPrice);
    }

    @Override
    public Product updateStock(Integer code, Integer stockToAdd) {
        Product product = shopRepository.findById(code).get();
        if (0 < stockToAdd) {
            product.setStock(product.getStock() + stockToAdd);
        } else {
            throw new RuntimeException("Cant not add negative stock");
        }
        return shopRepository.save(product);
    }

    @Override
    public List<Product> getProducts() {
        return shopRepository.findAll();
    }
}
