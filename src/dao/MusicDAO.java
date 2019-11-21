package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Band;
import model.Disc;
import model.Music;

public class MusicDAO extends DAO {
	
	public static void add(String name, int year, Band b) {
		try {
			String sql = "INSERT INTO Music (name, year, band) VALUES (?, ?, ?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.setInt(2, year);
			stmt.setInt(3, b.getId());
			stmt.execute();
			JOptionPane.showMessageDialog(null, "Música adicionada com sucesso!");
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Não foi possível adicionar a música!");
		}
	}
	
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
			JOptionPane.showMessageDialog(null, "Não foi possível buscar músicas!");
			return null;
		}
	}
	
	public static ArrayList<Music> list(Disc d)  {		
		try {
			String sql = "SELECT Music.id, Music.name, Music.year\n" + 
					"FROM MusicDisc, Disc, Music\n" + 
					"WHERE MusicDisc.disc = Disc.id\n" + 
					"AND MusicDisc.music = Music.id\n" + 
					"AND MusicDisc.disc = ?;";
			PreparedStatement stmt = conn.prepareStatement(sql);
			if(d == null) {
				stmt.setInt(1, 0);
			} else {
				stmt.setInt(1, d.getId());
			}
			ResultSet rs = stmt.executeQuery();
			ArrayList<Music> musics = new ArrayList<Music>();
			while(rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				int year = rs.getInt(3);
				Music music = new Music(id, name, year, null);
				musics.add(music);
				
			}
			return musics;
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Não foi possível buscar músicas!");
			return null;
		}
	}
	
	public static void edit(int id, String name, int year, Band b) {
		try {
			String sql = "UPDATE Music SET name = ?, year = ?, band = ? WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.setInt(2, year);
			stmt.setInt(3, b.getId());
			stmt.setInt(4, id);
			stmt.execute();
			JOptionPane.showMessageDialog(null, "Música editada com sucesso!");
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Não foi possível editar a música!");
		}
	}
	
	public static void remove(int id) {
		try {
			PreparedStatement stmt = conn.prepareStatement("DELETE FROM Music WHERE id = ?");
			stmt.setInt(1, id);
			stmt.execute();
			JOptionPane.showMessageDialog(null, "Música removida com sucesso!");
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Não foi possível remover a música!");
		}
	}
}
