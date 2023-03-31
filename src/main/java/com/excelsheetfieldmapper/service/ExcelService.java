package com.excelsheetfieldmapper.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface ExcelService {
    List<String>  findDuplicates(File tempFile) throws IOException;
}
