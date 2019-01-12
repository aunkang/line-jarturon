package webview;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebviewController {

	@RequestMapping(name = "/webview", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView getTasks() {
		ModelAndView mav = new ModelAndView("viewtask.html");
		System.err.println("Hello, controller!");
		System.out.println("Hello, controller!");

		return mav;
	}

}
