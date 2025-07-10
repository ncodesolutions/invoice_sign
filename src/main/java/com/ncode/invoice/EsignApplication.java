package com.ncode.invoice;

import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.ncode.invoice.esign.Main;

@EnableWebMvc
@ComponentScan(basePackages = { "com.ncode.invoice" })
@SpringBootApplication
@EnableAutoConfiguration
public class EsignApplication 
{
	
//	@Autowired
//    private Main mainEsign;
	
	public static void main(String[] args) 
	{
		System.out.println("Esign Application Started.....!!!!");
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));	
		SpringApplication.run(EsignApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception 
//	{
//		System.out.println("Main Application Started.....!!!!");
//		mainEsign.mainstart(); 
//	}

}
