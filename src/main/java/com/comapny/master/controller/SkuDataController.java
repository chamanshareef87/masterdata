package com.comapny.master.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.comapny.master.entity.SkuData;
import com.comapny.master.service.SkuDataService;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@CrossOrigin
@RestController
@RequestMapping("/api/sku")
public class SkuDataController {

	
    @Autowired
    private SkuDataService skuDataService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
        	System.out.println("file==================="+file);
            skuDataService.saveExcelData(file);
            return new ResponseEntity<>("File uploaded and data saved successfully!", HttpStatus.OK);
        } catch (Exception e) {
        	System.out.println("Exception in file upload controller");
        	e.printStackTrace();
            return new ResponseEntity<>("Failed to upload file: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping
    public ResponseEntity<List<SkuData>> readData() {
    	List<SkuData> skumaster = new ArrayList<SkuData>();
    	try {
            skumaster =  skuDataService.readData();
            return new ResponseEntity<>(skumaster, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(skumaster, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return new ResponseEntity<>("Test endpoint is working!", HttpStatus.OK);
    }

}