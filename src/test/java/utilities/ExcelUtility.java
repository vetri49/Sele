package utilities;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class ExcelUtility {

    private static final String path = "src/test/resources/TestData.xlsx";
    private static final String Sheet = "TestData";

    public static Map<String, String[]> readTestData() {
        Map<String, String[]> data = new TreeMap<>();

        try (FileInputStream file = new FileInputStream(path)) {
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheet(Sheet);

            int rowCount = sheet.getPhysicalNumberOfRows();
            for (int i = 0; i < rowCount; i++) {
                XSSFRow row = sheet.getRow(i);
                int colCount = row.getPhysicalNumberOfCells();
                String[] rowData = new String[colCount];
                for (int j = 0; j < colCount; j++) {
                    Cell cell = row.getCell(j);
                    rowData[j] = cell.toString();
                }
                data.put(String.valueOf(i + 1), rowData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    
    public static void writeTestData(Map<String, String[]> data) {
        try (FileOutputStream writer = new FileOutputStream(path)) {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet(Sheet);

            Set<String> keyset = data.keySet();
            int rownum = 0;

            for (String key : keyset) {
                Row row = sheet.createRow(rownum++);
                Object[] objArr = data.get(key);

                int cellnum = 0;

                for (Object obj : objArr) {
                    Cell cell = row.createCell(cellnum++);

                    if (obj instanceof String)
                        cell.setCellValue((String) obj);
                    else if (obj instanceof Integer)
                        cell.setCellValue((Integer) obj);
                }
            }

            workbook.write(writer);
            System.out.println("TestData written successfully on disk.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
    	
    	Map<String, String[]> data = new TreeMap<String,String[]>(); 
        data.put("1", 
                 new String[]{ "UserName", "Password", "Status" }); 
        data.put("2", 
        		new String[]{ "Ronan", "Eqmd20441", "fail" }); 
        data.put("3", 
        		new String[]{ "Rathoor", "vnrojfi(@u2", "pass" }); 
        data.put("4",  new String[]{"Sharukh", "Njnfuneu9!u","pass" }); 
        data.put("5", new String[] {"Virat", "NSukohl2s2i","pass" }); 
    	writeTestData(data);
       
        Map<String, String[]> testData = readTestData();
        System.out.println("Test Data from Excel:");
        for (Entry<String, String[]> entry : testData.entrySet()) {
            String key = entry.getKey();
            Object[] values = entry.getValue();
            System.out.print("Row " + key + ": ");
            for (Object value : values) {
                System.out.print(value + "\t");
            }
            System.out.println();
        }
        

  
        
    }
}
