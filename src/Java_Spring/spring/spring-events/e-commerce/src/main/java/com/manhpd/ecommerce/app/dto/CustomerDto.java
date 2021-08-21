package com.manhpd.ecommerce.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private String email;
    private boolean newsletter;
    private BigDecimal rewardPoints;
}
