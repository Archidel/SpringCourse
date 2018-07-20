package com.epam.theater.controller.command;

public class CommandImpl implements Command {

	@Override
	public String execute(String request) {
		System.out.println("Command impl method!");
		return null;
	}

}
