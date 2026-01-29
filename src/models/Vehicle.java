package models;

public abstract class Vehicle {
    private String id;
    private String model;
    private String type;
    private double pricePerDay;
    private boolean available;
    
    public Vehicle(String id, String model, String type, double pricePerDay) {
        this.id = id;
        this.model = model;
        this.type = type;
        this.pricePerDay = pricePerDay;
        this.available = true;
    }
    
    public String getId() { return id; }
    public String getModel() { return model; }
    public String getType() { return type; }
    public double getPricePerDay() { return pricePerDay; }
    public boolean isAvailable() { return available; }
    
    public void setAvailable(boolean available) {
        this.available = available;
    }
    
    public abstract String getVehicleDetails();
    
    public double calculateRentalCost(int days) {
        return days * pricePerDay;
    }
    
    @Override
    public String toString() {
        return model + " (" + type + ")";
    }
}
