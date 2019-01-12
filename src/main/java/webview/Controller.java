package webview;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import linebot.Application;
import linebot.Task;

@RestController
public class Controller {
	
	@GetMapping("/webview")
	public List<Task> getTasks() {
		return null;
	}
	
}
