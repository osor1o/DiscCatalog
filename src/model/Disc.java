package model;

import java.util.ArrayList;

public class Disc extends Model {
	private String name;
	private int year;
	private Band band;
	private ArrayList<Music> musics;
	
	// Construct
	public Disc(int id, String name, int year, Band band, ArrayList<Music> musics) {
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

	public ArrayList<Music> getMusics() {
		return musics;
	}

	public void setMusics(ArrayList<Music> musics) {
		this.musics = musics;
	}
}
