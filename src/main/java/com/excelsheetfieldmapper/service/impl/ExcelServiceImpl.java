package com.excelsheetfieldmapper.service.impl;

import com.excelsheetfieldmapper.service.ExcelService;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ExcelServiceImpl implements ExcelService {
    @Override
    public List<String> processDuplicates(File tempFile) throws IOException {
        List<String> duplicates = new ArrayList<>();

        Workbook workbook = WorkbookFactory.create(tempFile);
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            Sheet sheet = workbook.getSheetAt(i);
            Set<String> uniqueValues = new HashSet<>();
            for (Row row : sheet) {
                Cell cell = row.getCell(0);
                if (cell != null) {
                    String value = cell.getStringCellValue();
                    System.out.println(value);
                    if (!uniqueValues.add(value)) {
                        duplicates.add(value);
                        System.out.println("DUPLICATES:" + duplicates);
                    }
                }
            }
        }
        return duplicates;
    }
}
