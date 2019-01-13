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
	 
	@Scheduled(cron = "*/10 * * * * *", zone="Asia/Bangkok")
	public void pushNotification12PM() {
		Map<String, List<Task>> tasks = Application.maps;
		Set<String> userIds = tasks.keySet();
		for (String userId : userIds ) {
			String replyMessage = "This is your task \n\n";
			List<Task> targetTasks = tasks.get(userId);
			for (Task targetTask : targetTasks) {
				replyMessage += "Task Name: " + targetTask.getName() + "\n";
			}
			replyMessage += "\n\n See all task click link below \n line://app/1613138841-EqAkLo6L";
			PushMessage pushMessage = new PushMessage(userId, new TextMessage(replyMessage));
			lineMessagingClient.pushMessage(pushMessage);
		}
	}
	 
	 @Scheduled(cron = "0 0 18 1/1 * ? *", zone="Asia/Bangkok")
		public void pushNotification6PM() {
		 pushNotification12PM();
		}

}
