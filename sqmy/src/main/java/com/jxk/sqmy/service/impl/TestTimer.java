package com.jxk.sqmy.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
@Component
public class TestTimer {
	@Scheduled(cron = "0/30 * * * * ? ")
	public void test() {
		System.out.println(new  SimpleDateFormat(
	            "yyyy-MM-dd HH:mm:ss").format(new Date()));
	}

}