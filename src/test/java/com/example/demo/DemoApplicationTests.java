package com.example.demo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import linebot.AppRest;
import linebot.Task;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppRest.class)
public class DemoApplicationTests {
	@Autowired
	AppRest appRest;

	@Test
	@Ignore
	public void contextLoads() {
	}

	@Test
	public void sortTaskTest() {
		List<Task> tasks = new ArrayList<Task>();

		Task task = new Task();
		Calendar ca = new GregorianCalendar();
		ca.set(Calendar.DATE, 12);
		ca.set(Calendar.MONTH, 0);
		ca.set(Calendar.YEAR, 2019);

		ca.set(Calendar.HOUR_OF_DAY, 15);
		ca.set(Calendar.MINUTE, 0);
		ca.set(Calendar.MILLISECOND, 0);

		task.setName("Buy MilkC");
		task.setDuedate(ca.getTime());
		task.setDate("12/1/19");
		task.setTime("15:00");
		task.setImportantFlag(false);

		tasks.add(task);
		task = new Task();
		ca = new GregorianCalendar();

		ca.set(Calendar.DATE, 12);
		ca.set(Calendar.MONTH, 0);
		ca.set(Calendar.YEAR, 2019);

		ca.set(Calendar.HOUR_OF_DAY, 14);
		ca.set(Calendar.MINUTE, 0);
		ca.set(Calendar.MILLISECOND, 0);

		task.setName("Buy MilkB");
		task.setDuedate(ca.getTime());
		task.setDate("12/1/19");
		task.setTime("14:00");
		task.setImportantFlag(false);

		tasks.add(task);
		task = new Task();
		ca = new GregorianCalendar();

		ca.set(Calendar.DATE, 12);
		ca.set(Calendar.MONTH, 0);
		ca.set(Calendar.YEAR, 2019);

		ca.set(Calendar.HOUR_OF_DAY, 13);
		ca.set(Calendar.MINUTE, 0);
		ca.set(Calendar.MILLISECOND, 0);

		task.setName("Buy MilkA");
		task.setDuedate(ca.getTime());
		task.setDate("12/1/19");
		task.setTime("13:00");
		task.setImportantFlag(false);

		tasks.add(task);

		tasks = appRest.sortTask(tasks);

		assertEquals("Name is not correct", "Buy MilkA", tasks.get(0).getName());
		assertEquals("Date is not correct", "12/1/19", tasks.get(0).getDate());
		assertEquals("Time is not correct", "13:00", tasks.get(0).getTime());

		assertEquals("Name is not correct", "Buy MilkB", tasks.get(1).getName());
		assertEquals("Date is not correct", "12/1/19", tasks.get(1).getDate());
		assertEquals("Time is not correct", "14:00", tasks.get(1).getTime());

		assertEquals("Name is not correct", "Buy MilkC", tasks.get(2).getName());
		assertEquals("Date is not correct", "12/1/19", tasks.get(2).getDate());
		assertEquals("Time is not correct", "15:00", tasks.get(2).getTime());

	}

}
