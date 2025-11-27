package com.utility;

import com.entity.TransformerReadingDetails;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {
    public List<TransformerReadingDetails> readDataFromExcel() {
        String filePath = "resources/report.xlsx";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy'T'HH:mm:ss");
        List<TransformerReadingDetails> details = new ArrayList<>();
        try (InputStream fis =  getClass().getClassLoader().getResourceAsStream("report.xlsx");
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            int rowNumber =0;
            for (Row row : sheet) {

                if(rowNumber == 0) {
                    rowNumber++;
                } else {
                    if(rowNumber%30 == 0 ){
                        int i = 0;
                        TransformerReadingDetails detail = new TransformerReadingDetails();
                        String onlyDate = "";
                        for (Cell cell : row) {
                            switch (i) {
                                case 0:
                                    onlyDate = cell.getStringCellValue();
                                    break;
                                case 1:
                                    detail.setDate(LocalDateTime.parse(onlyDate+"T"+cell.getStringCellValue(),formatter));
                                    break;
                                case 2:
                                    detail.setName(cell.getStringCellValue());
                                    break;
                                case 3:
                                    detail.setTopOilTemperature(cell.getNumericCellValue());
                                    break;
                                case 5:
                                    detail.setWindingTemperature(cell.getNumericCellValue());
                                    break;
                                case 8:
                                    detail.setHumidity(cell.getNumericCellValue());
                                    break;
                                case 16:
                                    detail.setL1(Double.parseDouble(cell.getStringCellValue()));
                                    break;
                                case 17:
                                    detail.setL2(Double.parseDouble(cell.getStringCellValue()));
                                    break;
                                case 18:
                                    detail.setL3(Double.parseDouble(cell.getStringCellValue()));
                                    break;
                                default:
                                    break;
                            }
                            i++;
                        }
                        details.add(detail);
                    }
                    rowNumber++;
                }
            }

        } catch (IOException e) {e.printStackTrace();}
        return details;
    }
}