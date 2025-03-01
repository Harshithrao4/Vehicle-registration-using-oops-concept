package models;

public class Bike extends Vehicle {
    @SuppressWarnings("unused")
    private int engineCapacity;

    public Bike(String brand, String model, double rentalPrice, int engineCapacity) {
        super(brand, model, rentalPrice);
        this.engineCapacity = engineCapacity;
    }

    @Override
    public String getType() {
        return "Bike";
    }
}
