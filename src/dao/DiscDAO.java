package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Band;
import model.Disc;
import model.Music;

public class DiscDAO extends DAO {
	public static Disc add() {
		return null;
	}
	
	public static ArrayList<Disc> list(Band b) {
		try {
			String sql = 
				"SELECT Disc.id, Disc.name, Disc.year\n" + 
				"FROM MusicDisc, Disc, Music, Band\n" + 
				"WHERE MusicDisc.music = Music.id\n" + 
				"AND MusicDisc.disc = Disc.id\n" + 
				"AND Music.band = Band.id\n" + 
				"AND Music.band = ?\n" + 
				"GROUP BY Disc.id\n" +
				"ORDER BY Disc.name ASC;";
			PreparedStatement stmt = conn.prepareStatement(sql);
			if(b == null) {
				stmt.setInt(1, 0);
			} else {
				stmt.setInt(1, b.getId());
			}
			ResultSet rs = stmt.executeQuery();
			ArrayList<Disc> discs = new ArrayList<Disc>();
			while(rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				int year = rs.getInt(3);
				Disc disc = new Disc(id, name, year, b, null);
				discs.add(disc);
			}
			return discs;
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Não foi possível buscar discos!");
			return null;
		}
	}
	
	
}
