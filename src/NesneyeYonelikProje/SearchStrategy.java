package NesneyeYonelikProje;
import java.sql.SQLException;
import java.util.List;
public interface SearchStrategy {
	List<Car> search(CarDAO carDAO,String kriter) throws SQLException;
}
