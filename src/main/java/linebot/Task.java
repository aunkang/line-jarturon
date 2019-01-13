package linebot;

import java.util.Comparator;
import java.util.Date;

public class Task implements Comparable<Task> {
	private String date;
	private String time;
	private String name;
	private Date duedate;
	private String taskId;
	private Boolean importantFlag;
	
	


	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public Boolean isImportantFlag() {
		return importantFlag;
	}

	public void setImportantFlag(Boolean importantFlag) {
		this.importantFlag = importantFlag;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDuedate() {
		return duedate;
	}

	public void setDuedate(Date duedate) {
		this.duedate = duedate;
	}

	@Override
	public int compareTo(Task compareFruit) {

		// ascending order
		return this.getDuedate().compareTo(compareFruit.getDuedate());

		// descending order
		// return compareQuantity - this.quantity;

	}

	public static Comparator<Task> DueDateComparator = new Comparator<Task>() {

		public int compare(Task fruit1, Task fruit2) {

			if (fruit1.getDuedate() == null || fruit2.getDuedate() == null) {
				return 0;
			}

			// ascending order
			return fruit1.getDuedate().compareTo(fruit2.getDuedate());

			// descending order
			// return fruitName2.compareTo(fruitName1);
		}

	};

}
