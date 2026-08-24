package utilities;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	 private static final Properties properties = new Properties();
	 
	 private static final Logger logger =
		        LogManager.getLogger(ConfigReader.class);

	 
	 static
	 {
		 try {
			 FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
			 properties.load(fis);
			 logger.info("Configuration file loaded successfully");


		    } catch (IOException e) {
		    	
		    	logger.error("Failed to load config.properties", e);
		    	
		    	throw new RuntimeException("Failed to load config.properties", e);
		    }
	 }
	 public static String getBrowser() {
		    return properties.getProperty("browser");
		}

		public static String getUrl() {
		    return properties.getProperty("url");
		}

		/*public static int getTimeout() {
		    return Integer.parseInt(properties.getProperty("timeout"));
		}
		
		public static String getTestDataPath() {
		    return properties.getProperty("testdata.path");
		}*/
}
