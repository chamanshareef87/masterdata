package com.comapny.master.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "MASTER_FIELD_METADATA")
public class MasterFieldMetadata {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long masterId;
    private String fieldName;
    private String fieldColumnName;
    private String dataType;
    private Integer maxLength;
    private Boolean isNotNull;
    private Boolean isUnique;

    // Getters and Setters
}