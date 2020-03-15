package com.manhpd.jpaconverter.persistence.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "vt_ctt_transaction")
public class TransactionViettelpayEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The phone number that pay
     */
    private String calling;

    /**
     * The phone number that is paid
     */
    private String isdn;

    @Column(name = "tran_id")
    private String tranId;

    private Long amount;

    /**
     * The version of CTT
     */
    private String version;

    /**
     * The content of transaction
     */
    private String description;

    @Column(name = "service_pay")
    private String servicePay;

    @Column(name = "ctt_package")
    private String cttPackage;

    @Column(name = "command_code")
    private String commandCode;

    private String content;

    @Column(name = "omni_order_code")
    private String omniOrderCode;

    @Column(name = "omni_error_code")
    private String omniErrorCode;

    @Column(name = "omni_order_message")
    private String omniOrderMessage;

    @Column(name = "ctt_id")
    private String cttId;

    private Integer status;

    @Column(name = "ctt_pay_update_time", columnDefinition="DATETIME")
    private LocalDateTime cttPayUpdateTime;

    private String source;

    @Column(name = "order_type")
    private Integer orderType;

    @Column(name = "ctt_package_name")
    private String cttPackageName;

    @Column(name = "sim_number")
    private String simNumber;

    @Column(name = "created_at", columnDefinition="DATETIME")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition="DATETIME")
    private LocalDateTime updatedAt;

    @Column(name = "mps_service")
    private String mpsService;

    @Column(name = "refund_error_code")
    private String refundErrorCode;

    @Column(name = "service_indicator")
    private String serviceIndicator;

    @Column(name = "base_price")
    private Long basePrice;

    /**
     * The percent number that we can be discounted
     */
    private Float discount;

    @Column(name = "contract_no")
    private String contractNo;

    private String channel;

    private Integer retry;

    @Column(name = "is_display")
    private Boolean isDisplay;

}
