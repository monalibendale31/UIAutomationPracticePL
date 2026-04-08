package com.ui.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.Test;

import com.ui.constants.Env;

public class PropertiesUtil {

	@Test
	public static String readProperty(Env env,String propertyName )  {
		
		File file=new File(System.getProperty("user.dir") + "\\config\\"+ env +".properties");
		FileInputStream fis=null;
		Properties prop =new Properties();
		
		try {
			fis = new FileInputStream(file);
			prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		String value  =prop.getProperty(propertyName);
		System.out.println(value);
		return value;
	}

	
}
