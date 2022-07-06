package com.manhpd.vendingmachine.application.service.impl;

import com.manhpd.vendingmachine.application.service.CoinService;
import com.manhpd.vendingmachine.domain.CoinAmount;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@AllArgsConstructor
@Slf4j
@Service
public class CoinServiceImpl implements CoinService {

    @Autowired
    private final CoinAmount coinAmount;

    @Override
    public String sum(Double add) {
        log.info("Coming coin will be added your total amount: {}", add);

        double result = add / 100.0;
        if (Objects.nonNull(this.coinAmount.getBalance())) {
            result = result + this.coinAmount.getBalance();
        }

        this.coinAmount.setBalance(result);
        return "Your current balance is $" + result;
    }
}
