# excelsheetfieldmapper
This is a separate microservice to map the fields from multiple sheets and find out the unique and common values present betweem them 

Steps while working with an Excel Sheet in java: 
1. Create an InputStream from the uploaded file i.e.,multipartFile.getInputStream()
2. Create a workbook from this Multipart file inputstream to process the Excel Sheet : i.e.,WorkbookFactory.create(inputStream)
3. Iterate over the workbook in case there are multiple sheets present or if you have no idea about the number of sheets i.e., workbook.forEach()
4. Then Iterate over the sheet to access the Rows in the sheet : i.e., sheets.forEach()
5. Iterate over these rows to fetch the cells : rows.forEach()
6. row.getCell(0, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL); and process these values
