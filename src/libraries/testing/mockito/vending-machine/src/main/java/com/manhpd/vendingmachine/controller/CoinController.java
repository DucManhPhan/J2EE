package com.manhpd.vendingmachine.controller;

import com.manhpd.vendingmachine.application.dto.CoinRequestDto;
import com.manhpd.vendingmachine.application.service.CoinService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coins")
@AllArgsConstructor
public class CoinController {

    @Autowired
    private final CoinService coinService;

    @PostMapping
    public String sum(@RequestBody @Validated CoinRequestDto coinRequest) {
        return coinService.sum(coinRequest.getInsertedCoin());
    }

}
