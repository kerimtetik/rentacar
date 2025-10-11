package NesneyeYonelikProje;

import java.sql.SQLException;
import java.util.List;

public class SearchMarka implements SearchStrategy{
	@Override
	public List<Car> search(CarDAO carDAO, String marka) throws SQLException {
		// TODO Auto-generated method stub
		return carDAO.SearchCars(marka,"");
	}
}

