package com.epam.theater.service.utills;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DataUtill {
	private DataUtill() {
	}

	public static final Date dateFormatter(String dateStr) throws ParseException {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return format.parse(dateStr);
	}
}
