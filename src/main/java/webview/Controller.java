package webview;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class Controller {
	
	@GetMapping("/webview")
	public ModelAndView getTasks() {
		ModelAndView mav = new ModelAndView("viewtask.html");
		System.err.println("Hello, controller!");
		System.out.println("Hello, controller!");
		
		return mav;
	}
	
}
