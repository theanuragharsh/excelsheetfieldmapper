package com.excelsheetfieldmapper.service.impl;

import com.excelsheetfieldmapper.service.ExcelService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class ExcelServiceImpl implements ExcelService {
    @Override
    public List<String> findDuplicates(File tempFile) throws IOException {
        List<String> duplicates = new ArrayList<>();
        Set<String> uniqueValues = new HashSet<>();
        try (Workbook workbook = WorkbookFactory.create(tempFile)) {
//           Workbook workbook = WorkbookFactory.create(tempFile);
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                Sheet sheet = workbook.getSheetAt(i);
//            Set<String> uniqueValues = new HashSet<>();
                for (Row row : sheet) {
                    Cell cell = row.getCell(0);
                    if (cell != null) {
                        String value = cell.getStringCellValue();
                        log.debug(value);
                        if (!uniqueValues.add(value)) {
                            duplicates.add(value);
                            log.debug("DUPLICATES: {}", duplicates);
                        }
                    }
                }
            }
        } catch (IOException | EncryptedDocumentException e) {
            e.printStackTrace();
            log.error("ERROR while processing workbook !");
        }
        return duplicates;
    }
}
