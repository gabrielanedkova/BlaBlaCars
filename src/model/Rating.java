package model;

import java.time.LocalDateTime;

import model.Rating.Rate;

public class Rating implements Comparable<Rating>{

	public enum Rate{OUTSTANDING(5.0), EXCELLENT(4.0), GOOD(3.0), POOR(2.0), VERY_DIAPPOINTING(1.0);
		private final double value;
	    private Rate(double value) {
	        this.value = value;
	    }

	    public double getValue() {
	        return value;
	    }};
	    
	private Profile giver;
	private Profile receiver;
	private String description;
	private LocalDateTime date;
	private Rate rate;
	
	public Rating(Profile giver, Profile receiver, String description, LocalDateTime date, Rate rate) {
		setReceiver(receiver);
		setGiver(giver);
		setDescription(description);
		setDate(date);
		setRate(rate);
	}

	public Profile getGiver() {
		return giver;
	}
	
	private void setGiver(Profile giver) {
		if (giver != null)
			this.giver = giver;
	}
	
	private void setReceiver(Profile receiver) {
		if (receiver != null)
			this.receiver = receiver;
	}
	public String getDescription() {
		return description;
	}
	
	private void setDescription(String description) {
		if (description != null && !description.isEmpty())
			this.description = description;
	}
	
	public LocalDateTime getDate() {
		return date;
	}
	
	private void setDate(LocalDateTime date) {
		if (date != null)
			this.date = date;
	}
	
	public Rate getRate() {
		return rate;
	}
	
	private void setRate(Rate rate) {
		if (rate != null)
			this.rate = rate;
	}

	@Override
	public int compareTo(Rating o) {
		return o.date.compareTo(this.date);
	}
	
	@Override
	public String toString() {
		return this.description;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((giver == null) ? 0 : giver.hashCode());
		result = prime * result + ((receiver == null) ? 0 : receiver.hashCode());
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
		Rating other = (Rating) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (giver == null) {
			if (other.giver != null)
				return false;
		} else if (!giver.equals(other.giver))
			return false;
		if (receiver == null) {
			if (other.receiver != null)
				return false;
		} else if (!receiver.equals(other.receiver))
			return false;
		return true;
	}

	public long getReceiverId() {
		return receiver.getId();
	}
	
	public long getGiverId() {
		return giver.getId();
	}

}
