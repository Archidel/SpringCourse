package com.epam.theater.bean;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Auditorium {
	private String name;

	private long numberOfSeats;

	private Set<Long> vipSeats = Collections.emptySet();

	public Auditorium(String name, long numberOfSeats, Set<Long> vipSeats) {
		this.name = name;
		this.numberOfSeats = numberOfSeats;
		this.vipSeats = vipSeats;
	}

	public Auditorium() {
	}

	public long countVipSeats(Collection<Long> seats) {
		return seats.stream().filter(seat -> vipSeats.contains(seat)).count();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getNumberOfSeats() {
		return numberOfSeats;
	}

	public void setNumberOfSeats(long numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}

	public Set<Long> getAllSeats() {
		return LongStream.range(1, numberOfSeats + 1).boxed().collect(Collectors.toSet());
	}

	public Set<Long> getVipSeats() {
		return vipSeats;
	}

	public void setVipSeats(Set<Long> vipSeats) {
		this.vipSeats = vipSeats;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (int) (numberOfSeats ^ (numberOfSeats >>> 32));
		result = prime * result + ((vipSeats == null) ? 0 : vipSeats.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Auditorium other = (Auditorium) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (numberOfSeats != other.numberOfSeats)
			return false;
		if (vipSeats == null) {
			if (other.vipSeats != null)
				return false;
		} else if (!vipSeats.equals(other.vipSeats))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Auditorium [name=" + name + ", numberOfSeats=" + numberOfSeats + ", vipSeats=" + vipSeats + "]";
	}

}
