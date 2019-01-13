package linebot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppRest {

	@PostMapping("/markImportant/{userId}")
	public void markImportant(@PathVariable String userId, @RequestBody List<Task> targetTasks) {
		List<Task> tasks = Application.maps.get(userId);
		if (tasks != null && tasks.size() > 0) {
			for (int i = 0; i<= tasks.size()-1; i++) {
				for (Task targetTask : targetTasks) {
					if (tasks.get(i).getTaskId().equals(targetTask.getTaskId())) {
						Application.maps.get(userId).get(i).setImportantFlag(targetTask.isImportantFlag());
					}
				}
			}
		}
	}
	
	@DeleteMapping("/removeTask/{userId}")
	public void deleteTask(@PathVariable String userId, @RequestBody List<Task> targetTasks) {
		List<Task> tasks = Application.maps.get(userId);
		
		if (tasks != null && tasks.size() > 0) {
			List<Task> deleteTask = new ArrayList<Task>();
			
			for (int i = 0; i<= tasks.size()-1; i++) {
				for (Task targetTask : targetTasks) {
					if (tasks.get(i).getTaskId().equals(targetTask.getTaskId())) {
						deleteTask.add(tasks.get(i));
						break;
					}
				}
			}
			
			Application.maps.get(userId).removeAll(deleteTask);
		}
	}
	
	@GetMapping("/getTask")
	public ResponseEntity<?> getTasks(@RequestParam("userId") String userId) {
		List<Task> tasks = Application.maps.get(userId);
		tasks = this.sortTask(tasks);
		return new ResponseEntity<>(tasks, HttpStatus.OK);
	}
	
	public List<Task> sortTask(List<Task> tasks) {
		List<Task> paramsTasks = new ArrayList<Task>(tasks);
		
		List<Task> comTasks = new ArrayList<Task>();
		List<Task> favTasks = new ArrayList<Task>();
		List<Task> normalTasks = new ArrayList<Task>();
		
		for (Task task : paramsTasks) {
			if (task.isImportantFlag()) {
				favTasks.add(task);
			} else {
				normalTasks.add(task);
			}
		}
		
		Collections.sort(favTasks);
		Collections.sort(normalTasks);
		
		comTasks.addAll(favTasks);
		comTasks.addAll(normalTasks);

		return comTasks;
	}

}
