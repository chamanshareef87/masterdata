package com.comapny.master.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.comapny.master.entity.MasterFieldMetadata;

import java.util.List;

@Repository
public interface MasterFieldMetadataRepository extends JpaRepository<MasterFieldMetadata, Long> {
    List<MasterFieldMetadata> findByMasterId(Long masterId);
}