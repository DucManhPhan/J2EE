package com.manhpd.persistence.mapper;

import com.manhpd.domain.domain_object.UserCard;
import com.manhpd.persistence.entity.UserCardEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserCardMapper {

    @Mapping(target = "validityDate", source = "validityDate")
//    @Mapping(target = "createdDate", ignore = true)
//    @Mapping(target = "lastUpdate", ignore = true)
    UserCard toUserCard(UserCardEntity entity);

}
