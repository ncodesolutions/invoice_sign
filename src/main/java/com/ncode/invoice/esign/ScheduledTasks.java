package com.ncode.invoice.esign;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

@Component
public class ScheduledTasks {

	@Autowired
	private Main main;
	
	@PostConstruct
//    @Scheduled(cron = "0 */1 * * * ?") //every 2 min you call 
//    @Scheduled(cron = "0 0 1 * * ?")  // Daily  Running on the at 1:00 AM
//    @Scheduled(cron = "0 0 1 1 * ?") // Running on the 1st of every month at 1:00 AM
    public void runEveryTwoMinutes() {
        System.out.println("Task executed every 2 minutes");
        main.startSign();
    }


    
    
}
