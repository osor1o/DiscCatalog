package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Band;

public class BandDAO extends DAO {

	public static void add(String name, int year)  {
		try {
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO Band (name, year) VALUES (?, ?)");
			stmt.setString(1, name);
			stmt.setInt(2, year);
			stmt.execute();
			JOptionPane.showMessageDialog(null, "Banda adicionada com sucesso!");
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Não foi possível adicionar a banda!");
		}
	}
	
	public static ArrayList<Band> list()  {
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Band ORDER BY name ASC");
			ResultSet rs = stmt.executeQuery();
			ArrayList<Band> bands = new ArrayList<Band>();
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int year = rs.getInt("year");
				Band band = new Band(id, name, year);
				bands.add(band);
			}
			return bands;
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Não foi possível buscar bandas!");
			return null;
		}
	}
	
	public static void edit(int id, String name, int year)  {
		try {
			PreparedStatement stmt = conn.prepareStatement("UPDATE Band SET name = ?, year = ? WHERE id = ?");
			stmt.setString(1, name);
			stmt.setInt(2, year);
			stmt.setInt(3, id);
			stmt.execute();
			JOptionPane.showMessageDialog(null, "Banda editada com sucesso!");
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Não foi possível editar a banda!");
		}
	}
	
	public static void remove(int id) {
		try {
			PreparedStatement stmt = conn.prepareStatement("DELETE FROM Band WHERE id = ?");
			stmt.setInt(1, id);
			stmt.execute();
			JOptionPane.showMessageDialog(null, "Banda removida com sucesso!");
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Não foi possível remover a banda!");
		}
	}
	
}
