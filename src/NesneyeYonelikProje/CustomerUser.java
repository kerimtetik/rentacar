package NesneyeYonelikProje;

public class CustomerUser extends User{

	public CustomerUser(String ad, String soyad, String adres, String kullanici_adi, String kullanici_sifre, String tc,
			long numara) {
		super(ad, soyad, adres, kullanici_adi, kullanici_sifre, tc, numara);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void UserType() {
		System.out.println("User type: Müsteri");
		
	}

}
