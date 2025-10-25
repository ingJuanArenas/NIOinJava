import java.util.Scanner;

import Model.Car;
import Service.CarService;

public class App {

    private static Scanner sc= new Scanner(System.in);
    private static CarService carService= new CarService();
    public static void main(String[] args) throws Exception {
        boolean salir = false;
          carService.loadAllCars();
        do {
          

                    System.out.println("""
            ==== LOTE====
            1. ver carros
            2. agregar carro
            3. consultar carro
            4. borrar carro
            0.salir
                """);

        var op= sc.nextInt();

        switch (op) {
            case 1:
                carService.getCars();
                break;
            case 2:
                System.out.println("Model");
                var  model = sc.nextLine();
                System.out.println("Year;");
                var year = sc.nextInt();
                System.out.println("serial");
                var serial0 = sc.nextLine();
                System.out.println("Price");
                var price = sc.nextDouble();
                carService.addCar(new Car(model, year, serial0, price));
                break;
            case 3:
            sc.nextLine();
            System.out.println("INgrese el serial");
            var serial = sc.nextLine();
                System.out.println(carService.getCarBySerial(serial));
                break;
            case 4:
            sc.nextLine();
            System.out.println("INgrese el serial");
            var serial1 = sc.nextLine();
            carService.deleteCar(serial1);
            break;
            case 0:
             System.out.println("Saliendo .....");
            salir = true;
            default:
            System.out.println("Opcion invalida");
                break;
        }
        } while (salir != true);

    }
}
