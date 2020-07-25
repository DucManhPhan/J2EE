package com.manhpd.persistence.entity;

import com.manhpd.domain.dto.TransactionStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionEntity {

    public static final long serialVersionUID = 1L;

    @Id
    @Column(name = "transaction_id")
    private String transactionId;

    @Column(name = "withdraw_money")
    private BigDecimal withdrawMoney;

    @Column(name = "fee")
    private BigDecimal fee;

    @Enumerated
    @Column(name = "status", columnDefinition = "smallint")
    private TransactionStatus status = TransactionStatus.PENDING;

    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "card_number", referencedColumnName = "card_number")
    private UserCardEntity userCard;

}
