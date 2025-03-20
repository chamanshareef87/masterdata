package com.comapny.master.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
public class SkuData {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reportedSkuCode;
    private String targetSkuCode;
    private int quantity;
    private String partnerName;
    private String partnerCode;
    private String country;
    private String region;
    private String action;
    private String userComments;
    private String strategy;
    private boolean isBaseSku;
    private String startDate;
    private String endDate;
    private boolean bomUsageFlag;
    private int sequenceNumber;

    
}