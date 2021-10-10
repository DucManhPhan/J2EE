package com.manhpd.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BankInfo {

    private String tradingName;

    private String fullName;

    private String dataType;

    private String bin;

    private String bankRequired;

}
