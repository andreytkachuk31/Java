package pdf;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Andrii_Tkachuk
 * @since 5/19/2015
 */
@Controller
@RequestMapping("/rants.pdf")
public class PdfController {

    @RequestMapping(method = RequestMethod.GET)
    public String showPdfFile() {
        return "rants.pdf";
    }
}
