package com.epam.theater.controller.command.impl;

import com.epam.theater.controller.command.Command;

public class WrongCommand implements Command {

	@Override
	public String execute(String request) {
		return "Sorry, command not found!";
	}

}
