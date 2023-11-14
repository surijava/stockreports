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

public class DayTrading {
	public static final String FOLDERNAME = "C:/Users/surendra.chebrolu/Downloads/test";

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
					if(file.getName().contains("data")){
						System.out.println(file.getName());
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
		String cvsSplitBy = ",\"";
		List<DayReportPojo> listOfstocks = new ArrayList<DayReportPojo>();
		String[] stocks = null;
		try {

			br = new BufferedReader(new FileReader(FOLDERNAME+"/"+fileName));
			int count = 0;
			while ((line = br.readLine()) != null) {
				DayReportPojo pojo = new DayReportPojo();
				if(count >=2 ){
					
					if(line.contains(cvsSplitBy)){
						stocks = line.split(cvsSplitBy);
					}else{
						stocks = line.split(",");
					}
					for (int i = 0; i < stocks.length; i++) {
						if(i == 3){
							/*System.out.println(stocks[0].replace("\"","")+"--"+stocks[1].replace("\"", "").replace(",","")+"--"+
						stocks[2].replace("\"", "").replace(",","")+"--"+stocks[3].replace("\"", "").replace(",",""));*/
							if(stocks[0] != null){
								pojo.setNseScriptCode(stocks[0].replace("\"", "").replace(",",""));
								pojo.setOpen(stocks[1].replace("\"", "").replace(",",""));
								pojo.setHigh(stocks[2].replace("\"", "").replace(",",""));
								pojo.setLow(stocks[3].replace("\"", "").replace(",",""));
								break;
							}
						}
					}
				}
				listOfstocks.add(pojo);
				count++;
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

		Connection connection = MySqlConnection.getConnection();
		PreparedStatement statement1 = connection.prepareStatement("delete from stocksnsedialytrade ");
		statement1.execute();
		
		String query = "insert into stocksnsedialytrade (nsescriptcode,openvalue,highvalue,lowvalue,closevalue) values(?,?,?,?,?)";
		
		Set keys = dataMap.keySet();
		Iterator it = keys.iterator();
		while (it.hasNext()) {
			String object = (String) it.next();
			List<DayReportPojo> list = dataMap.get(object);
			for (DayReportPojo dayReportPojo : list) {
				if(dayReportPojo.getNseScriptCode() != null){
					PreparedStatement statement = connection.prepareStatement(query);
					statement.setString(1, dayReportPojo.getNseScriptCode());
					statement.setString(2, dayReportPojo.getOpen());
					statement.setString(3, dayReportPojo.getHigh());
					statement.setString(4, dayReportPojo.getLow());
					statement.setString(5, closeValue(dayReportPojo.getNseScriptCode()));
					try {
						statement.executeUpdate();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
			}

		}
	}
	public static String closeValue(String nseCode) throws Exception{
		
		String closeValue = null; 
		Connection connection = MySqlConnection.getConnection();
		String query= "select * from stocksnsedaywisereport1 where nsescriptcode=? order by date desc limit 1";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, nseCode);
		ResultSet set = statement.executeQuery();
		while(set.next()){
			closeValue = set.getString("closevalue")+"-"+set.getString("openvalue");
		}
		
		return closeValue; 
	}
}
