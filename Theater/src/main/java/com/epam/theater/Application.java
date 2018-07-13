package com.epam.theater;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.epam.theater.controller.Controller;
import com.epam.theater.controller.command.Command;

public class Application {

	@SuppressWarnings({ "unused", "resource" })
	public static void main(String[] args) throws InterruptedException {
		ApplicationContext context = new ClassPathXmlApplicationContext("appContext.xml");
		Controller controller = (Controller) context.getBean("controller");

		Command command = null;
		String response = null;
		
		//Save user
		command = Controller.getCommand("user_save_command");
		response = command.execute("Vladimir Ulyanov Vladimir_Ulyanov@epam.com 26.08.1995");
		printResponse(response);

		//Get all users from DB
		command = Controller.getCommand("get_all_users_command");
		response = command.execute(null);
		printResponse(response);
	
	
	}

	private static void printResponse(String response) {
		if (response != null) {
			System.out.println(response);
		}
	}

}
