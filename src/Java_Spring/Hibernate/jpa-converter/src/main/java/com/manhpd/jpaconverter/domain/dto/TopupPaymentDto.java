package com.manhpd.jpaconverter.domain.dto;


import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "command",
        "requestMti",
        "clientId",
        "msisdn",
        "customerCodeVt",
        "transAmount",
        "serviceIndicator",
        "requestId",
        "appId",
        "processCode",
        "PACKAGE_TELCO_CUST"
})
@XmlRootElement(name = "data")
public class TopupPaymentDto {

    private String command;

    private String requestMti;

    private String clientId;

    private String msisdn;

    private String customerCodeVt;

    private String transAmount;

    private String serviceIndicator;

    private String requestId;

    private String appId;

    private String processCode;

    private String PACKAGE_TELCO_CUST;

}
