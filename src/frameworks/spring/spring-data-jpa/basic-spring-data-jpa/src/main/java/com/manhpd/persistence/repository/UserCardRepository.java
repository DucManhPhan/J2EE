package com.manhpd.persistence.repository;

import com.manhpd.domain.domain_object.UserCard;
import com.manhpd.domain.repository.IUserCardRepository;

import java.util.List;
import java.util.Optional;

public class UserCardRepository implements IUserCardRepository {

    @Override
    public Optional<UserCard> findByCardNumber(String cardNumber) {
        return Optional.empty();
    }

    @Override
    public List<UserCard> findByIdNumber(String idNumber) {
        return null;
    }
}
