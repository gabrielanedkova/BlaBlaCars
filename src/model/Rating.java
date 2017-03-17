package model;

import java.time.LocalDateTime;

public class Rating implements Comparable<Rating>{

	enum Rate{OUTSTANDING(5.0), EXCELLENT(4.0), GOOD(3.0), POOR(2.0), VERY_DIAPPOINTING(1.0);
		private final double value;
	    private Rate(double value) {
	        this.value = value;
	    }

	    public double getValue() {
	        return value;
	    }};
	    
	private Profile giver;
	private String description;
	private LocalDateTime date;
	private Rate rate;
	
	public Rating(Profile giver, String description, LocalDateTime date, Rate rate) {
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
	
	
	
}
