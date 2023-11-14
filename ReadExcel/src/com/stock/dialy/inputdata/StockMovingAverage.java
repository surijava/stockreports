package com.stock.dialy.inputdata;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.stockmarket.csv.DayReportPojo;
import com.stockmarket.csv.MySqlConnection;
import com.readexcel.util.StockUtililty;

public class StockMovingAverage {


	public static final String FOLDERNAME = "C:\\Surendra\\stockreports\\report3";

	public static List<String> fileNames = new ArrayList<String>();
	
	public static Set<String> nseScriptNames = new TreeSet<String>();

	public static Map<String,DayReportPojo> dataMap = new HashMap<String,DayReportPojo>();

	public static void main(String[] args) throws Exception {
		readFileNames();
		operation();
		test(nseScriptNames);
		data();
	}

	
	
	public static void test(Set nseScriptsCodes) throws Exception {

		for (Object nseScript : nseScriptNames) {
			
			System.out.println(nseScript+"");
			DayReportPojo pojo = new DayReportPojo();
			pojo.setNseScriptCode(nseScript.toString());
			pojo.setTenma(getNseScriptsMA(nseScript.toString().trim(),10));
			pojo.setTwentyma(getNseScriptsMA(nseScript.toString().trim(),25));
			pojo.setFiftyma(getNseScriptsMA(nseScript.toString().trim(),50));
			pojo.setHudredma(getNseScriptsMA(nseScript.toString().trim(),100));
			pojo.setTwofiftyma(getNseScriptsMA(nseScript.toString().trim(),250));
			
			dataMap.put(nseScript.toString(), pojo);
		}
		System.out.println("preparation completed");
		
	}
	
	public static void data() throws Exception{
		
		String query = "insert into stockmovingaverage (nsescriptcode,"
				+ "10daysaverage,25daysaverage,50daysaverage,100daysaverage,200daysaverage,date) "
				+ "values(?,?,?,?,?,?,?)";
		
		Connection connection = MySqlConnection.getConnection();
		LocalDateTime ldt = LocalDateTime.now();		
		String currentDay = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH).format(ldt);

		java.sql.Date  currentDate= new java.sql.Date(StockUtililty.dateFormat(currentDay).getTime());

		
		for (String nseScriptName : dataMap.keySet()) {
			System.out.println(nseScriptName);
			
			DayReportPojo pojo = dataMap.get(nseScriptName);
			
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, pojo.getNseScriptCode());
			statement.setDouble(2, pojo.getTenma());
			statement.setDouble(3, pojo.getTwentyma());
			statement.setDouble(4, pojo.getFiftyma());
			statement.setDouble(5, pojo.getHudredma());
			statement.setDouble(6, pojo.getTwofiftyma());
			statement.setDate(7, currentDate);
			statement.executeUpdate();
			
			
			}
			
			
		}
		
		

	
	public static Double getNseScriptsMA(String nsescriptCode,int period) throws Exception{

		Connection connection = MySqlConnection.getConnection();
		double count = 0.00;
		String query = "SELECT * FROM stockmarket.stocksnsedaywisereport1 where nsescriptcode =? order by date desc";  
		
		try {
			PreparedStatement statementDates = connection.prepareStatement(query);
			
			statementDates.setString(1, nsescriptCode);
			ResultSet rs= statementDates.executeQuery();
			
			int rows = 0; 
			while (rs.next()) {
				//System.out.println(rs.getString("closevalue") +"---------"+rs.getString("date"));
				
				if(rows < period){
					count = count + Double.parseDouble(rs.getString("closevalue"));
				}else{
					break;
				}
				rows++;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count/period;
	}


	public static List<Double> getNseScripts(String nsescriptCode,int period) throws Exception{

		Connection connection = MySqlConnection.getConnection();
		
		List<Double> scriptCloseValues = new ArrayList<Double>();
		
		double count = 0.00;
		LocalDateTime ldt = LocalDateTime.now();
		//int period = 7;

		String currentDay = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH).format(ldt);

		java.sql.Date sixDatePreviousDate = new java.sql.Date(StockUtililty.
				daysAfter(currentDay,period).getTime()); 
		java.sql.Date  currentDate= new java.sql.Date(StockUtililty.
				daysBefore(currentDay,period).getTime());
		

		String query = "SELECT * FROM stockmarket.stocksnsedaywisereport1 where nsescriptcode =? and  date between ? and ? order by date desc";  
		
		
		
		try {
			PreparedStatement statementDates = connection.prepareStatement(query);
			
			statementDates.setString(1, nsescriptCode);
			statementDates.setDate(2, currentDate);
			statementDates.setDate(3, sixDatePreviousDate);
			ResultSet rs= statementDates.executeQuery();
			
			while (rs.next()) {
				//System.out.println(rs.getString("closevalue") +"---------"+rs.getString("date"));
				count = count + Double.parseDouble(rs.getString("closevalue"));
			}
			scriptCloseValues.add(count/period);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return scriptCloseValues;
	}

	public static void readFileNames(){

		try {
			File folder = new File(FOLDERNAME);
			File[] listOfFiles = folder.listFiles();
			for (File file : listOfFiles) {
				if (file.isFile()) {
					if(file.getName().contains("cm")){
						fileNames.add(file.getName());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public  static void operation() throws Exception{

			readFileData(fileNames.get(0));

	}

	public static void readFileData(String fileName) throws Exception{

		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		try {

			br = new BufferedReader(new FileReader(FOLDERNAME+"/"+fileName));
			while ((line = br.readLine()) != null) {
				String[] stocks = line.split(cvsSplitBy);
				if(stocks[1].trim().equalsIgnoreCase("EQ")){
					nseScriptNames.add(stocks[0]);
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}




}

