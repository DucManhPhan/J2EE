package com.manhpd.repository;

import com.manhpd.dto.EmployeeDto;
import com.manhpd.dto.UpdateEmployeeData;
import com.manhpd.entity.EmployeeEntity;
import com.manhpd.repository.EmployeeRepository;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<EmployeeDto> findAllEmployees() {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(EmployeeEntity.class);
        List<EmployeeEntity> entities = criteria.list();

        return this.toEmployeeDtos(entities);
    }

    @Override
    public void updateEmployeeWithId(int id, UpdateEmployeeData data) {
        Objects.nonNull(data);

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(EmployeeEntity.class);
        criteria.add(Restrictions.eq("id", id));

        EmployeeEntity entity = (EmployeeEntity) criteria.uniqueResult();
        this.updateEntity(entity, data);
    }

    private EmployeeDto toEmployeeDto(EmployeeEntity entity) {
        EmployeeDto dto = new EmployeeDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setBirthday(entity.getBirthday());

        return dto;
    }

    private List<EmployeeDto> toEmployeeDtos(List<EmployeeEntity> entities) {
        if (Objects.isNull(entities) || entities.isEmpty()) {
            return new ArrayList<>();
        }

        List<EmployeeDto> dtos = new ArrayList<>();
        entities.stream().forEach(entity -> {
            dtos.add(this.toEmployeeDto(entity));
        });

        return dtos;
    }

    private void updateEntity(EmployeeEntity entity, UpdateEmployeeData data) {
        entity.setName(data.getName());
        entity.setBirthday(data.getBirthDay());
    }
}
