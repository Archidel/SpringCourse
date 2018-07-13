package com.epam.theater.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.theater.controller.command.Command;

public class Controller {
	private static final Logger logger = LoggerFactory.getLogger(Controller.class);
	private static Map<String, Command> commands;

	public static Command getCommand(String commandName) {
		Command command = commands.get(commandName);

		if (command == null) {
			logger.error("Wrong command: " + commandName);
			command = commands.get("wrong_command");
		}

		return command;
	}

	public static void setCommands(Map<String, Command> commands) {
		Controller.commands = commands;
	}

}
