package model;

public class Disc extends Model {
	private String name;
	private Band band;
	private int year;
	private Music[] musics;
	
	public Disc(int id, String name, Band band, int year, Music[] musics) {
		this.id = id;
		this.name = name;
		this.band = band;
		this.year = year;
		this.musics = musics;
	}
}
