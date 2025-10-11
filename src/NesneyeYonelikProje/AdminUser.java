package NesneyeYonelikProje;

public class AdminUser extends User {

	public AdminUser(String ad, String soyad, String adres, String kullanici_adi, String kullanici_sifre, String tc,
			long numara) {
		super(ad, soyad, adres, kullanici_adi, kullanici_sifre, tc, numara);
	}

	@Override
	public void UserType() {
		System.out.println("User type: admin");	
	}
	
	
}
