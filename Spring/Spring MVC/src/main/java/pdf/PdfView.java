package pdf;

import com.lowagie.text.Document;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author Andrii_Tkachuk
 * @since 5/19/2015
 */
public class PdfView extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Table rantTable = new Table(4);
        rantTable.setWidth(90);
        rantTable.setBorderWidth(1);

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
