package com.epam.theater.controller.command;

@FunctionalInterface
public interface Command {
	String execute(String request);
}
