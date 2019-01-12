package linebot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

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
	private List<Task> tasks = new ArrayList<Task>();

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@EventMapping
	public Message handleTextMessage(MessageEvent<TextMessageContent> e) throws Exception {
		System.out.println("event: " + e);
		TextMessageContent message = e.getMessage();

		String input = String.valueOf(message.getText());

		String[] inputs = input.split(":");
		String taskStr = inputs[0].trim();
		String dateStr = inputs[1].trim();
		String hhStr = inputs[2].trim();
		String mmStr = inputs[3].trim();
		Date date = new Date();

		Calendar c = new GregorianCalendar();
		switch (dateStr.toLowerCase()) {
		case "tomorrow": {
			c.add(Calendar.DATE, 1);
			c.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hhStr));
			c.set(Calendar.MINUTE, Integer.parseInt(mmStr));
			c.set(Calendar.MILLISECOND, 0);
			date = c.getTime();
			break;
		}

		case "today": {
			c.add(Calendar.DATE, 1);
			c.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hhStr));
			c.set(Calendar.MINUTE, Integer.parseInt(mmStr));
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

		Task task = new Task();
		task.setName(taskStr);
		task.setDate(dateStr);
		task.setTime(hhStr+mmStr);
		task.setDuedate(date);

		return new TextMessage(
				message.getText() + "    \n" + "name: " + taskStr + "    \n" + "Date: " + dateStr + "    \n" + "HH:mm : " + hhStr+mmStr);
	}
}