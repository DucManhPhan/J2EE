package com.manhpd.domain.repository;

import com.manhpd.domain.domain_object.UserCard;

import java.util.List;
import java.util.Optional;

public interface IUserCardRepository {

    Optional<UserCard> findByCardNumber(String cardNumber);

    List<UserCard> findByIdNumber(String idNumber);

}
