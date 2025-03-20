package com.comapny.master.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.comapny.master.entity.MasterMetadata;

@Repository
public interface MasterMetadataRepository extends JpaRepository<MasterMetadata, Long> {
    MasterMetadata findByMasterName(String masterName);
}

