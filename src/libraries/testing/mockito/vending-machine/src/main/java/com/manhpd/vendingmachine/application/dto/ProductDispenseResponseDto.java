package com.manhpd.vendingmachine.application.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDispenseResponseDto {

    private String responseMessage;
    private String productName;
    private Double currentBalance;

}
