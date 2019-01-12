package webview;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import linebot.Application;
import linebot.Task;

@RestController
public class WebviewController {

	@RequestMapping(name = "/getTask", method = { RequestMethod.GET })
	public List<Task> getTasks(String userId) {
		return Application.maps.get(userId);
	}

}
