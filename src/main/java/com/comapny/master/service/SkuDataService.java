package com.comapny.master.service;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.comapny.master.entity.SkuData;
import com.comapny.master.repository.SkuDataRepository;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SkuDataService {

    @Autowired
    private SkuDataRepository skuDataRepository;

    public void saveExcelData(MultipartFile file) throws IOException {
        List<SkuData> skuDataList = new ArrayList<>();
        System.out.println("At service method.");
        try {
			try (XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream())) {
			    var sheet = workbook.getSheetAt(0);
			    System.out.println("sheet="+sheet);
			    for (int i = 1; i <= sheet.getLastRowNum(); i++) { // Skip header row
			        Row row = sheet.getRow(i);
			        System.out.println("row ="+row);
			        SkuData skuData = new SkuData();
			        System.out.println("0="+row.getCell(0).getCellType());
			        System.out.println("1="+row.getCell(1).getCellType());
			        System.out.println("2="+row.getCell(2).getCellType());
			        System.out.println("3="+row.getCell(3).getCellType());
			        System.out.println("4="+row.getCell(4).getCellType());
			        System.out.println("5="+row.getCell(5).getCellType());
			        System.out.println("6="+row.getCell(6).getCellType());
			        System.out.println("7="+row.getCell(7).getCellType());
			        System.out.println("8="+row.getCell(8).getCellType());
			        System.out.println("9="+row.getCell(9).getCellType());
			        System.out.println("10="+row.getCell(10).getCellType());
			        System.out.println("11="+row.getCell(11).getCellType());
			        System.out.println("12="+row.getCell(12).getCellType());
			        System.out.println("13="+row.getCell(13).getCellType());
			        System.out.println("14="+row.getCell(14).getCellType());

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
			        if (DateUtil.isCellDateFormatted(row.getCell(11))) {
                        // If the cell is a date, format it as a string
                        Date date = row.getCell(11).getDateCellValue();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy"); // Change format as needed
                        skuData.setStartDate(dateFormat.format(date));
                    }
			        if (DateUtil.isCellDateFormatted(row.getCell(12))) {
                        // If the cell is a date, format it as a string
                        Date date = row.getCell(12).getDateCellValue();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy"); // Change format as needed
                        skuData.setEndDate(dateFormat.format(date));
                    }
			        
			        skuData.setBomUsageFlag(row.getCell(13).getBooleanCellValue());
			        skuData.setSequenceNumber((int) row.getCell(14).getNumericCellValue());
			        
			        skuDataList.add(skuData);
			    }
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        skuDataRepository.saveAll(skuDataList);
    }

	public List<SkuData> readData() {
		return skuDataRepository.findAll();
	}
	
}