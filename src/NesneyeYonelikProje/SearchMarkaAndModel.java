package NesneyeYonelikProje;

import java.sql.SQLException;
import java.util.List;

public class SearchMarkaAndModel implements SearchStrategy{

	@Override
	public List<Car> search(CarDAO carDAO, String kriter) throws SQLException {
		String[] kriterler = kriter.split(",");
        String marka = kriterler[0];
        String model = kriterler[1];
        return carDAO.SearchCars(marka, model);
	}

}
