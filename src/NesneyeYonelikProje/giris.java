package NesneyeYonelikProje;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import javax.swing.UIManager;
import java.awt.Window.Type;

public class giris extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField Kullanıctext;
	private JPasswordField sifretext;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					giris frame = new giris();
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
	public giris() {
		setBackground(Color.BLACK);
		setType(Type.UTILITY);
		setTitle("GİRİŞ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 631, 342);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(146, 192, 194));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton girisButton = new JButton("Giriş");
		girisButton.setForeground(Color.BLACK);
		girisButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//Aksiyon butonu içinde veri kontrolu
				// form2 penceresini oluştur
				menu f2=new menu();
				Userekran f3= new Userekran();
				String kullanıcı,sifre;
				kullanıcı=Kullanıctext.getText();
				sifre=sifretext.getText();
				//JOptionPane mesaj= new JOptionPane();
			
				if (kullanıcı.equalsIgnoreCase("Sena") && sifre.equalsIgnoreCase("Arslan")) {
					f2.setVisible(true);;
					setVisible(false); 
					
					
				}

				else if (kullanıcı.equalsIgnoreCase("Kerim") && sifre.equalsIgnoreCase("Tetik")) {
					f2.setVisible(true);;
					setVisible(false); 					
				}
				
				else {
					JOptionPane.showMessageDialog(null, "Kullanıcı adı veya şifre eşleşmiyor");
					//mesaj.showConfirmDialog(null,"Giriş başarısız!");
					
				}
				//Car car2=new Car("Pagani","Huayra","Otomatik","Benzin",100000);classı cagırma	
			}
			});
		
		girisButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		girisButton.setBackground(Color.LIGHT_GRAY);
		girisButton.setBounds(158, 189, 116, 39);
		contentPane.add(girisButton);
		
		Kullanıctext = new JTextField();
		Kullanıctext.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		Kullanıctext.setBackground(SystemColor.info);
		Kullanıctext.setBounds(136, 87, 158, 39);
		contentPane.add(Kullanıctext);
		Kullanıctext.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Kullanıcı Adı :");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 90, 116, 36);
		contentPane.add(lblNewLabel);
		
		JLabel lblifre = new JLabel("    Şifre :");
		lblifre.setHorizontalAlignment(SwingConstants.LEFT);
		lblifre.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblifre.setBounds(10, 143, 116, 36);
		contentPane.add(lblifre);
		
		JLabel lblNewLabel_1_1 = new JLabel("  Arslan Rent a car                          Tetik Kasko Güvencesiyle");
		lblNewLabel_1_1.setBackground(Color.WHITE);
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.ITALIC, 18));
		lblNewLabel_1_1.setBounds(10, 0, 607, 32);
		contentPane.add(lblNewLabel_1_1);
		
		sifretext = new JPasswordField();
		sifretext.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		sifretext.setBackground(SystemColor.info);
		sifretext.setBounds(136, 140, 158, 39);
		contentPane.add(sifretext);
		
		JLabel lblNewLabel_1 = new JLabel("Admin Girişi");
		lblNewLabel_1.setBackground(Color.WHITE);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_1.setBounds(136, 42, 158, 39);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Müşteri Girişi");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(383, 76, 158, 35);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Araç Kirala");
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.setBounds(383, 115, 158, 52);
		contentPane.add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Userekran f3 = new Userekran();
                f3.setVisible(true);
                setVisible(false);
            }
		});
	}
}
