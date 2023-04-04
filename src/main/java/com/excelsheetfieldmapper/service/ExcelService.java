package com.excelsheetfieldmapper.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ExcelService {

    ResponseEntity<List<String>> findDuplicates(MultipartFile multipartFile);

}
