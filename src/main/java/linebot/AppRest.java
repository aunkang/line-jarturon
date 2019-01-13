package linebot;

import java.util.ArrayList;
import java.util.Collections;
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
						tasks.get(i).setImportantFlag(targetTask.isImportantFlag());
						break;
					}
				}
			}
		}
	}
	
	@DeleteMapping("/removeTask/{userId}")
	public void deleteTask(@PathVariable String userId, @RequestBody List<Task> targetTasks) {
		List<Task> tasks = Application.maps.get(userId);
		
		if (tasks != null && tasks.size() > 0) {
			List<Integer> deleteIndex = new ArrayList<>();
			for (int i = 0; i<= tasks.size()-1; i++) {
				for (Task targetTask : targetTasks) {
					if (tasks.get(i).getTaskId().equals(targetTask.getTaskId())) {
						deleteIndex.add(i);
						break;
					}
				}
			}
			for (int index : deleteIndex) {
				tasks.remove(index);
			}
		}
	}
	
	@GetMapping("/getTask")
	public ResponseEntity<?> getTasks(@RequestParam("userId") String userId) {
		List<Task> tasks = Application.maps.get(userId);
		tasks = this.sortTask(tasks);
//		for (int round = 0; round <= tasks.size()-2; round++) {
//			for (int index = round+1; index <= tasks.size()-1; index++ ) {
//				if (tasks.get(round).getImportantFlag()) {
//					if (tasks.get(index).getImportantFlag()) {
//						if (tasks.get(round).getDuedate().compareTo(tasks.get(index).getDuedate()) > 0) {
//							temp = tasks.get(round);
//							tasks.remove(round);
//							tasks.add(round, tasks.get(index));
//							tasks.remove(index);
//							tasks.add(index, temp);
//						}
//					} 
//				} 
//				else {
//					if (tasks.get(index).getImportantFlag()) {
//						temp = tasks.get(round);
//						tasks.remove(round);
//						tasks.add(round, tasks.get(index));
//						tasks.remove(index);
//						tasks.add(index, temp);
//					}
//					else {
//						if (tasks.get(round).getDuedate().compareTo(tasks.get(index).getDuedate()) > 0) {
//							temp = tasks.get(round);
//							tasks.remove(round);
//							tasks.add(round, tasks.get(index));
//							tasks.remove(index);
//							tasks.add(index, temp);
//						}
//					}
//				}
//			}
//		}
		
//		Task minimumTask = tasks.get(0);
//		int minYear = Integer.valueOf(minimumTask.getDate().split("/")[2]);
//		int minMonth = Integer.valueOf(minimumTask.getDate().split("/")[1]);
//		int minDay = Integer.valueOf(minimumTask.getDate().split("/")[0]);
//		int minHours = Integer.valueOf(minimumTask.getTime().split(":")[0]);
//		int minMinutes = Integer.valueOf(minimumTask.getTime().split(":")[1]);
//		int year;
//		int month;
//		int day;
//		int hours;
//		int minutes;
//		
//		int firstIndexSort = 0;
//		for (Task task : tasks) {
//			for (int i = firstIndexSort; i <= tasks.size()-1; i++) {
//				year = Integer.valueOf(tasks.get(i).getDate().split("/")[2]);
//				month = Integer.valueOf(tasks.get(i).getDate().split("/")[1]);
//				day = Integer.valueOf(tasks.get(i).getDate().split("/")[0]);
//				hours = Integer.valueOf(tasks.get(i).getTime().split(":")[0]);
//				minutes = Integer.valueOf(tasks.get(i).getTime().split(":")[1]);
//				
//				/* this case for task that mark as important */
//				if (minimumTask.getImportantFlag()) {
//					if (tasks.get(i).getImportantFlag()) {
//						if (year < minYear) {
//							minimumTask = tasks.get(i);
//						} else if (year < minYear) {
//							minimumTask = tasks.get(i);
//						} else if (month < minMonth) {
//							minimumTask = tasks.get(i);
//						} else if (day < minDay) {
//							minimumTask = tasks.get(i);
//						} else if (hours < minHours) {
//							minimumTask = tasks.get(i);
//						} else if (minutes < minMinutes) {
//							minimumTask = tasks.get(i);
//						}
//					}
//				}
//					
//				/* this is normal case */
//				else {
//					if (year < minYear) {
//						minimumTask = tasks.get(i);
//					} else if (year < minYear) {
//						minimumTask = tasks.get(i);
//					} else if (month < minMonth) {
//						minimumTask = tasks.get(i);
//					} else if (day < minDay) {
//						minimumTask = tasks.get(i);
//					} else if (hours < minHours) {
//						minimumTask = tasks.get(i);
//					} else if (minutes < minMinutes) {
//						minimumTask = tasks.get(i);
//					}
//				}
//			}
//			firstIndexSort++;
//			sortedTask.add(minimumTask);
//		}
		

		return new ResponseEntity<>(tasks, HttpStatus.OK);
//		return new ResponseEntity<>(tasks, HttpStatus.OK);
		
	}
	
	public List<Task> sortTask(List<Task> tasks) {
		Collections.sort(tasks);
		return tasks;
	}

}
