package NesneyeYonelikProje;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CarDAOTest {
    private Connection connection;
    private CarDAO carDAO;

    @BeforeEach
    public void setUp() throws SQLException {
        setUpDatabase();
    }

    @AfterEach
    public void tearDown() throws SQLException {
        clearDatabase();
        connection.close();
    }

    private void setUpDatabase() throws SQLException {
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/rentacar", "postgres", "1234");
        carDAO = new CarDAO(connection);
       // System.out.println("Veritabanı çalışıyor");
    }



    @Test
    public void testAddCar() throws SQLException {
        Car car = new SedanCar("Toyota", "Camry", "Benzin", "Otomatik", 200, true, "34ABC123", "Sedan");
        try {
            carDAO.addCar(car);
            Car retrievedCar = carDAO.getCarByPlate("34ABC123");
            assertNotNull(retrievedCar);
            assertEquals("Toyota", retrievedCar.getMarka());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Araç ekleme çalışıyor");
    }

    @Test
    public void testUpdateCar() throws SQLException {
        Car car = new SedanCar("Toyota", "Camry", "Benzin", "Otomatik", 200, true, "34ABC124", "Sedan");
        carDAO.addCar(car);

        car.setModel("Corolla");
        car.setUygun(false);
        carDAO.updateCar(car, "34ABC124");

        Car retrievedCar = carDAO.getCarByPlate("34ABC124");
        assertNotNull(retrievedCar);
        assertEquals("Corolla", retrievedCar.getModel());
        assertFalse(retrievedCar.getUygun());
        System.out.println("Araç güncelleme çalışıyor");
    }

    @Test
    public void testDeleteCar() throws SQLException {
        Car car = new SedanCar("Toyota", "Camry", "Benzin", "Otomatik", 200, true, "34ABC125", "Sedan");
        carDAO.addCar(car);

        carDAO.deleteCar("34ABC125");
        Car retrievedCar = carDAO.getCarByPlate("34ABC125");
        assertNull(retrievedCar);
        System.out.println("Araç silme çalışıyor");
    }

    @Test
    public void testGetAllCars() throws SQLException {
        Car car1 = new SedanCar("Toyota", "Camry", "Benzin", "Otomatik", 200, true, "34ABC126", "Sedan");
        Car car2 = new HatchbackCar("Honda", "Civic", "Dizel", "Manuel", 150, true, "35DEF456", "Hatchback");
        carDAO.addCar(car1);
        carDAO.addCar(car2);

        List<Car> cars = carDAO.getAllCars();
        assertEquals(2, cars.size());
        System.out.println("Araç listeleme çalışıyor");
    }

    @Test
    public void testSearchCars() throws SQLException {
        Car car1 = new SedanCar("Toyota", "Camry", "Benzin", "Otomatik", 200, true, "34ABC127", "Sedan");
        Car car2 = new HatchbackCar("Honda", "Civic", "Dizel", "Manuel", 150, true, "35DEF457", "Hatchback");
        carDAO.addCar(car1);
        carDAO.addCar(car2);

        List<Car> cars = carDAO.SearchCars("Toyota", "Civic");
        assertEquals(2, cars.size());
      //  System.out.println("Araç arama çalışıyor");
    }
    private void clearDatabase() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM car");
        }
    }
}
