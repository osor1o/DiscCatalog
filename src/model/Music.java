package model;

public class Music extends Model {
	private String name;
	private int year;
	private Band band;
	
	public Music(int id, String name, int year, Band band) {
		this.id = id;
		this.name = name;
		this.year = year;
		this.band = band;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Band getBand() {
		return band;
	}

	public void setBand(Band band) {
		this.band = band;
	}
}
