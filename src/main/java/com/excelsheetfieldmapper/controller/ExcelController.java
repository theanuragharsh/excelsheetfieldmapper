package com.excelsheetfieldmapper.controller;

import com.excelsheetfieldmapper.service.ExcelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ExcelController {

    private final ExcelService excelService;

    @PostMapping("/excel/duplicate/fields")
    public List<String> processExcel(@RequestParam("file") MultipartFile file) throws IOException {
        File tempFile = File.createTempFile("Test", ".xlsx");
        file.transferTo(tempFile);
        List<String> duplicates = excelService.findDuplicates(tempFile);
        if (tempFile.delete())
            System.out.println("Temp file deleted");
        return duplicates;
    }
}
