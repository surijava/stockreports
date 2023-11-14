package com.stockmarket.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class NSEVCSVOperationFull {
	
	public static final String FOLDERNAME = "C:/Users/surendra.chebrolu/Downloads";

	public static List<String> fileNames = new ArrayList<String>();

	public static Map<String,List<DayReportPojo>> dataMap = new HashMap<String,List<DayReportPojo>>();

	public static void main(String[] args) throws Exception {
		readFileNames();
		operation();
		dataBaseOperation(dataMap);
	}

	private static void readFileNames(){

		try {
			File folder = new File(FOLDERNAME);
			File[] listOfFiles = folder.listFiles();
			for (File file : listOfFiles) {
				if (file.isFile()) {
					if(file.getName().contains("EQ")){
						fileNames.add(file.getName());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void operation() throws Exception{

		for (int i = 0; i < fileNames.size(); i++) {
			readFileData(fileNames.get(i));
		}

	}

	public static void readFileData(String fileName) throws Exception{

		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		List<DayReportPojo> listOfstocks = new ArrayList<DayReportPojo>();

		try {

			br = new BufferedReader(new FileReader(FOLDERNAME+"/"+fileName));
			while ((line = br.readLine()) != null) {
				String[] stocks = line.split(cvsSplitBy);
				if(stocks[1].trim().equalsIgnoreCase("EQ")){
					DayReportPojo pojo = new DayReportPojo();
					pojo.setNseScriptCode(stocks[0]);
					pojo.setPreviousClose(stocks[3]);
					pojo.setOpen(stocks[4]);
					pojo.setHigh(stocks[5]);
					pojo.setLow(stocks[6]);
					pojo.setLast(stocks[7]);
					pojo.setClose(stocks[8]);
					pojo.setAverageprice(stocks[9]);
					pojo.setNoshares(stocks[10]);
					pojo.setTurnover(stocks[11]);
					pojo.setNoTrades(stocks[12]);
					pojo.setDeliveryQuantity(stocks[13]);
					pojo.setDeliveryPercentage(stocks[14]);
					listOfstocks.add(pojo);
				}
				
			}
			dataMap.put(fileName, listOfstocks);

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

	public static void dataBaseOperation(Map<String,List<DayReportPojo>> dataMap) throws Exception{

		
		String query = "insert into stocksnsefullreport (nsescriptid,previousclosevalue,"
				+ "openvalue,highvalue,lowvalue,lastpricevalue,closepricevalue,averagepricevalue"
				+ ",noofshares,turnover,nooftrades,deliveryshares,deliverypercentage,date) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Connection connection = MySqlConnection.getConnection();
		
		Set keys = dataMap.keySet();
		Iterator it = keys.iterator();

		SimpleDateFormat formatter1 = new SimpleDateFormat("ddMMyy");  

		while (it.hasNext()) {

			int count = 0 ;

			String filename = (String) it.next();
			  Date date2 = formatter1.parse(filename.replace("EQ","").replace(".CSV",""));  
			  java.sql.Date insertdate = new java.sql.Date(date2.getTime()); 
			 
			
			PreparedStatement statement1 = connection.prepareStatement("select count(*) from "
					+ "stocksnsedaydata where date= ?");
			statement1.setDate(1, insertdate);
			ResultSet rs= statement1.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
				 if(!(dataMap.get(filename).size() == count)){
					 PreparedStatement statement = connection.prepareStatement("delete from stocksnsedaydata "
					 		+ "where date= ?");
					 statement.setDate(1, insertdate);
					 statement.executeUpdate();
					 count = 0;
				 }
			}

			if(count == 0){

				System.out.println("Processing File is "+filename);
				List<DayReportPojo> listOfstocks = dataMap.get(filename);
				PreparedStatement statement = connection.prepareStatement(query);
				for (int i = 0; i < listOfstocks.size(); i++) {
					DayReportPojo pojo = listOfstocks.get(i);
					statement.setString(1, pojo.getNseScriptCode());
					statement.setString(2, pojo.getPreviousClose());
					statement.setString(3, pojo.getOpen());
					statement.setString(4, pojo.getHigh());
					statement.setString(5, pojo.getLow());
					statement.setString(6, pojo.getLast());
					statement.setString(7, pojo.getClose());
					statement.setString(8, pojo.getAverageprice());
					statement.setString(9, pojo.getNoshares());
					statement.setString(10, pojo.getTurnover());
					statement.setString(11, pojo.getNoTrades());
					statement.setString(12, pojo.getDeliveryQuantity());
					statement.setString(13, pojo.getDeliveryPercentage());
					statement.setDate(14, insertdate);
					statement.executeUpdate();
				}
			}else{
				System.out.println("file data exists in DB "+filename);
			}
		}
	}


}
