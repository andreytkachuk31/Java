package excel;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author Andrii_Tkachuk
 * @since 5/19/2015
 */
public class ExcelView extends AbstractExcelView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HSSFSheet sheet = createSheet(workbook);
        int rowIndex = addRow(sheet, 1);
    }

    private HSSFSheet createSheet(HSSFWorkbook workbook) {
        HSSFSheet sheet = workbook.createSheet("Rants");
        HSSFRow header = sheet.createRow(0);
        header.createCell(0).setCellValue("Date");
        header.createCell(1).setCellValue("Text");
        return sheet;
    }

    private int addRow(HSSFSheet sheet, int rowNum) {
        HSSFRow row = sheet.createRow(rowNum++);
        row.createCell(0).setCellValue("Rant date");
        row.createCell(1).setCellValue("Rant text");
        return rowNum;
    }
}