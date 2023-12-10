package utils;

import java.io.FileInputStream;

import java.io.IOException;
import java.util.Properties;

public class ReadProperties {
  
	public static String readfile(String key) throws IOException {
		FileInputStream file =new FileInputStream("src/test/resources/configfile/config.properties");
		Properties p = new Properties();
		p.load(file);
		
		return p.getProperty(key);
		
	}
}
