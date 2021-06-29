package com.pragmatic.anassifi.todolist.postgreSQL.util;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PostgresMyScheduler {
	
	@Scheduled(fixedDelayString = "PT10M", initialDelay = 3000)
	public void aLilSch() {
		PostgresMyThread mt = new PostgresMyThread();
		mt.start();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	    Date now = new Date();
	    String strDate = sdf.format(now);
	    System.out.println("At " + strDate);
	}
}
