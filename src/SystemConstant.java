

public class SystemConstant {
	
	public static final String HOST = "127.0.0.1";
	public static String user = "sa";
	public static String password = "sa123456";
	
	public static String dbURL = "jdbc:sqlserver://" + HOST 
			+ ":1433;databaseName=jspdb";
	
//	public static String dbURL1 = "jdbc:sqlserver://" + HOST 
//			+ ":1433;databaseName=jspdb;user=sa;password=sa123456";

	static {
		System.out.println("dbURL=" + dbURL);
		System.out.println("user=" + user);
		System.out.println("password=" + password);
	}

	public static String getDbURL() {
		return dbURL;
	}

	public static String getUser() {
		return user;
	}

	public static String getPassword() {
		return password;
	}
	
	
}
