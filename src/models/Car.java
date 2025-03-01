package models;

public class Car extends Vehicle {
    @SuppressWarnings("unused")
	private int seatingCapacity;

    public Car(String brand, String model, double rentalPrice, int seatingCapacity) {
        super(brand, model, rentalPrice);
        this.seatingCapacity = seatingCapacity;
    }

    @Override
    public String getType() {
        return "Car";
    }
}
