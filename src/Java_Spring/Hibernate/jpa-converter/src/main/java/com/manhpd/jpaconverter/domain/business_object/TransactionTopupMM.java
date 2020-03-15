package com.manhpd.jpaconverter.domain.business_object;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionTopupMM {

    private Long id;

    private String tranId;

    private String cttId;

    private String calling;

    private String isdn;

    // the amount of money to transfer
    private Long amount;

    private String serviceType;

    private String servicePay;

    private Integer status;

    private String errorCode;

    private String errorMessage;

    private Integer isRefund;

    private Integer retry;

    private String channel;

    private String description;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String isdnLogin;

    private String orderTime;

    private String serviceIndicator;

    private String content;

    private String contractId;

}
