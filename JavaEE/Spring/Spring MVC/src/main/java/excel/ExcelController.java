package excel;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Andrii_Tkachuk
 * @since 5/20/2015
 */
@Controller
@RequestMapping("/rants.xls")
public class ExcelController {

    @RequestMapping(method = RequestMethod.GET)
    public String showExcelFile() {
        return "rants.xls";
    }
}
