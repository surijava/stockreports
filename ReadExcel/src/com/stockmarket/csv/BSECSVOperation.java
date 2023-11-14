package com.stockmarket.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BSECSVOperation {

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
				DayReportPojo pojo = new DayReportPojo();
				pojo.setBaseScripId(stocks[0]);
				pojo.setBaseScripCode(stocks[1]);
				pojo.setScGroup(stocks[2]);
				pojo.setScType(stocks[3]);
				pojo.setOpen(stocks[4]);
				pojo.setHigh(stocks[5]);
				pojo.setLow(stocks[6]);
				pojo.setClose(stocks[7]);
				pojo.setLast(stocks[8]);
				pojo.setPreviousClose(stocks[9]);
				pojo.setNoTrades(stocks[10]);
				pojo.setNoshares(stocks[11]);
				pojo.setTurnover(stocks[12]);
				if(stocks.length == 14){
					pojo.setTDCLOINDI(stocks[13]);
				}
				listOfstocks.add(pojo);
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

		
		String query = "insert into stocksdaydata (bsescriptid,bsescriptcode,"
				+ "scgroup,sctype,openvalue,highvalue,lowvalue,closevalue,lastvalue,"
				+ "previousclosevalue,nooftrades,noofshares,turnover,tdcloindi,date) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
					+ "stocksdaydata where date= ?");
			statement1.setDate(1, insertdate);
			ResultSet rs= statement1.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
				 if(!(dataMap.get(filename).size() == count)){
					 PreparedStatement statement = connection.prepareStatement("delete from stocksdaydata "
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
					statement.setString(1, pojo.getBaseScripId());
					statement.setString(2, pojo.getBaseScripCode());
					statement.setString(3, pojo.getScGroup());
					statement.setString(4, pojo.getScType());
					statement.setString(5, pojo.getOpen());
					statement.setString(6, pojo.getHigh());
					statement.setString(7, pojo.getLow());
					statement.setString(8, pojo.getClose());
					statement.setString(9, pojo.getLast());
					statement.setString(10, pojo.getPreviousClose());
					statement.setString(11, pojo.getNoTrades());
					statement.setString(12, pojo.getNoshares());
					statement.setString(13, pojo.getTurnover());
					statement.setString(14, pojo.getTDCLOINDI());
					statement.setDate(15, insertdate);
					statement.executeUpdate();
				}
			}else{
				System.out.println("file data exists in DB "+filename);
			}
		}
	}


}
