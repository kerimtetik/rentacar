package NesneyeYonelikProje;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import java.awt.Window.Type;
import java.awt.Color;
import javax.swing.ScrollPaneConstants;
import java.awt.SystemColor;


public class Userekran extends JFrame {

    private JTextField markaField;
    private JTextField modelField;
    private JButton searchButton;
    private JButton listeleButton;
    private JTable resultsTable;
    private JButton btnKirala;
    private JTextField kiragun;
    private CarSearchContext searchContext;

    public Userekran() {
    	setBackground(new Color(146, 192, 194));
    	setType(Type.UTILITY);
        setTitle("Araç Sorgulama");
        setSize(760, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        JLabel label = new JLabel("Marka:");
        label.setBackground(new Color(146, 192, 194));
        label.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        inputPanel.add(label);
        markaField = new JTextField();
        markaField.setBackground(SystemColor.info);
        markaField.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        inputPanel.add(markaField);

        JLabel label_1 = new JLabel("Model:");
        label_1.setBackground(new Color(146, 192, 194));
        label_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
        label_1.setHorizontalAlignment(SwingConstants.CENTER);
        inputPanel.add(label_1);
        modelField = new JTextField();
        modelField.setBackground(SystemColor.info);
        modelField.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        inputPanel.add(modelField);

        searchButton = new JButton("Ara");
        searchButton.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
        searchButton.setBackground(Color.LIGHT_GRAY);
        inputPanel.add(searchButton);
        
                listeleButton = new JButton("Listele");
                listeleButton.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
                listeleButton.setBackground(Color.LIGHT_GRAY);
                inputPanel.add(listeleButton);
                
                        listeleButton.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                listeleCars();
                            }
                        });

        getContentPane().add(inputPanel, BorderLayout.NORTH);
        
        btnKirala = new JButton("Kirala");
        btnKirala.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
        btnKirala.setBackground(Color.LIGHT_GRAY);
        btnKirala.setForeground(Color.BLACK);
        inputPanel.add(btnKirala);
        btnKirala.addActionListener(new ActionListener() { //FACADE
        	public void actionPerformed(ActionEvent e) {
        		kiralaCar();
        	}

			private void kiralaCar() {
				int selectedRow = resultsTable.getSelectedRow();
		        if (selectedRow != -1) {
		            try {
		                String plaka = resultsTable.getValueAt(selectedRow, 0).toString();
		                int kiraGunu = Integer.parseInt(kiragun.getText());

		                try (Connection connection = Veritabani.baglan()) {
		                    CarDAO carDAO = new CarDAO(connection);
		                    Car car = carDAO.getCarByPlate(plaka);

		                    if (car != null && car.getUygun()) {
		                        car.setUygun(false);
		                        int toplamMaliyet = car.getBedel() * kiraGunu;
		                        carDAO.updateCar(car,plaka);

		                        JOptionPane.showMessageDialog(null, "Araba başarıyla " + kiraGunu + " günlüğüne kiralandı.\nToplam Maliyet: " + toplamMaliyet + " TL");
		                    } else {
		                        JOptionPane.showMessageDialog(null, "Araç uygun değil veya bulunamadı.");
		                    }
		                } catch (SQLException e) {
		                    e.printStackTrace();
		                    JOptionPane.showMessageDialog(null, "Veritabanı bağlantısında hata oluştu!", "Hata", JOptionPane.ERROR_MESSAGE);
		                }
		            } catch (NumberFormatException e) {
		                JOptionPane.showMessageDialog(null, "Geçersiz kira günü.", "Hata", JOptionPane.ERROR_MESSAGE);
		            }
		        } else {
		            JOptionPane.showMessageDialog(null, "Lütfen kiralamak için bir araç seçin.", "Hata", JOptionPane.WARNING_MESSAGE);
		        }
				
				
			}
        });
        
        kiragun = new JTextField();
        kiragun.setHorizontalAlignment(SwingConstants.CENTER);
        kiragun.setBackground(SystemColor.info);
        kiragun.setToolTipText("");
        kiragun.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        inputPanel.add(kiragun);
        kiragun.setText("Kaç gün Kiralamak İstersiniz?");
        kiragun.setColumns(10);
        
        String kira_gun = kiragun.getText();
        resultsTable = new JTable();
        resultsTable.setBackground(new Color(146, 192, 194));
        JScrollPane scrollPane = new JScrollPane(resultsTable);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchCars();
            }
        });
    }

    private void searchCars() {
    	String marka = markaField.getText();
    	String model = modelField.getText();
        try (Connection connection = Veritabani.baglan()) {
            CarDAO carDAO = new CarDAO(connection);
            CarSearchContext searchContext = new CarSearchContext();
            List<Car> cars;
            if (!marka.isEmpty() && !model.isEmpty()) {
                searchContext.setStrategy(new SearchMarkaAndModel());
                cars = searchContext.executeStrategy(carDAO, marka + "," + model);
            }else if (!marka.isEmpty()) {
            	searchContext.setStrategy(new SearchMarka());
                cars = searchContext.executeStrategy(carDAO, marka);
            }else if (!model.isEmpty()) {
            	searchContext.setStrategy(new SearchModel());
                cars = searchContext.executeStrategy(carDAO, model);
            } else {
                JOptionPane.showMessageDialog(this, "Lütfen en az bir arama kriteri girin.", "Hata", JOptionPane.WARNING_MESSAGE);
                return;
            }

            CarTableModel tableModel = new CarTableModel(cars);
            resultsTable.setModel(tableModel);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Veritabanı bağlantısında hata oluştu!", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public class CarSearchContext {
        private SearchStrategy strategy;

        public void setStrategy(SearchStrategy strategy) {
            this.strategy = strategy;
        }

        public List<Car> executeStrategy(CarDAO carDAO, String kriter) throws SQLException {
            return strategy.search(carDAO, kriter);
        }
    }
    
    
    private void listeleCars() {
        try (Connection connection = Veritabani.baglan()) {
            CarDAO carDAO = new CarDAO(connection);
            List<Car> cars = carDAO.getAllCars();
            CarTableModel tableModel = new CarTableModel(cars);
            resultsTable.setModel(tableModel);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Veritabanı bağlantısında hata oluştu!", "Hata", JOptionPane.ERROR_MESSAGE);
        }
        kiragun.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Userekran().setVisible(true);
            }
        });
    }
}