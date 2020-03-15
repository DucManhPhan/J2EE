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
public class TransactionViettelpay {

    private Long id;

    /**
     * The phone number that pay
     */
    private String calling;

    /**
     * The phone number that is paid
     */
    private String isdn;

    // id of transaction
    private String tranId;

    private Long amount;

    private String version;

    /**
     * The content of transaction
     */
    private String description;

    /**
     * The service package
     * mps: vas package
     * vtfree: vtfree package
     * topup, omi: omi business
     * data: data package
     * mnp: cmgs phone number
     */
    private String servicePay;

    /**
     * The name of package when using vas, data, vtfree
     */
    private String cttPackage;

    /**
     * The period code for vas package
     */
    private String commandCode;

    /**
     * Under json format, it contains the information of phone number
     */
    private String content;

    /**
     * The package code with omi business
     */
    private String omniOrderCode;

    /**
     * The error code when creating order
     */
    private String omniErrorCode;

    private String omniOrderMessage;

    /**
     * The id of CTT that returns
     */
    private String cttId;

    /**
     * The state of a transaction
     * -1: pending transaction
     * 0: fail transaction
     * 1: successful transaction
     * 2: successful refund
     * 3: fail refund
     */
    private Integer status;

    private LocalDateTime cttPayUpdateTime;

    /**
     * APP, WEB, WAP
     */
    private String source;

    /**
     * The transaction type
     * 1: prepaid
     * 2: postpaid
     * 21: register vas
     * 22: register data
     * 23: register vtfree
     * 24: topup
     */
    private Integer orderType;

    /**
     * The package that register
     */
    private String cttPackageName;

    private String simNumber;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String mpsService;

    private String refundErrorCode;

    private String serviceIndicator;

    private Long basePrice;

    /**
     * The percent number that we can be discounted
     */
    private Float discount;

    /**
     * mnp: The contract number when pay
     */
    private String contractNo;

    /**
     * mnp: the paid channel
     */
    private String channel;

    /**
     * The number of retry
     */
    private Integer retry;

    /**
     * Is it display transaction history?
     */
    private Boolean isDisplay;

    @Override
    public String toString() {
        return "Info of TransactionViettelpay object: "
             + "{"
             + "isdn: " + this.isdn
             + ", tranId: " + this.tranId
             + ", cttId: " + this.cttId
             + ", status " + this.status
             + ", content " + this.content
             + ", transAmount: " + this.amount
             + ", servicePay: " + this.servicePay
             + ", omniErrorCode: " + this.omniErrorCode
             + ", omniOrderMessage: " + this.omniOrderMessage
             + ", serviceIndicator: " + this.serviceIndicator
             + ", retry: " + this.retry
             + ", createdAt: " + this.createdAt
             + ", updatedAt: " + this.updatedAt
             + "}";
    }

}
