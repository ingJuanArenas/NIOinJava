package Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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

    public  void deleteCar(String serial)throws IOException{
        var carFound = getCarBySerial(serial);
        if (carFound.isPresent()) {
           cars.remove(carFound);
           saveAllCars();
        }else{
            System.out.println("No se ha encontrado el serial");
        }
    }

    public void saveCar(Car car){
        try {
        var line = String.join(",",
        car.getModel(),
        String.valueOf(car.getYear()),
        car.getSerial(),
        String.valueOf(car.getPrice())
    );

        Files.writeString(Paths.get("../cars.txt"), line, 
                StandardOpenOption.CREATE, StandardOpenOption.APPEND);

            System.out.println("Carro agregado exitosament");

        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    public void saveAllCars()throws IOException{
        
            cars.forEach((c)->{
                var line = String.join(",",
                c.getModel(),
                String.valueOf(c.getYear()),
                c.getSerial(),
                String.valueOf(c.getPrice())
    );
                
            try {
                Files.writeString(Paths.get("../cars.txt"), line, StandardOpenOption.CREATE_NEW, StandardOpenOption.APPEND);
            } catch (IOException e) {
                System.out.println(e);
            }

            });
        }
    }