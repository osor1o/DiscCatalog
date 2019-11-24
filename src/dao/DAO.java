package dao;

import java.sql.Connection;

import config.Database;

public class DAO {
	protected static Connection conn = Database.getConnection();
}
