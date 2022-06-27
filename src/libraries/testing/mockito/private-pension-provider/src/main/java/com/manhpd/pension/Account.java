package com.manhpd.pension;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.Set;

@Data
public class Account {

    private String id;
    private String fistName;
    private String lastName;
    private LocalDate dob;
    private String taxId;
    private long totalInvestmentValue;
    private Currency ccy;
    private Set<String> investments;
    private long availableCash;
    private LocalDateTime expectedRetirement;

}
