package common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadPropertiesFile {
	private static Properties getProperties(String propName) {
		Properties prop = new Properties();
		InputStream in = Object.class.getResourceAsStream("/"+propName);
		try {
			prop.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	
	
	public static String getSql(String sqlName){
		String sqlContent=getProperties("sql.properties").getProperty(sqlName);
		if (sqlContent==null||"".equals(sqlContent)||sqlContent.equals(null)) {
			return "SELECT 1+1 FROM DUAL";
		}else {
			return sqlContent;
		}
	}
	
	public static void main(String args[]){
		Properties prop  = ReadPropertiesFile.getProperties("druid-pool.properties");
		System.out.print(prop.getProperty("url"));
	}

}
