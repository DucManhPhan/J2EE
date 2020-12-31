package com.manhpd;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GiftRewardLoyaltyProgram {

    private Long productId;

    private Integer neededPoints;

    @Override
    public String toString() {
        return "GiftRewardLoyaltyProgram{" +
                "productId=" + productId +
                ", neededPoints=" + neededPoints +
                '}';
    }
}
