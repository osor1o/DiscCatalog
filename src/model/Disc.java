package model;

import valueObject.Name;
import valueObject.Year;

public class Disc extends Model {
	private Name name;
	private Band band;
	private Categorie[] categories;
	private Year releaseYear;
	
	public Disc(Name name, Band band, Categorie[] categories, Year releaseYear) {
		this.name = name;
		this.band = band;
		this.categories = categories;
		this.releaseYear = releaseYear;
	}
}
