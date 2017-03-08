package application;

import java.net.InetAddress;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @RequestMapping(value = {"/"})
    public ModelAndView main(ModelAndView mav) throws Exception {

        mav.setViewName("index");
        String hostname = InetAddress.getLocalHost().getHostName();
        mav.addObject("hostname", hostname);
        return mav;
        
    }

}
