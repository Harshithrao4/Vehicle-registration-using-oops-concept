package services;
import models.Vehicle;
import java.util.ArrayList;
import java.util.List;

public class RentalService {
    private List<Vehicle> vehicles;

    public RentalService() {
        this.vehicles = new ArrayList<>();
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public List<Vehicle> getAvailableVehicles() {
        List<Vehicle> availableVehicles = new ArrayList<>();
        for (Vehicle v : vehicles) {
            if (v.isAvailable()) {
                availableVehicles.add(v);
            }
        }
        return availableVehicles;
    }

    public boolean rentVehicle(int index) {
        if (index >= 0 && index < vehicles.size() && vehicles.get(index).isAvailable()) {
            vehicles.get(index).rentVehicle();
            return true;
        }
        return false;
    }
}
