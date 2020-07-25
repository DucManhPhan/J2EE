package com.manhpd.domain.domain_object;

import com.manhpd.domain.dto.TransactionStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {

    private String transactionId;

    private BigDecimal withdrawMoney;

    private BigDecimal fee;

    private TransactionStatus status;

    private LocalDateTime createdDate = LocalDateTime.now();

    private LocalDateTime lastUpdate;

}
