package edu.kh.jdbc.common;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.Scanner;

public class LoadXMLFile {
	public static void main(String[] args) {
		
		// XML 파일 읽어오기(Properties, FileInputStream)
		
		try {
			Properties prop = new Properties();
			
			// driver.xml 파일을 읽어오기 위한 InputStream 객체 생성
			FileInputStream fis = new FileInputStream("driver.xml");
			
			// 연결된 driver.xml 파일에 있는 내용을 모두 읽어와
			// Properties 객체에 K:V 형식으로 저장
			prop.loadFromXML(fis);
			
			System.out.println(prop);
			
			// Property : 속성(데이터)
			
			// prop.getProperty("key") : key가 일치하는 속성(데이터)를 얻어옴
			
			String driver = prop.getProperty("driver");
			String url = prop.getProperty("url");
			String user = prop.getProperty("user");
			String password = prop.getProperty("password");
			
			System.out.println();
			
			// driver.xml 파일에서 읽어온 값들을 이용해 Connection 생성
			Class.forName(driver); // oracle.jdbc.driver.OracleDriver
			Connection conn = DriverManager.getConnection(url, user, password);
			
			System.out.println(conn);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
}
