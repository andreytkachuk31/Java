package pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author Andrii_Tkachuk
 * @since 5/19/2015
 */
public class PdfView extends AbstractITextPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PdfPTable rantTable = new PdfPTable(4);
        rantTable.setTotalWidth(90);

        rantTable.addCell("State");
        rantTable.addCell("Plate");
        rantTable.addCell("Date Posted");
        rantTable.addCell("Text");

        rantTable.addCell("Rant State");
        rantTable.addCell("Rant Plate");
        rantTable.addCell("Rant Date Posted");
        rantTable.addCell("Rant Text");

        document.add(rantTable);
    }
}
