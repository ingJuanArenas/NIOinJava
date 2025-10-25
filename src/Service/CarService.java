package Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import Model.Car;


public class CarService {
    private List<Car> cars;

    public CarService() {
        this.cars = new ArrayList<>();
    }

    public void loadAllCars(){
                try {
            var lines= Files.readAllLines(Paths.get("../cars.txt"));
            lines.forEach((line)->{
                var c = line.split(",");
                if (c.length == 4) {
                    var model = c[0];
                    var year= Integer.parseInt(c[1]);
                    var serial = c[2];
                    var price= Double.parseDouble(c[3]);

                    cars.add(new Car(model,year, serial, price));
                }
            });
        } catch (IOException e) {
            System.out.println("ERROR: "+ e);
        }
    }
    public  void getCars(){
        cars.forEach(c-> System.out.println(c));
    }
    
    public  Optional<Car> getCarBySerial(String serial){
        return cars.stream().filter(c-> c.getSerial().equals(serial)).findFirst();
    }

    public  void addCar(Car car){
        var carFound = getCarBySerial(car.getSerial());
        if (carFound.isPresent()) {
            System.out.println("El serial ya se encuentra registrado");
        }else{
            cars.add(car);
            saveCar(car);
            System.out.println("Operacion Exitosa");
        }
    }

public void deleteCar(String serial){
    var carFound = getCarBySerial(serial);
    if (carFound.isPresent()) {
        cars.remove(carFound.get());
        saveAllCars();
        System.out.println("Carro eliminado correctamente.");
    } else {
        System.out.println("No se ha encontrado el serial");
    }
}

public void saveAllCars() {
      try {
        var content = new StringBuilder();
        cars.forEach(c-> content.append(c.toString() + System.lineSeparator()));

        Files.writeString(Paths.get("../cars.txt"), content.toString());
      } catch (IOException e) {
        e.printStackTrace();
      }
}

    public void saveCar(Car car){
       try {
        Files.writeString(Paths.get("../cars.txt"),car.toString(),StandardOpenOption.APPEND);
       } catch (IOException e) {
        System.out.println("ERROR: " +e);
       }
    }
    }