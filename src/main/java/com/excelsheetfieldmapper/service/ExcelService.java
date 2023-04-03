package com.excelsheetfieldmapper.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ExcelService {

    List<String> findDuplicates(MultipartFile multipartFile) throws IOException;

}
