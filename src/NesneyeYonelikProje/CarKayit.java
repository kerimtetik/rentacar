package NesneyeYonelikProje;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Window.Type;
import javax.swing.border.EtchedBorder;
import java.awt.SystemColor;

public class CarKayit extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldmodel;
    private JTextField textFieldmarka;
    private JTextField textFielducret;
    private JTable table2;
    String sorgu;
    DefaultTableModel model = new DefaultTableModel();
    Object[] kolonlar = {"plaka", "marka", "model", "vites", "yakit", "ucret", "uygun","kasa"};    
    Object[] satirlar = new Object[8];

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CarKayit frame = new CarKayit();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    JComboBox<String> comboBoxuygun;
    private JTextField textFieldplaka;
    private JComboBox<String> comboBoxCarType;

    public CarKayit() throws SQLException {
    	setType(Type.UTILITY);
        Veritabani.baglan();
        String url = "jdbc:postgresql://localhost:5432/rentacar";
        String username = "postgres";
        String password = "1234";
        Connection connection = DriverManager.getConnection(url, username, password);
        
        setTitle("Araç Kayıt");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 983, 484);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(146, 192, 194));
        contentPane.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Ara\u00E7 Kay\u0131t", TitledBorder.CENTER, TitledBorder.BOTTOM, null, new Color(0, 0, 0)));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Marka");
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 17));
        lblNewLabel.setBounds(21, 83, 95, 19);
        contentPane.add(lblNewLabel);
        
        JLabel lblModel = new JLabel("Model");
        lblModel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 17));
        lblModel.setBounds(21, 134, 95, 19);
        contentPane.add(lblModel);
        
        JLabel lblVites = new JLabel("Ücret");
        lblVites.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 17));
        lblVites.setBounds(21, 287, 95, 19);
        contentPane.add(lblVites);
        
        JLabel lblNewLabel_2_1 = new JLabel("Vites");
        lblNewLabel_2_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 17));
        lblNewLabel_2_1.setBounds(21, 185, 95, 19);
        contentPane.add(lblNewLabel_2_1);
        
        JLabel lblNewLabel_2_2 = new JLabel("Yakıt");
        lblNewLabel_2_2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 17));
        lblNewLabel_2_2.setBounds(21, 236, 95, 19);
        contentPane.add(lblNewLabel_2_2);
        
        textFieldmodel = new JTextField();
        textFieldmodel.setBackground(SystemColor.info);
        textFieldmodel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        textFieldmodel.setBounds(126, 135, 118, 21);
        contentPane.add(textFieldmodel);
        textFieldmodel.setColumns(10);
        
        textFieldmarka = new JTextField();
        textFieldmarka.setBackground(SystemColor.info);
        textFieldmarka.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        textFieldmarka.setColumns(10);
        textFieldmarka.setBounds(126, 84, 118, 21);
        contentPane.add(textFieldmarka);
        
        textFielducret = new JTextField();
        textFielducret.setBackground(SystemColor.info);
        textFielducret.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        textFielducret.setColumns(10);
        textFielducret.setBounds(126, 288, 118, 21);
        contentPane.add(textFielducret);
        
        comboBoxuygun = new JComboBox<String>();
        comboBoxuygun.setBackground(SystemColor.info);
        comboBoxuygun.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        comboBoxuygun.setModel(new DefaultComboBoxModel<String>(new String[] {"Evet", "Hayır"}));
        comboBoxuygun.setBounds(126, 331, 118, 33);
        contentPane.add(comboBoxuygun);
        
        JComboBox<String> comboBoxvites = new JComboBox<String>();
        comboBoxvites.setBackground(SystemColor.info);
        comboBoxvites.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        comboBoxvites.setModel(new DefaultComboBoxModel(new String[] {"Benzin", "Dizel", "Hibrit", "Elektrikli"}));
        comboBoxvites.setBounds(126, 229, 118, 33);
        contentPane.add(comboBoxvites);
        
        JComboBox<String> comboBoxyakit = new JComboBox<String>();
        comboBoxyakit.setBackground(SystemColor.info);
        comboBoxyakit.setModel(new DefaultComboBoxModel(new String[] {"Manuel\t", "Otomatik"}));
        comboBoxyakit.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        comboBoxyakit.setBounds(126, 178, 118, 33);
        contentPane.add(comboBoxyakit);

        JLabel lblCarType = new JLabel("Araba Türü");
        lblCarType.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 17));
        lblCarType.setBounds(21, 389, 95, 19);
        contentPane.add(lblCarType);

        comboBoxCarType = new JComboBox<String>();
        comboBoxCarType.setBackground(SystemColor.info);
        comboBoxCarType.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        comboBoxCarType.setModel(new DefaultComboBoxModel(new String[] {"Sedan", "Hatchback", "SUV"})); // Buraya diğer araba türlerini de ekleyebilirsiniz
        comboBoxCarType.setBounds(126, 382, 118, 33);
        contentPane.add(comboBoxCarType);
        
        JButton ekleButton = new JButton("EKLE");
        ekleButton.setBackground(Color.LIGHT_GRAY);
        ekleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String marka = textFieldmarka.getText();
                    String model = textFieldmodel.getText();
                    String vites = (String) comboBoxvites.getSelectedItem();
                    String yakit = (String) comboBoxyakit.getSelectedItem();            
                    int ucret = Integer.parseInt(textFielducret.getText());
                    boolean uygun;
                    String plaka = textFieldplaka.getText();
                    String secilenSecenek = (String) comboBoxuygun.getSelectedItem();
                    if (secilenSecenek.equals("Evet")) {
                        uygun = true;
                    } else {
                        uygun = false;
                    }
                    
                    String kasa = (String) comboBoxCarType.getSelectedItem();
                    Car car;
                    if (kasa.equals("Sedan")) {
                        car = new SedanCar(marka, model, yakit, vites, ucret, uygun, plaka,kasa);
                    }else if(kasa.equals("Hatchback")){
                    	car = new HatchbackCar(marka, model, yakit, vites, ucret, uygun, plaka,kasa);
                    }else if(kasa.equals("SUV")){
                    	car = new SuvCar(marka, model, yakit, vites, ucret, uygun, plaka,kasa);
                    }
                    else {  
                        return;
                    }
                    
                    
                    CarDAO carDAO = new CarDAO(connection);
                    carDAO.addCar(car);
                
                    JOptionPane.showMessageDialog(null, "Araba başarıyla eklendi.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Geçersiz ücret.");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Araba eklenirken bir hata oluştu: " + ex.getMessage());
                }
            }
        });
    
        ekleButton.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
        ekleButton.setBounds(297, 375, 151, 47);
        contentPane.add(ekleButton);
        
        JButton btnSil = new JButton("SİL");
        btnSil.setForeground(Color.BLACK);
        btnSil.setBackground(Color.LIGHT_GRAY);
        btnSil.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table2.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Lütfen silmek için bir satır seçin.");
                    return;
                }

                try {
                    String plaka = model.getValueAt(selectedRow, 0).toString(); // Seçilen satırdaki plaka bilgisini al
                    CarDAO carDAO = new CarDAO(connection);
                    carDAO.deleteCar(plaka); // Arabayı veritabanından sil
                    model.removeRow(selectedRow); // Tablodan satırı sil
                    JOptionPane.showMessageDialog(null, "Araba başarıyla silindi.");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Araba silinirken bir hata oluştu: " + ex.getMessage());
                }
            }
        });
        btnSil.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
        btnSil.setBounds(745, 375, 151, 47);
        contentPane.add(btnSil);
        
        JButton btnUpdate = new JButton("GÜNCELLE");
        btnUpdate.setBackground(Color.LIGHT_GRAY);
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String marka = textFieldmarka.getText();
                    String model = textFieldmodel.getText();
                    String vites = (String) comboBoxvites.getSelectedItem();
                    String yakit = (String) comboBoxyakit.getSelectedItem();
                    int ucret = Integer.parseInt(textFielducret.getText());
                    boolean uygun;
                    String secilenSecenek = (String) comboBoxuygun.getSelectedItem();
                    String plaka = textFieldplaka.getText();
                    if (secilenSecenek.equals("Evet")) {
                        uygun = true;
                    } else {
                        uygun = false;
                    }
                    
                    String kasa = (String) comboBoxCarType.getSelectedItem();
                    Car car;
                    if (kasa.equals("Sedan")) {
                        car = new SedanCar(marka, model, yakit, vites, ucret, uygun, plaka,kasa);
                    }else if(kasa.equals("Hatchback")){
                    	car = new HatchbackCar(marka, model, yakit, vites, ucret, uygun, plaka,kasa);
                    }else if(kasa.equals("SUV")) {
                        car = new SuvCar(marka, model, yakit, vites, ucret, uygun, plaka,kasa);
                    }
                    else {                      
                        return;
                    }
                    
                    CarDAO carDAO = new CarDAO(connection);
                    carDAO.updateCar(car, plaka);
                    JOptionPane.showMessageDialog(null, "Araba başarıyla güncellendi.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Geçersiz ücret.");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Araba güncellenirken bir hata oluştu: " + ex.getMessage());
                }
            }
        });
        btnUpdate.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
        btnUpdate.setBounds(521, 375, 151, 47);
        contentPane.add(btnUpdate);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(296, 80, 596, 284);
        contentPane.add(scrollPane);
        
        table2 = new JTable();
        table2.setForeground(Color.DARK_GRAY);
        table2.setBackground(SystemColor.info);
        scrollPane.setViewportView(table2);
        model.setColumnIdentifiers(kolonlar);
        table2.setModel(model);
        
        textFieldplaka = new JTextField();
        textFieldplaka.setBackground(SystemColor.info);
        textFieldplaka.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        textFieldplaka.setColumns(10);
        textFieldplaka.setBounds(126, 33, 118, 21);
        contentPane.add(textFieldplaka);
        
        JLabel lblNewLabel_1 = new JLabel("Plaka");
        lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 17));
        lblNewLabel_1.setBounds(21, 32, 95, 19);
        contentPane.add(lblNewLabel_1);
        
        JButton listeleButton = new JButton("OTOMOBİLLERİ LİSTELE");
        listeleButton.setBackground(Color.LIGHT_GRAY);
        listeleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.setRowCount(0); // Mevcut tablodaki tüm verileri temizleyip tekrarı engellemek için
                
                sorgu = "SELECT * FROM car";
                ResultSet rs = Veritabani.listele(sorgu);
                try {
                    while (rs.next()) {
                        satirlar[0] = rs.getString("plaka");
                        satirlar[1] = rs.getString("marka");
                        satirlar[2] = rs.getString("model");
                        satirlar[3] = rs.getString("vites");
                        satirlar[4] = rs.getString("yakit");
                        satirlar[5] = rs.getInt("ucret");
                        satirlar[6] = rs.getBoolean("uygun");
                        satirlar[7] = rs.getString("kasa");
                        model.addRow(satirlar);
                    }
                    table2.setModel(model);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        listeleButton.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
        listeleButton.setBounds(297, 18, 375, 47);
        contentPane.add(listeleButton);
        
        JLabel lblUygun = new JLabel("Uygun");
        lblUygun.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 17));
        lblUygun.setBounds(21, 338, 95, 19);
        contentPane.add(lblUygun);
        
        JButton btnGeri = new JButton("Geri");
        btnGeri.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                giris g1 = new giris();
                g1.setVisible(true);
                setVisible(false);
        	}
        });
        btnGeri.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
        btnGeri.setBackground(new Color(146, 192, 194));
        btnGeri.setBounds(856, 10, 103, 44);
        contentPane.add(btnGeri);

        table2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table2.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Lütfen bir satır seçin.");
                    return;
                }

                try {
                    // Sütun sayısını kontrol et
                    int columnCount = table2.getColumnCount();
                    if (columnCount < 8) { // 8 sütun olduğu
                        JOptionPane.showMessageDialog(null, "Tabloda yeterli sütun yok.");
                        return;
                    }

                    // Veri türlerini ve değerleri kontrol et
                    textFieldplaka.setText(model.getValueAt(selectedRow, 0).toString());
                    textFieldmarka.setText(model.getValueAt(selectedRow, 1).toString());
                    textFieldmodel.setText(model.getValueAt(selectedRow, 2).toString());
                    comboBoxyakit.setSelectedItem(model.getValueAt(selectedRow, 3).toString());
                    comboBoxvites.setSelectedItem(model.getValueAt(selectedRow, 4).toString());
                    textFielducret.setText(model.getValueAt(selectedRow, 5).toString());

                    Object uygunValue = model.getValueAt(selectedRow, 6);
                    if (uygunValue instanceof Boolean) {
                        boolean uygun = (Boolean) uygunValue;
                        String uygunString = uygun ? "Evet" : "Hayır";
                        comboBoxuygun.setSelectedItem(uygunString);
                    } else {
                        JOptionPane.showMessageDialog(null, "Uygun sütunundaki değer beklenen türde değil.");
                        return;
                    }

                    comboBoxCarType.setSelectedItem(model.getValueAt(selectedRow, 7).toString());

                } catch (ArrayIndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(null, "Seçilen satırda beklenmeyen bir hata oluştu: " + ex.getMessage());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Beklenmeyen bir hata oluştu: " + ex.getMessage());
                }
            }
        });
    }
}
