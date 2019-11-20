package model;

public class Disc extends Model {
	private String name;
	private Band band;
	private int year;
	private Music[] musics;
	
	public Disc(int id, String name, int year, Band band, Music[] musics) {
		this.id = id;
		this.name = name;
		this.band = band;
		this.year = year;
		this.musics = musics;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Band getBand() {
		return band;
	}

	public void setBand(Band band) {
		this.band = band;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Music[] getMusics() {
		return musics;
	}

	public void setMusics(Music[] musics) {
		this.musics = musics;
	}
}
