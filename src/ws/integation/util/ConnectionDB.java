package ws.integation.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDB {	
	private static PropertiesConfig config = null;
	
	private ConnectionDB() {
		throw new IllegalStateException("Utility class");
	}

	public static Connection getDbCustomer() {
		try {
			if (config == null) {
				config = PropertiesConfig.getInstance();
			}
			String url = config.getServerLineintegation();
			String user = config.getUsernameLineIntegation();
			String password = config.getPasswordLineIntegation();
			return DriverManager.getConnection(url, user, password);
		} 
		catch (Exception e) {
			return null;
		}
	}
}
