package NesneyeYonelikProje;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class CarTableModel extends AbstractTableModel{
	private final List<Car> cars;
    private final String[] columnNames = {"Plaka", "Marka", "Model", "Vites", "Yakıt", "Ücret", "Uygun", "Kasa"};

    public CarTableModel(List<Car> cars) {
        this.cars = cars;
    }

    @Override
    public int getRowCount() {
        return cars.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Car car = cars.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return car.getPlaka();
            case 1:
                return car.getMarka();
            case 2:
                return car.getModel();
            case 3:
                return car.getVites();
            case 4:
                return car.getYakit();
            case 5:
                return car.getBedel();
            case 6:
                return car.getUygun();
            case 7:
                return car.getKasa();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}
	