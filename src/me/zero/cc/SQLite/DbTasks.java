package me.zero.cc.SQLite;

import java.io.File;
import java.sql.ResultSet;

public class DbTasks {
	private SqliteDb sdb = null;

	public DbTasks() {
		initdb();
	}

	private void initdb() {		
		if(!new File("db").exists()){
			new File("db").mkdir();
		}
			try {
				sdb = new SqliteDb("jdbc:sqlite:db/breakout.db");
				CreateDb();
				System.out.println("SqlLite Db geladen!");				
			} catch (Exception e1) {
				System.out.println("Laden der Sql-Lite db fehlgeschlagen!");
				e1.printStackTrace();
			}
	}

	private void CreateDb() {
		System.out.println("Erstelle Datenbanken...");
		try {
			sdb.executeStmt("CREATE TABLE IF NOT EXISTS Points (id int ,Player VARCHAR(20), points int);");			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ResultSet getResultSet(String query) {
		try {
			return sdb.executeQry(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void ExexuteQuery(String query) {
		try {
			sdb.executeStmt(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void CloseConnection() {
		try {
				sdb.CloseConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}