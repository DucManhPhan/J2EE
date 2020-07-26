package com.manhpd.persistence.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "user_card")
@Data
@NoArgsConstructor
public class UserCardEntity {

    public static final long serialVersionUID = 1L;

    @Id
    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "identification")
    private String identification;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "validity_date")
    private String validityDate;

    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    @OneToMany(mappedBy = "userCard", orphanRemoval = true)
    private List<TransactionEntity> vdsTransactions;

}

