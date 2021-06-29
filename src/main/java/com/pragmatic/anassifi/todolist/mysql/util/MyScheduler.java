package com.pragmatic.anassifi.todolist.mysql.util;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class MyScheduler {
	
	@Scheduled(fixedDelayString = "PT10M", initialDelay = 3000)
	public void aLilSch() {
		MyThread mt = new MyThread();
		mt.start();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	    Date now = new Date();
	    String strDate = sdf.format(now);
	    System.out.println("At " + strDate);
	}
}
