package Model;

public class Car {
    private String model;
    private int year;
    private String serial;
    private double price;

    
    public Car(String model, int year, String serial, double price) {
        this.model = model;
        this.year = year;
        this.serial = serial;
        this.price = price;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public String getSerial() {
        return serial;
    }
    public void setSerial(String serial) {
        this.serial = serial;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    @Override
    public String toString() {
        return  model + "," + year + "," + serial + "," + price ;
    }


    
    
}