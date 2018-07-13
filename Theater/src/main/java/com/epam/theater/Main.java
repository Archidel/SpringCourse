package com.epam.theater;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
	public static void main(String[] args) {
		System.out.println("Start");
		String date = "1995-08-26";
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = null;
		try {
			date1 = format.parse(date);
		} catch (ParseException e) {
			System.err.println("ERROR!!!!!");
			e.printStackTrace();
		}
		
		System.out.println(date1);
	}
}
