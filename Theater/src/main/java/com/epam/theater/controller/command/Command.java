package com.epam.theater.controller.command;

import java.util.Map;

public interface Command {
	String SEPARATOR = " ";

	String execute(Map<String, String> request);
}
