package com.manhpd.vendingmachine.controller;

import com.manhpd.vendingmachine.application.dto.ProductDispenseResponseDto;
import com.manhpd.vendingmachine.application.dto.ProductRequestDto;
import com.manhpd.vendingmachine.application.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private final ProductService productService;

    @PostMapping
    public ProductDispenseResponseDto dispense(@Valid @RequestBody ProductRequestDto productRequestDto) {
        return this.productService.dispense(productRequestDto);
    }

}
