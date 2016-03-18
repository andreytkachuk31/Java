package freemarker;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;
import java.util.Date;

/**
 * @author Andrii_Tkachuk
 * @since 5/19/2015
 */
@Controller
@RequestMapping("/freemarker")
public class FreeMarkerController {

    @RequestMapping(method = RequestMethod.GET)
    public String showFreeMarkerPage(final Model model) {
        model.addAttribute("rants", Arrays.asList("1", "2", "3"));
        return "freemarker";
    }
}
