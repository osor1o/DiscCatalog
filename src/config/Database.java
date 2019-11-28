package config;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
	private static Connection connection = null;
	
	// Construct
	private Database() {
		try {
			Class.forName("org.sqlite.JDBC");
			Database.connection = DriverManager.getConnection("jdbc:sqlite::resource:storage.db");
		} catch(Exception e) {
			System.out.println("[Database Error] " + e);
		}
	}
	
	public static Connection getConnection() {
		if(Database.connection == null) {
			new Database();
		}
		return Database.connection;
	}
}
