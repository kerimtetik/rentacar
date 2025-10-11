package NesneyeYonelikProje;

public abstract class User {
	protected String ad,soyad,adres,kullanici_adi,kullanici_sifre,tc;
	protected long numara;
	
	public User(String ad,String soyad,String adres,String kullanici_adi,String kullanici_sifre,String tc,long numara) {
		this.ad=ad;
		this.soyad=soyad;
		this.adres=adres;
		this.kullanici_adi=kullanici_adi;
		this.kullanici_sifre=kullanici_sifre;
		this.tc=tc;
		this.numara=numara;
	}
	 public abstract void UserType();
	
}
