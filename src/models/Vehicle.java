package models;

public abstract class Vehicle {
    protected String brand;
    protected String model;
    public double rentalPrice;
    protected boolean isAvailable;

    public Vehicle(String brand, String model, double rentalPrice) {
        this.brand = brand;
        this.model = model;
        this.rentalPrice = rentalPrice;
        this.isAvailable = true;
    }

    public abstract String getType();

    public boolean isAvailable() {
        return isAvailable;
    }

    public void rentVehicle() {
        if (isAvailable) {
            isAvailable = false;
        } else {
            System.out.println("Vehicle is already rented.");
        }
    }

    public void returnVehicle() {
        isAvailable = true;
    }

    @Override
    public String toString() {
        return getType() + " - " + brand + " " + model + " (₹" + rentalPrice + " per day)";
    }
}
