package model;

public class Band extends Model {
	private String name;
	private int year;
	
	// Construct
	public Band(int id, String name, int year) {
		this.id = id;
		this.name = name;
		this.year = year;
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
	
	public String toString() {
		return name;
	}
}
