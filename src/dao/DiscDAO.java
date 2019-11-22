package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

import model.Band;
import model.Disc;
import model.Music;

public class DiscDAO extends DAO {
	
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
	
	public static void add(String name, int year, Band band, ArrayList<Music> musics) {
		try {
			if(musics.size() < 1) {
				throw new SQLException("O disco deve conter uma música");
			}
			
			String sql = "INSERT INTO Disc (name, year) VALUES (?, ?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.setInt(2, year);
			stmt.execute();
			
			ResultSet rs = stmt.getGeneratedKeys();
			int discId = 0;
			if (rs.next()) {
				discId = rs.getInt(1);
			}
			
			sql = "INSERT INTO MusicDisc (disc, music) VALUES (?, ?)";
			stmt = conn.prepareStatement(sql);
			
			for(Music m : musics) {
				stmt.setInt(1, discId);
				stmt.setInt(2, m.getId());
				stmt.execute();
			}
			
			JOptionPane.showMessageDialog(null, "Disco adicionado com sucesso!");
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Não foi possível adicionar o disco!");
		}
	}
	
	public static void edit(int id, String name, int year, Band band, ArrayList<Music> musics) {
		try {
			if(musics.size() < 1) {
				throw new SQLException("O disco deve conter uma música");
			}
			String sql = "UPDATE Disc SET name = ?, year = ? WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.setInt(2, year);
			stmt.setInt(3, id);
			stmt.execute();
			
			HashMap<Integer, Music> selectedMusics = new HashMap<Integer, Music>();
			for(Music m : musics) {
				selectedMusics.put(m.getId(), m);
			}
			
			HashMap<Integer, Music> bankMusics = new HashMap<Integer, Music>();
			Disc d = new Disc(id, name, year, band, musics);
			for(Music m : MusicDAO.list(d)) {
				bankMusics.put(m.getId(), m);
			}
			
			for(Music m : selectedMusics.values()) {
				if(!bankMusics.containsKey(m.getId())) {
					sql = "INSERT INTO MusicDisc (music, disc) VALUES (?, ?)";
					stmt = conn.prepareStatement(sql);
					stmt.setInt(1, m.getId());
					stmt.setInt(2, id);
					stmt.execute();
				}
			}
			
			for(Music m : bankMusics.values()) {
				if(!selectedMusics.containsKey(m.getId())) {
					sql = "DELETE FROM MusicDisc WHERE music = ? AND disc = ?";
					stmt = conn.prepareStatement(sql);
					stmt.setInt(1, m.getId());
					stmt.setInt(2, id);
					stmt.execute();
				}
			}
			JOptionPane.showMessageDialog(null, "Disco editado com sucesso!");
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Não foi possível editar o disco!");
		}
	}
	
	public static void remove(int id) {
		try {
			PreparedStatement stmt = conn.prepareStatement("DELETE FROM MusicDisc WHERE disc = ?");
			stmt.setInt(1, id);
			stmt.execute();
			
			stmt = conn.prepareStatement("DELETE FROM Disc WHERE id = ?");
			stmt.setInt(1, id);
			stmt.execute();
			JOptionPane.showMessageDialog(null, "Disco removido com sucesso!");
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Não foi possível remover o disco!");
		}
	}
}
