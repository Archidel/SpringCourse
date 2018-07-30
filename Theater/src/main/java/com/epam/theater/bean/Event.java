package com.epam.theater.bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Objects;
import java.util.TreeMap;
import java.util.TreeSet;

public class Event extends DomainObject {
	private String name;
	private NavigableSet<LocalDateTime> airDates = new TreeSet<LocalDateTime>();
	private double basePrice;
	private EventRating rating;
	private NavigableMap<LocalDateTime, Auditorium> auditoriums = new TreeMap<LocalDateTime, Auditorium>();

	public Event() {
	}

	public Event(String name, NavigableSet<LocalDateTime> airDates, double basePrice, EventRating rating,
			NavigableMap<LocalDateTime, Auditorium> auditoriums) {
		this.name = name;
		this.airDates = airDates;
		this.basePrice = basePrice;
		this.rating = rating;
		this.auditoriums = auditoriums;
	}

	public boolean assignAuditorium(LocalDateTime dateTime, Auditorium auditorium) {
		if (airDates.contains(dateTime)) {
			auditoriums.put(dateTime, auditorium);
			return true;
		} else {
			return false;
		}
	}

	public boolean removeAuditoriumAssignment(LocalDateTime dateTime) {
		return auditoriums.remove(dateTime) != null;
	}

	public boolean addAirDateTime(LocalDateTime dateTime) {
		return airDates.add(dateTime);
	}

	public boolean addAirDateTime(LocalDateTime dateTime, Auditorium auditorium) {
		boolean result = airDates.add(dateTime);
		if (result) {
			auditoriums.put(dateTime, auditorium);
		}
		return result;
	}

	public boolean removeAirDateTime(LocalDateTime dateTime) {
		boolean result = airDates.remove(dateTime);
		if (result) {
			auditoriums.remove(dateTime);
		}
		return result;
	}

	public boolean airsOnDateTime(LocalDateTime dateTime) {
		return airDates.stream().anyMatch(dt -> dt.equals(dateTime));
	}

	public boolean airsOnDate(LocalDate date) {
		return airDates.stream().anyMatch(dt -> dt.toLocalDate().equals(date));
	}

	public boolean airsOnDates(LocalDate from, LocalDate to) {
		return airDates.stream()
				.anyMatch(dt -> dt.toLocalDate().compareTo(from) >= 0 && dt.toLocalDate().compareTo(to) <= 0);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public NavigableSet<LocalDateTime> getAirDates() {
		return airDates;
	}

	public void setAirDates(NavigableSet<LocalDateTime> airDates) {
		this.airDates = airDates;
	}

	public double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}

	public EventRating getRating() {
		return rating;
	}

	public void setRating(EventRating rating) {
		this.rating = rating;
	}

	public NavigableMap<LocalDateTime, Auditorium> getAuditoriums() {
		return auditoriums;
	}

	public void setAuditoriums(NavigableMap<LocalDateTime, Auditorium> auditoriums) {
		this.auditoriums = auditoriums;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Event other = (Event) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Event [name=" + name + ", airDates=" + airDates + ", basePrice=" + basePrice + ", rating=" + rating
				+ ", auditoriums=" + auditoriums + "]";
	}

}
