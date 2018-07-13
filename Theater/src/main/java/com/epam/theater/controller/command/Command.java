package com.epam.theater.controller.command;

@FunctionalInterface
public interface Command {
	String SEPARATOR = " ";

	String execute(String request);
}
