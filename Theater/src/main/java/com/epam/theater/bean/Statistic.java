package com.epam.theater.bean;

public class Statistic {
	private int nameTimes;
	private int priceTimes;
	private int ticketWasbookingTimes;

	public int getTicketWasbookingTimes() {
		return ticketWasbookingTimes;
	}

	public void setTicketWasbookingTimes(int ticketWasbookingTimes) {
		this.ticketWasbookingTimes = ticketWasbookingTimes;
	}

	public int getNameTimes() {
		return nameTimes;
	}

	public void setNameTimes(int nameTimes) {
		this.nameTimes = nameTimes;
	}

	public int getPriceTimes() {
		return priceTimes;
	}

	public void setPriceTimes(int priceTimes) {
		this.priceTimes = priceTimes;
	}

	@Override
	public String toString() {
		return "Statistic [nameTimes=" + nameTimes + ", priceTimes=" + priceTimes + ", ticketWasbookingTimes="
				+ ticketWasbookingTimes + "]";
	}

}
