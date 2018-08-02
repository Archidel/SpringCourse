package com.epam.theater.controller;

public interface Constants {
	String PARAM_NAME_USER_ID = "user.id";
	String PARAM_NAME_USER_NAME = "user.name";
	String PARAM_NAME_USER_SURNAME = "user.surname";
	String PARAM_NAME_USER_EMAIL = "user.email";

	String PARAM_NAME_AUDITORIUM_ID = "auditorium.id";
	String PARAM_NAME_AUDITORIUM_NAME = "auditorium.name";
	String PARAM_NAME_AUDITORIUM_NUMBER_OF_SEATS = "auditorium.numberOfSeats";
	String PARAM_NAME_AUDITORIUM_VIP_SEATS = "auditorium.vipSeats";
	
	String PARAM_NAME_EVENT_NAME = "event.name";
	String PARAM_NAME_EVENT_ID = "event.id";
	String PARAM_NAME_EVENT_BASE_PRICE = "event.basePrice";
	String PARAM_NAME_EVENT_RATING = "event.rating";
	String PARAM_NAME_EVENT_DATETIME = "event.dateTime";

	String PROPERTY_NAME_DB_URL = "datasource.url";
	String PROPERTY_NAME_DB_USERNAME = "datasource.username";
	String PROPERTY_NAME_DB_PASSWORD = "datasource.username";
	String PROPERTY_NAME_DB_DRIVER = "datasource.driver-class-name";
}
