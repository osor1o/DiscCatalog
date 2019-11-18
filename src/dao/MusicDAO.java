package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Band;
import model.Music;

public class MusicDAO extends DAO {
	
	public static ArrayList<Music> list(Band b)  {		
		try {
			String sql = "SELECT * FROM Music, Band WHERE Music.band = Band.id AND band = ? ORDER BY Music.name ASC";
			PreparedStatement stmt = conn.prepareStatement(sql);
			if(b == null) {
				stmt.setInt(1, 0);
			} else {
				stmt.setInt(1, b.getId());
			}
			ResultSet rs = stmt.executeQuery();
			ArrayList<Music> musics = new ArrayList<Music>();
			while(rs.next()) {
				int id = rs.getInt(4);
				String name = rs.getString(6);
				int year = rs.getInt(7);
				Band band = new Band(id, name, year);
				
				id = rs.getInt(1);
				name = rs.getString(2);
				year = rs.getInt(3);
				Music music = new Music(id, name, year, band);
				musics.add(music);
				
			}
			return musics;
		} catch(SQLException e) {
			System.out.println(e);
			JOptionPane.showMessageDialog(null, "Não foi possível buscar músicas!");
			return null;
		}
	}
	
}
