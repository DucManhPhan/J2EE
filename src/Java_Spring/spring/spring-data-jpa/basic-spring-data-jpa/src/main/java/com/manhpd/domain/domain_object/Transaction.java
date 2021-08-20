package com.manhpd.domain.domain_object;

import com.manhpd.domain.dto.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    private String transactionId;

    private BigDecimal withdrawMoney;

    private BigDecimal fee;

    private String cardNumber;

    private String identification;

    private String phoneNumber;

    private String fullName;

    private String validityDate;

    private TransactionStatus status;

    private LocalDateTime createdDate = LocalDateTime.now();

    private LocalDateTime lastUpdate;

}
