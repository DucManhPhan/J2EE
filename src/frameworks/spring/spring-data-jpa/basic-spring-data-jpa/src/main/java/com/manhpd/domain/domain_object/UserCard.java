package com.manhpd.domain.domain_object;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCard {

    private String cardNumber;

    private String identification;

    private String phoneNumber;

    private String fullName;

    private String validityDate;

}
