package webview;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import linebot.Application;
import linebot.Task;

@RestController
public class WebviewController {

	@GetMapping("/getTask")
	public List<Task> getTasks(@RequestParam("userId") String userId) {
		List<Task> tasks = Application.maps.get(userId);

		Collections.sort(tasks, new Comparator<Task>() {
			public int compare(Task o1, Task o2) {
				if (o1.getDuedate() == null || o2.getDuedate() == null)
					return 0;
				return o1.getDuedate().compareTo(o2.getDuedate());
			}
		});

		return tasks;
	}

}
