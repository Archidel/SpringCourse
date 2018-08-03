package com.epam.theater.bean;

import java.time.LocalDateTime;
import java.util.Objects;

public class Ticket extends DomainObject {
	private User user;
	private Event event;
	private LocalDateTime dateTime;
	private long seat;

	public Ticket() {
	}

	public Ticket(User user, Event event, LocalDateTime dateTime, long seat) {
		this.user = user;
		this.event = event;
		this.dateTime = dateTime;
		this.seat = seat;
	}

	public User getUser() {
		return user;
	}

	public Event getEvent() {
		return event;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public long getSeat() {
		return seat;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dateTime, event, seat);
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
		Ticket other = (Ticket) obj;
		if (dateTime == null) {
			if (other.dateTime != null) {
				return false;
			}
		} else if (!dateTime.equals(other.dateTime)) {
			return false;
		}
		if (event == null) {
			if (other.event != null) {
				return false;
			}
		} else if (!event.equals(other.event)) {
			return false;
		}
		if (seat != other.seat) {
			return false;
		}
		return true;
	}

	
	@Override
	public String toString() {
		return "Ticket [dateTime=" + dateTime + ", seat=" + seat + "]";
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public void setSeat(long seat) {
		this.seat = seat;
	}

}
