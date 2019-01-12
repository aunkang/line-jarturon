package linebot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

@SpringBootApplication
@LineMessageHandler
public class Application {
	public static Map<String, List<Task>> maps = new HashMap<String, List<Task>>();

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@EventMapping
	public Message handleTextMessage(MessageEvent<TextMessageContent> e) throws Exception {
		System.out.println("event: " + e);
		String input = String.valueOf(this.getMessage(e));
		String userId = String.valueOf(this.getIdentity(e));

		String[] inputs = input.split(":");
		String taskStr = inputs[0].trim();
		String dateStr = inputs[1].trim();
		String hhStr = inputs[2].trim();
		String mmStr = inputs[3].trim();

		Date dueDate = this.createDuedate(dateStr, hhStr, mmStr);

		Task task = new Task();
		task.setName(taskStr);
		task.setDate(dateStr);
		task.setTime(hhStr + mmStr);
		task.setDuedate(dueDate);

		this.saveTask(userId, task);
		
		
		
		

		return new TextMessage("Your task has been added" + "    \n\n" + "Name: " + taskStr + "    \n" + "Date: " + dueDate
				+ "    \n" + "HH:mm : " + hhStr + ":" + mmStr + "\n\n See all task click link below. \n line://app/1613138841-EqAkLo6L");
	}

	private String getMessage(MessageEvent<TextMessageContent> e) {
		return e.getMessage().getText();
	}

	private String getIdentity(MessageEvent<TextMessageContent> e) {
		return e.getSource().getSenderId();
	}

	private Date createDuedate(String dateStr, String hhStr, String mmStr) throws Exception {
		Date date = new Date();
		Calendar c = new GregorianCalendar();
		switch (dateStr.toLowerCase()) {
		case "tomorrow": {
			c.add(Calendar.DATE, 1);
			c.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hhStr));
			c.set(Calendar.MINUTE, Integer.parseInt(mmStr));
			c.set(Calendar.SECOND, 0);
			c.set(Calendar.MILLISECOND, 0);
			date = c.getTime();
			break;
		}

		case "today": {
			c.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hhStr));
			c.set(Calendar.MINUTE, Integer.parseInt(mmStr));
			c.set(Calendar.SECOND, 0);
			c.set(Calendar.MILLISECOND, 0);
			date = c.getTime();
			break;
		}
		default: {
			SimpleDateFormat formatterDate = new SimpleDateFormat("d/m/yy HH:mm");
			date = formatterDate.parse(dateStr + " " + hhStr + ":" + mmStr);
			break;
		}
		}
		return date;
	}

	private void saveTask(String userId, Task task) {
		if (maps.containsKey(userId)) {
			List<Task> tasks = maps.get(userId);
			tasks.add(task);
		} else {
			List<Task> tasks = new ArrayList<Task>();
			tasks.add(task);
			maps.put(userId, tasks);
		}
	}
}