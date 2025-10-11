package NesneyeYonelikProje;

import java.sql.SQLException;
import java.util.List;

public class SearchModel implements SearchStrategy  {
	public List<Car> search(CarDAO carDAO, String model) throws SQLException {
		// TODO Auto-generated method stub
		return carDAO.SearchCars("",model);
	}
}
