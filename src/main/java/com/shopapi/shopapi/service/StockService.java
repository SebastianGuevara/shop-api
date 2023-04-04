package com.shopapi.shopapi.service;

import com.shopapi.shopapi.data.Stock;
import com.shopapi.shopapi.repository.IStockRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Service
public class StockService implements IStockService {
    private final IStockRepository stockRepository;

    @Override
    public Stock addProduct(Stock stock) {
        if (0 > stock.getQuantity())
            throw new RuntimeException("No se puede crear un producto con stock negativo");
        return stockRepository.save(stock);
    }

    @Override
    public float getTotalPrice(List<Stock> products) {
        Integer totalPrice = 0;
        for (Stock product : products) {
            Stock stockProduct = stockRepository.findById(product.getID()).get();
            totalPrice += stockProduct.getValue() * product.getQuantity();
        }
        return totalPrice;
    }

    @Override
    public String sellProducts(List<Stock> products) {
        Map<String, Integer> soldProducts = new HashMap<>();
        for (Stock product : products) {
            Stock stockProduct = stockRepository.findById(product.getID()).get();
            if (0 <= stockProduct.getQuantity() - product.getQuantity()) {
                stockProduct.setQuantity((stockProduct.getQuantity() - product.getQuantity()));
                soldProducts.put(stockProduct.getName(), product.getQuantity());
                stockRepository.save(stockProduct);
            } else {
                throw new RuntimeException(String.format("You can't buy %d of %s because it only has %d units available.", product.getQuantity(), stockProduct.getName(), stockProduct.getQuantity()));
            }
        }
        return String.format("You sold: %s. And the total price was %f", soldProducts, getTotalPrice(products));
    }

    @Override
    public Stock updateStock(Integer code, Integer stockToAdd) {
        Stock product = stockRepository.findById(code).get();
        if (0 < stockToAdd) {
            product.setQuantity(product.getQuantity() + stockToAdd);
        } else {
            throw new RuntimeException("Cant not add negative stock");
        }
        return stockRepository.save(product);
    }

    @Override
    public List<Stock> getProducts() {
        return stockRepository.findAll();
    }
}
