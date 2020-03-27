package com.manhpd.jpaconverter.persistence.entity;


import com.manhpd.jpaconverter.shared.db.converter.LocalDateTimeAttributeConverter;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "vt_topupmm_transaction")
public class TransactionTopupMMEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tran_id")
    private String tranId;

    @Column(name = "ctt_id")
    private String cttId;

    private String calling;

    // phone number
    private String isdn;

    // the amount of money to transfer
    private Long amount;

    @Column(name = "service_type")
    private String serviceType;

    @Column(name = "service_pay")
    private String servicePay;

    private Integer status;

    @Column(name = "error_code")
    private String errorCode;

    @Column(name = "error_message")
    private String errorMessage;

    @Column(name = "is_refund")
    private Integer isRefund;

    private Integer retry;

    private String channel;

    private String description;

    @Convert(converter = LocalDateTimeAttributeConverter.class)
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Convert(converter = LocalDateTimeAttributeConverter.class)
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "isdn_login")
    private String isdnLogin;

    @Column(name = "order_time")
    private String orderTime;

    @Column(name = "service_indicator")
    private String serviceIndicator;

    private String content;

    @Column(name = "contract_id")
    private String contractId;

}
