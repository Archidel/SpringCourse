package com.epam.theater.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.stereotype.Component;

import com.epam.theater.configuration.ContextProvider;
import com.epam.theater.controller.command.Command;

@Component
public class Controller {
	private static final Logger logger = LoggerFactory.getLogger(Controller.class);
	private static final String WRONG_COMMAND = "wrong_command";

	public static Command getCommand(String commandName) {
		Command command = null;

		if (commandName != null) {
			try {
				command = (Command) ContextProvider.getBean(commandName);
			} catch (NoSuchBeanDefinitionException e) {
				logger.error("Wrong command: " + commandName, e);
				command = (Command) ContextProvider.getBean(WRONG_COMMAND);
			}
		} else {
			logger.error("Wrong command: " + commandName);
			command = (Command) ContextProvider.getBean(WRONG_COMMAND);
		}

		return command;
	}

}
