package com.manhpd.persistence.mapper;

import com.manhpd.domain.domain_object.Transaction;
import com.manhpd.persistence.entity.TransactionEntity;
import com.manhpd.persistence.entity.UserCardEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    @Mapping(target = "createdDate", source = "transactionEntity.createdDate")
    @Mapping(target = "lastUpdate", source = "transactionEntity.lastUpdate")
    Transaction toTransaction(TransactionEntity transactionEntity, UserCardEntity userCardEntity);

    default Transaction toTransaction(TransactionEntity transactionEntity) {
        return transactionEntity == null ? null
                              : toTransaction(transactionEntity, transactionEntity.getUserCard());
    }

}
