package config;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller	
public class MainController {	
	
	@RequestMapping(value = {"/"})
	public ModelAndView main(ModelAndView mav) throws UnknownHostException {		
		
	    	mav.setViewName("hello");			
			String hostname = InetAddress.getLocalHost().getHostName();
			mav.addObject("hostname", hostname);		
	    	    
        return mav;
	}

}
