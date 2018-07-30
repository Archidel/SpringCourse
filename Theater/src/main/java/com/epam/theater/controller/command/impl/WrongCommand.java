package com.epam.theater.controller.command.impl;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.epam.theater.controller.command.Command;

@Component(value = "wrong_command")
public class WrongCommand implements Command {

	@Override
	public String execute(Map<String, String> request) {
		return "Wrong command!";
	}

}
