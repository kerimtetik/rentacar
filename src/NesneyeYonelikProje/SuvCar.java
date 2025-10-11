package NesneyeYonelikProje;

public class SuvCar extends Car{

	public SuvCar(String marka, String model, String vites, String yakit, int bedel, Boolean uygun, String plaka,String kasa) {
		super(marka, model, vites, yakit, bedel, uygun, plaka,kasa);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void displayCarInfo() {
		System.out.println("Araç bilgisi: " + getMarka() + " " + getModel() + ", Vites: " + getVites() + ", Yakit: " + getYakit() + ", Bedel: " + getBedel() + ", Uygun: " + getUygun() + ", Plaka: " + getPlaka()+",Kasa: "+getKasa());
	}

}
