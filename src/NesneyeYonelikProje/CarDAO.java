package NesneyeYonelikProje;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CarDAO {
	private Connection connection;

	public CarDAO(Connection connection) {
		this.connection = connection;
	}

	public void addCar(Car car) throws SQLException {//parametreler için preparedstatement?
		String sql = "INSERT INTO car (marka, model, vites, yakit, ucret, uygun, plaka,kasa) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, car.getMarka());
			statement.setString(2, car.getModel());
			statement.setString(3, car.getVites());
			statement.setString(4, car.getYakit());
			statement.setInt(5, car.getBedel());
			statement.setBoolean(6, car.getUygun());
			statement.setString(7, car.getPlaka());
			statement.setString(8, car.getKasa());
			statement.executeUpdate();
		}
	}

	public void updateCar(Car car, String plaka) throws SQLException {
	    String sql = "UPDATE car SET marka = ?, model = ?, vites=? , yakit = ?, ucret = ?, uygun = ?, kasa = ? WHERE plaka = ?";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, car.getMarka());
			statement.setString(2, car.getModel());
			statement.setString(3, car.getVites());
			statement.setString(4, car.getYakit());
			statement.setInt(5, car.getBedel());
			statement.setBoolean(6, car.getUygun());
			statement.setString(7, car.getKasa());
	        statement.setString(8, plaka);
			int affectedRows = statement.executeUpdate();
			System.out.println("Update completed. Rows affected: " + affectedRows);
		}
	}

	public void deleteCar(String plaka) throws SQLException {
		String sql = "DELETE FROM car WHERE plaka = ?";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, plaka);
			statement.executeUpdate();
		}
	}
	public List<Car> getAllCars() throws SQLException {
        List<Car> cars = new ArrayList<>();
        String sql = "SELECT * FROM car";
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Car car = createCarFromResultSet(resultSet);
                cars.add(car);
            }
        }
        return cars;
    }

	public List<Car> SearchCars(String str, String str2) throws SQLException {
        String sql = "SELECT * FROM car WHERE marka = ? OR model = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, str);
            preparedStatement.setString(2, str2);
            
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                List<Car> cars = new ArrayList<>();
                while (resultSet.next()) {
                    Car car = createCarFromResultSet(resultSet);
                    cars.add(car);
                }
                return cars;
            }
        }
    }
    private Car createCarFromResultSet(ResultSet resultSet) throws SQLException {
        String marka = resultSet.getString("marka");
        String model = resultSet.getString("model");
        String vites = resultSet.getString("vites");
        String yakit = resultSet.getString("yakit");
        int ucret = resultSet.getInt("ucret");
        boolean uygun = resultSet.getBoolean("uygun");
        String plaka = resultSet.getString("plaka");
        String kasa = resultSet.getString("kasa");

        switch (kasa) {
            case "Sedan":
                return new SedanCar(marka, model, yakit, vites, ucret, uygun, plaka, kasa);
            case "Hatchback":
                return new HatchbackCar(marka, model, yakit, vites, ucret, uygun, plaka, kasa);
            case "SUV":
                return new SuvCar(marka, model, yakit, vites, ucret, uygun, plaka, kasa);
            default:
                return null;
        }
    }
    public Car getCarByPlate(String plaka) throws SQLException {
        String query = "SELECT * FROM car WHERE plaka = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, plaka);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String marka = resultSet.getString("marka");
            String model = resultSet.getString("model");
            String yakit = resultSet.getString("yakit");
            String vites = resultSet.getString("vites");
            int ucret = resultSet.getInt("ucret");
            boolean uygun = resultSet.getBoolean("uygun");
            String kasa = resultSet.getString("kasa");

            if (kasa.equals("Sedan")) {
                return new SedanCar(marka, model, yakit, vites, ucret, uygun, plaka, kasa);
            } else if (kasa.equals("Hatchback")) {
                return new HatchbackCar(marka, model, yakit, vites, ucret, uygun, plaka, kasa);
            } else if (kasa.equals("SUV")) {
                return new SuvCar(marka, model, yakit, vites, ucret, uygun, plaka, kasa);
            } else {
                return null;
            }
        }

        return null;
    }


	public static void main(String[] args) {
		
	}
}
