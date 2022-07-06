package com.manhpd.vendingmachine.application.dto;

import com.manhpd.vendingmachine.validation.OneOf;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CoinRequestDto {

    @NotNull
    @Positive
    @OneOf({ 5.0, 10.0, 25.0 })
    private Double insertedCoin;

}

