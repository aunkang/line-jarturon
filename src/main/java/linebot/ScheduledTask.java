package linebot;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.message.TextMessage;


@Component
@EnableScheduling
public class ScheduledTask {
	
	 @Autowired
	 public LineMessagingClient lineMessagingClient;
	 
//	@Scheduled(cron = "0 0 12 1/1 * ? *",zone = "Asia/Thailand")
	@Scheduled(cron = "*/10 * * * * *")
	public void pushNotification() {
		Map<String, List<Task>> tasks = Application.maps;
		Set<String> userIds = tasks.keySet();
		for (String userId : userIds ) {
			String replyMessage = "This is your task \n\n";
			List<Task> targetTasks = tasks.get(userId);
			for (Task targetTask : targetTasks) {
				replyMessage += "Task Name: " + targetTask.getName() + "\n";
			}
			PushMessage pushMessage = new PushMessage(userId, new TextMessage(replyMessage));
			lineMessagingClient.pushMessage(pushMessage);
		}
	}

}
