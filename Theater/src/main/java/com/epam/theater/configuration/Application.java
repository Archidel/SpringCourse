package com.epam.theater.configuration;

import java.util.HashMap;
import java.util.Map;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.epam.theater.controller.Controller;
import com.epam.theater.controller.command.Command;
import static com.epam.theater.controller.Constants.PARAM_NAME_USER_NAME;
import static com.epam.theater.controller.Constants.PARAM_NAME_USER_SURNAME;
import static com.epam.theater.controller.Constants.PARAM_NAME_USER_EMAIL;
import static com.epam.theater.controller.Constants.PARAM_NAME_USER_ID;
import static com.epam.theater.controller.Constants.PARAM_NAME_AUDITORIUM_NAME;
import static com.epam.theater.controller.Constants.PARAM_NAME_EVENT_NAME;
import static com.epam.theater.controller.Constants.PARAM_NAME_EVENT_ID;
import static com.epam.theater.controller.Constants.PARAM_NAME_EVENT_BASE_PRICE;
import static com.epam.theater.controller.Constants.PARAM_NAME_EVENT_DATETIME;
import static com.epam.theater.controller.Constants.PARAM_NAME_EVENT_RATING;
import static com.epam.theater.controller.Constants.PARAM_NAME_AUDITORIUM_NUMBER_OF_SEATS;
import static com.epam.theater.controller.Constants.PARAM_NAME_AUDITORIUM_VIP_SEATS;
import static com.epam.theater.controller.Constants.PARAM_NAME_USER_SEATS;
import static com.epam.theater.controller.Constants.PARAM_NAME_TICKET_SEAT;;

public class Application {

	public static void main(String[] args) throws InterruptedException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
		ContextProvider.setApplicationContext(context);

		Command command = null;
		String response = null;
		Map<String, String> requestParam = new HashMap<String, String>();

		// ###############################
		// ###### User functionality #####
		// ###############################

		// Save user
		
/*		  requestParam.put(PARAM_NAME_USER_NAME, "Albert");
		  requestParam.put(PARAM_NAME_USER_SURNAME, "Zarankovich");
		  requestParam.put(PARAM_NAME_USER_EMAIL, "Albert_Zarankovich@epam.com");
		  
		  command = Controller.getCommand("save_user"); response =
		  command.execute(requestParam); printResponse(response);
		 */

		// Get all users
		command = Controller.getCommand("get_all_users");
		response = command.execute(null);
		printResponse(response);

		// GetUserByEmail
		requestParam.put(PARAM_NAME_USER_EMAIL, "Albert_Zarankovich@epam.com");

		command = Controller.getCommand("get_user_by_email");
		response = command.execute(requestParam);
		printResponse(response);

		// get user by ID
		requestParam.put(PARAM_NAME_USER_ID, "1");

		command = Controller.getCommand("get_user_by_id");
		response = command.execute(requestParam);
		printResponse(response);

		// ###############################
		// ### Auditorium functionality ##
		// ###############################

		// Auditorium save
/*		requestParam.put(PARAM_NAME_AUDITORIUM_NAME, "audit1");
		requestParam.put(PARAM_NAME_AUDITORIUM_NUMBER_OF_SEATS, "50");
		requestParam.put(PARAM_NAME_AUDITORIUM_VIP_SEATS, "1,2,3,4,5,6,7,8,9,10");
		
		command = Controller.getCommand("save_auditorium");
		response = command.execute(requestParam);
		printResponse(response);
	*/	
		
		// Auditorium getAll
		command = Controller.getCommand("get_all_auditoriums");
		response = command.execute(requestParam);
		printResponse(response);

		// Auditorium getByName
		requestParam.put(PARAM_NAME_AUDITORIUM_NAME, "audit1");

		command = Controller.getCommand("get_auditorium_by_name");
		response = command.execute(requestParam);
		printResponse(response);

		// ###############################
		// ##### Event functionality #####
		// ###############################

		// Event save
/*		requestParam.put(PARAM_NAME_AUDITORIUM_NAME, "audit1");
		requestParam.put(PARAM_NAME_EVENT_NAME, "Event #2");
		requestParam.put(PARAM_NAME_EVENT_BASE_PRICE, "666");
		requestParam.put(PARAM_NAME_EVENT_RATING, "HIGH");
		requestParam.put(PARAM_NAME_EVENT_DATETIME, "2018-10-09T10:45");

		command = Controller.getCommand("save_event");
		response = command.execute(requestParam);
		printResponse(response);
*/
		// Event get by name
		requestParam.put(PARAM_NAME_EVENT_NAME, "Event #1");

		command = Controller.getCommand("get_event_by_name");
		response = command.execute(requestParam);
		printResponse(response);
		
		// Event get all
		command = Controller.getCommand("get_all_event");
		response = command.execute(requestParam);
		printResponse(response);

		// ###############################
		// ### Booking functionality ##
		// ###############################

		// getPrice
		requestParam.put(PARAM_NAME_EVENT_NAME, "Event #1");
		requestParam.put(PARAM_NAME_EVENT_DATETIME, "2018-07-09T10:45");
		requestParam.put(PARAM_NAME_USER_EMAIL, "Albert_Zarankovich@epam.com");
		requestParam.put(PARAM_NAME_USER_SEATS, "50");
		
		command = Controller.getCommand("get_price");
		response = command.execute(requestParam);
		printResponse(response);
		
		// booking ticket
		requestParam.put(PARAM_NAME_EVENT_NAME, "Event #1");
		requestParam.put(PARAM_NAME_EVENT_DATETIME, "2018-07-09T10:45");
		requestParam.put(PARAM_NAME_USER_EMAIL, "Albert_Zarankovich@epam.com");
		requestParam.put(PARAM_NAME_TICKET_SEAT, "66");
		
		command = Controller.getCommand("booking_ticket");
		response = command.execute(requestParam);
		printResponse(response);
		
		
		// getPurchesTickes
		requestParam.put(PARAM_NAME_EVENT_NAME, "Event #1");
		requestParam.put(PARAM_NAME_EVENT_DATETIME, "2018-07-09T10:45");
		
		command = Controller.getCommand("get_purchased_tickets");
		response = command.execute(requestParam);
		printResponse(response);
		
		
		// getDiscount
		requestParam.put(PARAM_NAME_EVENT_NAME, "Event #1");
		requestParam.put(PARAM_NAME_USER_EMAIL, "Albert_Zarankovich@epam.com");
		requestParam.put(PARAM_NAME_USER_SEATS, "1");
		requestParam.put(PARAM_NAME_EVENT_DATETIME, "2018-07-09T10:45");
		
		command = Controller.getCommand("get_discount");
		response = command.execute(requestParam);
		printResponse(response);
	}

	private static void printResponse(String response) {
		if (response != null) {
			System.out.println("##########################################");
			System.out.println(response);
			System.out.println("##########################################");
		}
	}
}
