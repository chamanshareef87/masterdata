package com.comapny.master.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.comapny.master.entity.MasterMetadata;
import com.comapny.master.repository.MasterMetadataRepository;
import com.comapny.master.service.MasterDataService;

@RestController
@RequestMapping("/api/masterdata")
public class MasterDataController {

    @Autowired
    private MasterDataService masterDataService;
    @Autowired
    private MasterMetadataRepository masterMetadataRepository;

    @PostMapping("/create-table")
    public String createTable(@RequestParam String masterName ) {
        return masterDataService.createTable(masterName);
    }
    
    @PostMapping
    public MasterMetadata createMasterMetadata(@RequestBody MasterMetadata masterMetadata) {
        return masterMetadataRepository.save(masterMetadata);
    }

    @GetMapping
    public List<MasterMetadata> getAllMasterMetadata() {
        return masterMetadataRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MasterMetadata> getMasterMetadataById(@PathVariable Long id) {
        return masterMetadataRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MasterMetadata> updateMasterMetadata(@PathVariable Long id, @RequestBody MasterMetadata masterMetadata) {
        return masterMetadataRepository.findById(id)
                .map(existingMetadata -> {
                    existingMetadata.setMasterName(masterMetadata.getMasterName());
//                    existingMetadata.setDescription(masterMetadata.getDescription());
                    MasterMetadata updatedMetadata = masterMetadataRepository.save(existingMetadata);
                    return ResponseEntity.ok(updatedMetadata);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMasterMetadata(@PathVariable Long id) {
        return masterMetadataRepository.findById(id)
                .map(existingMetadata -> {
                    masterMetadataRepository.delete(existingMetadata);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
}