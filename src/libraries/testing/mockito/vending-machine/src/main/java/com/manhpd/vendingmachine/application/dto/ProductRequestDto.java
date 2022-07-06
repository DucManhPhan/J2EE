package com.manhpd.vendingmachine.application.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDto {

    @NotNull
    @Positive
    private Integer id;

}
