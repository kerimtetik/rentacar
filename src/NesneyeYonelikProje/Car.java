package NesneyeYonelikProje;

public abstract class Car {
	private String marka, model, yakit, vites, plaka,kasa;
	private int bedel;
	private Boolean uygun;

	public Car(String marka, String model, String vites, String yakit, int bedel, Boolean uygun, String plaka,String kasa) {
		this.marka = marka;
		this.model = model;
		this.vites = vites;
		this.yakit = yakit;
		this.bedel = bedel;
		this.uygun = uygun;
		this.plaka = plaka;
		this.kasa =  kasa;
	}

	public String getKasa() {
		return kasa;
	}

	public void setKasa(String kasa) {
		this.kasa = kasa;
	}

	public String getPlaka() {
		return plaka;
	}
	public void setPlaka(String plaka) {
		this.plaka = plaka;
	}
	public String getMarka() {
		return marka;
	}
	public void setMarka(String marka) {
		this.marka = marka;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getYakit() {
		return yakit;
	}
	public void setYakit(String yakit) {
		this.yakit = yakit;
	}
	public String getVites() {
		return vites;
	}
	public void setVites(String vites) {
		this.vites = vites;
	}
	public int getBedel() {
		return bedel;
	}
	public void setBedel(int bedel) {
		this.bedel = bedel;
	}
	
	public Boolean getUygun() {
		return uygun;
	}
	public void setUygun(Boolean uygun) {
		this.uygun = uygun;
	}
	
	
	public abstract void displayCarInfo();


}
