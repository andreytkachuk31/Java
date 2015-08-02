package velocity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;
import java.util.Date;

/**
 * @author Andrii_Tkachuk
 * @since 5/12/2015
 */
@Controller
@RequestMapping("/velocity")
public class VelocityController {

    @RequestMapping(method = RequestMethod.GET)
    public String showVelocityPage(final Model model) {
        model.addAttribute("rants", Arrays.asList("1", "2", "3"));
        return "velocity";
    }
}
