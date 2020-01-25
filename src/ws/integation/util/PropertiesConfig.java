package ws.integation.util;

import ws.integation.system.environment.SystemType;

public class PropertiesConfig {
	
	private static PropertiesConfig propertiesConfig = null;
	
	private String serverLineIntegation;
	
	private String dbLineIntegation;
	
	private String usernameLineIntegation;
	
	private String passwordLineIntegation;
	
	private String searchCustomer;
	
	private String domainCus20;
	
	public PropertiesConfig() {
		dbLineIntegation = "customer";
		switch (SystemType.ENV_TYPE) {
			case LOCALHOST:
				serverLineIntegation = "jdbc:postgresql://localhost:5432/";
				usernameLineIntegation = "root";
				passwordLineIntegation = "admin";
				domainCus20 = "http://localhost:3001";
				break;			
			case DEV:
				serverLineIntegation = "jdbc:postgresql://localhost:5432/";
				usernameLineIntegation = "root";
				passwordLineIntegation = "admin";
				domainCus20 = "http://localhost:3001";
				break;		
			case SIT:
				serverLineIntegation = "jdbc:postgresql://localhost:5432/";
				usernameLineIntegation = "root";
				passwordLineIntegation = "admin";
				domainCus20 = "http://localhost:3001";
				break;
			case UAT:
				serverLineIntegation = "jdbc:postgresql://localhost:5432/";
				usernameLineIntegation = "root";
				passwordLineIntegation = "admin";
				domainCus20 = "http://localhost:3001";
				break;
			case PROD:
				serverLineIntegation = "jdbc:postgresql://localhost:5432/";
				usernameLineIntegation = "root";
				passwordLineIntegation = "admin";
				domainCus20 = "http://localhost:3001";
				break;
			default:
				break;
		}
		searchCustomer = domainCus20 + "/custom/search";
	}

	public static PropertiesConfig getInstance() {
		if (propertiesConfig == null) {
			propertiesConfig = new PropertiesConfig();
			return propertiesConfig;
		}
		else {
			return propertiesConfig;
		}
	}

	public String getServerLineintegation() {
		return serverLineIntegation;
	}

	public void setServerLineIntegation(String data) {
		this.serverLineIntegation = data;
	}

	public String getDbLineIntegation() {
		return dbLineIntegation;
	}

	public void setDatabasenameTLwebCA(String data) {
		this.dbLineIntegation= data;
	}

	public String getUsernameLineIntegation() {
		return usernameLineIntegation;
	}

	public void setUsernameTLwebCA(String data) {
		this.usernameLineIntegation = data;
	}

	public String getPasswordLineIntegation() {
		return passwordLineIntegation;
	}

	public void setPasswordLineIntegation(String data) {
		this.passwordLineIntegation = data;
	}

	public String getSearchCustomer() {
		return searchCustomer;
	}

	public void setSearchCustomer(String data) {
		this.searchCustomer = data;
	}

	public String getDomainCus20() {
		return domainCus20;
	}

	public void setDomainTLProPlus(String data) {
		this.domainCus20 = data;
	}
}
