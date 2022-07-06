package com.manhpd.vendingmachine.application.service;

import com.manhpd.vendingmachine.application.dto.ProductDispenseResponseDto;
import com.manhpd.vendingmachine.application.dto.ProductRequestDto;

public interface ProductService {
    ProductDispenseResponseDto dispense(ProductRequestDto dto);
}
