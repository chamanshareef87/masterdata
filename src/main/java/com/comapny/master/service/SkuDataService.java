package com.comapny.master.service;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.comapny.master.entity.SkuData;
import com.comapny.master.repository.SkuDataRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SkuDataService {

    @Autowired
    private SkuDataRepository skuDataRepository;

    public void saveExcelData(MultipartFile file) throws IOException {
        List<SkuData> skuDataList = new ArrayList<>();
        try (XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream())) {
            var sheet = workbook.getSheetAt(0);
            for (int i = 1; i <= sheet.getLastRowNum(); i++) { // Skip header row
                Row row = sheet.getRow(i);
                SkuData skuData = new SkuData();
                skuData.setReportedSkuCode(row.getCell(0).getStringCellValue());
                skuData.setTargetSkuCode(row.getCell(1).getStringCellValue());
                skuData.setQuantity((int) row.getCell(2).getNumericCellValue());
                skuData.setPartnerName(row.getCell(3).getStringCellValue());
                skuData.setPartnerCode(row.getCell(4).getStringCellValue());
                skuData.setCountry(row.getCell(5).getStringCellValue());
                skuData.setRegion(row.getCell(6).getStringCellValue());
                skuData.setAction(row.getCell(7).getStringCellValue());
                skuData.setUserComments(row.getCell(8).getStringCellValue());
                skuData.setStrategy(row.getCell(9).getStringCellValue());
                skuData.setBaseSku(row.getCell(10).getBooleanCellValue());
                skuData.setStartDate(row.getCell(11).getStringCellValue());
                skuData.setEndDate(row.getCell(12).getStringCellValue());
                skuData.setBomUsageFlag(row.getCell(13).getBooleanCellValue());
                skuData.setSequenceNumber((int) row.getCell(14).getNumericCellValue());
                skuDataList.add(skuData);
            }
        }
        skuDataRepository.saveAll(skuDataList);
    }

	public List<SkuData> readData() {
		return skuDataRepository.findAll();
	}
	
}