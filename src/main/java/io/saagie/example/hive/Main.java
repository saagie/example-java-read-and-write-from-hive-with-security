package io.saagie.example.hive;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Logger;

public class Main {


	private static final Logger logger = Logger.getLogger("io.saagie.example.hive.Main");
	private static final String JDBC_DRIVER_NAME = "org.apache.hive.jdbc.HiveDriver";
	private static String connectionUrl;
	private static String user;
	private static String password;

	public static void main(String[] args) throws IOException {

		if (args.length < 1) {
			logger.severe("3 arg are required :\n\t- connectionurl ex: jdbc:hive2://hiveserver:10000/;ssl=false \n" +
					"\t-user\n" +
					"\t-password");
			System.err.println("3 args are required :\n\t-connectionurl  ex: jdbc:hive2://hiveserver:10000/;ssl=false \n" +
					"\t-user\n" +
					"\t-password");
			System.exit(128);
		}
		// Get url Connection
		connectionUrl = args[0];
		// Get user
		user = args[1];
		// Get password
		password = args[2];

		// Init Connection
		Connection con = null;

		//==== Select data from Hive Table
		String sqlStatementDrop = "DROP TABLE IF EXISTS helloworld";
		String sqlStatementCreate = "CREATE TABLE helloworld (message String) STORED AS PARQUET";
		String sqlStatementInsert = "INSERT INTO helloworld VALUES (\"helloworld\")";
		String sqlStatementSelect = "SELECT * from helloworld";
		try {
			// Set JDBC Hive Driver
			Class.forName(JDBC_DRIVER_NAME);
			// Connect to Hive
			con = DriverManager.getConnection(connectionUrl,user,password);
			// Init Statement
			Statement stmt = con.createStatement();
			// Execute DROP TABLE Query
			stmt.execute(sqlStatementDrop);
			// Execute CREATE Query
			stmt.execute(sqlStatementCreate);
			// Execute INSERT Query
			stmt.execute(sqlStatementInsert);
			// Execute SELECT Query
			ResultSet rs = stmt.executeQuery(sqlStatementSelect);
			while(rs.next()) {
				logger.info(rs.getString(1));
			}

		} catch (Exception e) {
			logger.severe(e.getMessage());
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				// swallow
			}
		}

	}
}
