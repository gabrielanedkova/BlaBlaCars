package model;

import model.Car.Comfort;

public class Car {

	@Override
	public String toString() {
		return "Car [brand=" + brand + ", model=" + model + ", comfort=" + comfort + ", numberOfSeats=" + numberOfSeats
				+ ", color=" + color + ", type=" + type + "]";
	}


	private static final int MAX_NUMBER_OF_SEATS = 10;
	private static final int MIN_NUMBER_OF_SEATS = 2;


	public enum Brand {HONDA, BMW, MERCEDES};
	public enum Comfort{ BASIC, NORMAL, COMFORTABLE, LUXURY};
	public enum Color { BLACK, BLUE, GREEN, WHITE, YELLOW, PINK, RED, GREY, SILVER, GOLD, BEIGE, ORANGE, PURPLE, BROWN};
	public enum TypeOfCar { SALOON, HATCHBACK, CONVERTIBLE, ESTATE, SUV, STATION_WAGON, MINIVAN, VAN};
	
	private Brand brand;
	private String model; //maybe not String
	private Comfort comfort;
	private int numberOfSeats; // 2-6
	private Color color;
	private TypeOfCar type;
	//private ?? photo
	
	
	public Car(Brand brand, String model,  TypeOfCar type, Comfort comfort, int numberOfSeats, Color color) {

		setBrand(brand);
		setModel(model);
		setType(type);
		setComfort(comfort);
		setNumberOfSeats(numberOfSeats);
		setColor(color);
	}


	protected void setBrand(Brand brand) {
		
		if(brand != null){
			this.brand = brand;
		}
	}


	protected void setModel(String model) {
		if(model != null){
			this.model = model;
		}
	}


	protected void setComfort(Comfort comfort) {
		if(comfort != null){
			this.comfort = comfort;
		}
	}


	protected void setNumberOfSeats(int numberOfSeats) {
		// this is including driver
		if(numberOfSeats >= MIN_NUMBER_OF_SEATS && numberOfSeats <= MAX_NUMBER_OF_SEATS){
			this.numberOfSeats = numberOfSeats;
		}
	}


	protected void setColor(Color color) {
		if(color != null){
			this.color = color;
		}
	}


	protected void setType(TypeOfCar type) {
		if(type != null){
			this.type = type;
		}
	}


	public Comfort getComfort() {
		return comfort;
	}
	
	

}
