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
		
		  requestParam.put(PARAM_NAME_USER_NAME, "Albert");
		  requestParam.put(PARAM_NAME_USER_SURNAME, "Zarankovich");
		  requestParam.put(PARAM_NAME_USER_EMAIL, "Albert_Zarankovich@epam.com");
		  
		  command = Controller.getCommand("save_user"); response =
		  command.execute(requestParam); printResponse(response);
		 

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

		// Remove user
		// requestParam.put(PARAM_NAME_USER_ID, "1");

		// command = Controller.getCommand("remove_user");
		// response = command.execute(requestParam);
		// printResponse(response);

		// ###############################
		// ### Auditorium functionality ##
		// ###############################

		// Auditorium save
		requestParam.put(PARAM_NAME_AUDITORIUM_NAME, "audit1");
		requestParam.put(PARAM_NAME_AUDITORIUM_NUMBER_OF_SEATS, "50");
		requestParam.put(PARAM_NAME_AUDITORIUM_VIP_SEATS, "1,2,3,4,5,6,7,8,9,10");
		
		command = Controller.getCommand("save_auditorium");
		response = command.execute(requestParam);
		printResponse(response);
		
		
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
		requestParam.put(PARAM_NAME_AUDITORIUM_NAME, "audit1");
		requestParam.put(PARAM_NAME_EVENT_NAME, "Event #1");
		requestParam.put(PARAM_NAME_EVENT_BASE_PRICE, "666");
		requestParam.put(PARAM_NAME_EVENT_RATING, "HIGH");
		requestParam.put(PARAM_NAME_EVENT_DATETIME, "2018-07-09T10:45");

		command = Controller.getCommand("save_event");
		response = command.execute(requestParam);
		printResponse(response);

		// Event get by name
		requestParam.put(PARAM_NAME_EVENT_NAME, "Event #1");

		command = Controller.getCommand("get_event_by_name");
		response = command.execute(requestParam);
		printResponse(response);

		// Event remove
		requestParam.put(PARAM_NAME_EVENT_ID, "0");

		command = Controller.getCommand("event_remove");
		response = command.execute(requestParam);
		printResponse(response);

		// ###############################
		// ### Booking functionality ##
		// ###############################

		// getPrice
		// getPurchesTickes
		// getDiscount

		/*
		 * LocalDateTime ldt1 = LocalDateTime.of(2018, Month.AUGUST, 8, 18, 00);
		 * LocalDateTime ldt2 = LocalDateTime.of(2018, Month.DECEMBER, 9, 12, 15);
		 * LocalDateTime ldt3 = LocalDateTime.of(2018, Month.JULY, 18, 11, 45);
		 * LocalDateTime ldt4 = LocalDateTime.of(2018, Month.JUNE, 20, 19, 00);
		 * 
		 * try { Auditorium auditorium1 = auditoriumService.getByName("auditorium_2");
		 * Auditorium auditorium2 = auditoriumService.getByName("auditorium_3");
		 * Auditorium auditorium3 = auditoriumService.getByName("auditorium_4");
		 * Auditorium auditorium4 = auditoriumService.getByName("auditorium_5");
		 * 
		 * // Evemt #1 Event event = new Event(); event.setName("Event #1");
		 * event.setBasePrice(100.0); event.setRating(EventRating.HIGH);
		 * event.addAirDateTime(ldt1); event.addAirDateTime(ldt2);
		 * event.assignAuditorium(ldt1, auditorium1); event.assignAuditorium(ldt2,
		 * auditorium2); eventService.save(event);
		 * printResponse("Event has been saved");
		 * 
		 * // Evemt #2 event = new Event(); event.setName("Event #2");
		 * event.setBasePrice(15.0); event.setRating(EventRating.LOW);
		 * event.addAirDateTime(ldt3); event.addAirDateTime(ldt4);
		 * event.assignAuditorium(ldt3, auditorium3); event.assignAuditorium(ldt4,
		 * auditorium4); eventService.save(event);
		 * printResponse("Event has been saved"); } catch (ServiceException e) {
		 * logger.error("Error save event", e); }
		 * 
		 * // Event get by name try { Event event = eventService.getByName("Event #1");
		 * printResponse(event.toString()); } catch (ServiceException e) {
		 * logger.error("Error of getting event by name", e); }
		 * 
		 * // Event get by name try { Event event = eventService.getByName("Event #1");
		 * printResponse(event.toString()); } catch (ServiceException e) {
		 * logger.error("Error of getting event by name", e); }
		 * 
		 * // Event get by id try { Event event = eventService.getById(1L);
		 * printResponse(event.toString()); } catch (ServiceException e) {
		 * logger.error("Error of getting event by id", e); }
		 * 
		 * // Event remove try { Event event = eventService.getById(0L);
		 * eventService.remove(event); printResponse("Event has been removed"); } catch
		 * (ServiceException e) { logger.error("Error of removing event", e); }
		 * 
		 * // ############################### // ### Booking functionality ## //
		 * ###############################
		 * 
		 * // getPrice try { Event event = eventService.getByName("Event #2"); user =
		 * userService.getUserByEmail("Albert_Zarankovich@epam.com"); double totalprice
		 * = bookingService.getTicketsPrice(event, ldt3, user, new
		 * HashSet<Long>(Arrays.asList(6L, 66L))); printResponse("Total price: " +
		 * totalprice); } catch (ServiceException e) { logger.error("Error of booking",
		 * e); }
		 * 
		 * // getPrice try { Event event = eventService.getByName("Event #2"); user =
		 * userService.getUserByEmail("Albert_Zarankovich@epam.com"); Set<Ticket>
		 * tickets = new HashSet<Ticket>(); tickets.add(new Ticket(user, event, ldt3,
		 * 6L)); tickets.add(new Ticket(user, event, ldt3, 66L));
		 * 
		 * bookingService.bookTickets(tickets);
		 * printResponse("Tickets has been booked by " + user.getEmail()); } catch
		 * (ServiceException e) { logger.error("Error of booking", e); }
		 * 
		 * // getPurchesTickes try { Event event = eventService.getByName("Event #2");
		 * Set<Ticket> tickets = bookingService.getPurchasedTicketsForEvent(event,
		 * ldt3); printResponse(tickets.toString()); } catch (ServiceException e) {
		 * logger.error("Error of booking", e); }
		 * 
		 * // getDiscount try { Event event = eventService.getByName("Event #2"); user =
		 * userService.getUserByEmail("Albert_Zarankovich@epam.com"); byte disc =
		 * discountService.getDiscount(user, event, LocalDateTime.of(2018, Month.JULY,
		 * 18, 00, 00), 10); printResponse("Discount = " + disc); } catch
		 * (ServiceException e) { logger.error("Error get discount", e); }
		 */

	}

	private static void printResponse(String response) {
		if (response != null) {
			System.err.println(response);
		}
	}
}
