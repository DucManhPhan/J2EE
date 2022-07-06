package com.manhpd.vendingmachine.application.service.impl;

import com.manhpd.vendingmachine.application.dto.ProductDispenseResponseDto;
import com.manhpd.vendingmachine.application.dto.ProductRequestDto;
import com.manhpd.vendingmachine.application.service.ProductService;
import com.manhpd.vendingmachine.domain.CoinAmount;
import com.manhpd.vendingmachine.persistence.entity.Product;
import com.manhpd.vendingmachine.persistence.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CoinAmount coinAmount;

    @Override
    public ProductDispenseResponseDto dispense(ProductRequestDto dto) {
        Optional<Product> optProduct = this.productRepository.findById(dto.getId());
        Product product = optProduct.orElseThrow(RuntimeException::new);

        if (product.getStock() < 1) {
            return ProductDispenseResponseDto.builder()
                    .responseMessage("SOLD OUT")
                    .currentBalance(this.coinAmount.getBalance())
                    .build();
        }

        if (this.coinAmount.getBalance() < product.getPrice()) {
            return ProductDispenseResponseDto.builder()
                    .responseMessage("Unfortunately your balance is not sufficient")
                    .currentBalance(this.coinAmount.getBalance())
                    .build();
        }

        this.updateStock(product);
        this.coinAmount.setBalance(this.coinAmount.getBalance() - product.getPrice());

        return ProductDispenseResponseDto.builder()
                .productName(product.getName())
                .responseMessage("THANK YOU")
                .currentBalance(this.coinAmount.getBalance())
                .build();
    }

    private void updateStock(Product product) {
        product.setStock(product.getStock() - 1);
        this.productRepository.save(product);
    }

}
