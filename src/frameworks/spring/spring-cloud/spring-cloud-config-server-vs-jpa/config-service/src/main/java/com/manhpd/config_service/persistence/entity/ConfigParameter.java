package com.manhpd.config_service.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "config_parameter")
@Data
@NoArgsConstructor
public class ConfigParameter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column
    private String serviceName;

    @Column
    private String key;

    @Column
    private String value;

    @Column
    private String status;

    @Column
    private LocalDateTime createdAt;
}
