package com.excelsheetfieldmapper.service.impl;

import com.excelsheetfieldmapper.exception.EmptyFileException;
import com.excelsheetfieldmapper.exception.ExcelFileProcessingException;
import com.excelsheetfieldmapper.service.ExcelService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class ExcelServiceImpl implements ExcelService {

    @Override
    public ResponseEntity<List<String>> findDuplicates(MultipartFile multipartFile) {
        if (multipartFile.isEmpty())
            throw new EmptyFileException("Empty File..Please upload a correct file");
        List<String> duplicates = new ArrayList<>();
        Set<String> uniqueValues = new HashSet<>();
        try (Workbook workbook = WorkbookFactory.create(multipartFile.getInputStream())) {
            workbook.forEach(sheet ->
                    sheet.forEach(row -> {
                        Cell cell = row.getCell(0, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                        if (cell != null && cell.getCellType() == CellType.STRING) {
                            String value = cell.getStringCellValue();
                            if (!uniqueValues.add(value)) {
                                duplicates.add(value);
                                log.debug("DUPLICATES: {}", duplicates);
                            }
                        }
                    })
            );
        } catch (EncryptedDocumentException | IOException e) {
            log.error("ERROR while processing workbook", e);
            throw new ExcelFileProcessingException("ERROR while processing workbook");
        }
        return new ResponseEntity<>(duplicates, HttpStatus.OK);
    }
}
