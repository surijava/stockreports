package com.stockmarket.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Trand {
	public static final String FOLDERNAME = "C:/Users/surendra.chebrolu/Downloads/test";

	public static List<String> fileNames = new ArrayList<String>();

	public static Map<Integer,DayReportPojo> dataMap = new HashMap<Integer,DayReportPojo>();

	public static Connection connection = MySqlConnection.getConnection();

	public static List<String> longbuy = new ArrayList<String>();
	public static List<String> longsell = new ArrayList<String>();

	public static void main(String[] args) throws Exception {
		readFileNames();
		operation();
		dbOprations(dataMap);
	}

	private static void readFileNames(){

		try {
			File folder = new File(FOLDERNAME);
			File[] listOfFiles = folder.listFiles();
			for (File file : listOfFiles) {
				if (file.isFile()) {
					fileNames.add(file.getName());
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
		String cvsSplitBy = "\",\"";

		try {

			br = new BufferedReader(new FileReader(FOLDERNAME+"/"+fileName));
			int count = 0;

			while ((line = br.readLine()) != null) {
				if(count >=2 ){
					DayReportPojo pojo = new DayReportPojo();
					String[] stocks = line.split(cvsSplitBy);
					for (int i = 0; i < stocks.length; i++) {
						if(i == 3){
							//System.out.println(stocks[0].replace("\"","")+"--"+stocks[1].replace(",", "")+"--"+stocks[2].replace(",", "")+"--"+stocks[3].replace(",", ""));
							if(stocks[0] != null){
								pojo.setNseScriptCode(stocks[0].replace("\"",""));
								pojo.setOpen(stocks[1].replace(",", ""));
								pojo.setHigh(stocks[2].replace(",", ""));
								pojo.setLow(stocks[3].replace(",", ""));
								break;
							}
						}
					}
					dataMap.put(count, pojo);

				}
				count++;
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
		//System.out.println(dataMap);
	}

	public static void dbOprations(Map<Integer,DayReportPojo> dataMap) throws Exception{

		
		String query = "insert into stocksnsedialytrade (nsescriptcode,openvalue,highvalue,lowvalue) values(?,?,?,?)";
		Connection connection = MySqlConnection.getConnection();
		PreparedStatement statement1 = connection.prepareStatement("delete from stocksnsedialytrade ");
		statement1.execute();
		
		System.out.println(dataMap);
		Set keys = dataMap.keySet();
		Iterator it = keys.iterator();
		
		int count = 2 ;
		while (it.hasNext()) {
			
			DayReportPojo pojo = dataMap.get(count);
			System.out.println(count);
			if(pojo.getNseScriptCode().trim() != null ){
				PreparedStatement statement = connection.prepareStatement(query);
				statement.setString(1, pojo.getNseScriptCode());
				statement.setString(2, pojo.getOpen());
				statement.setString(3, pojo.getHigh());
				statement.setString(4, pojo.getLow());
				try {
					statement.executeUpdate();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			count++;
			
		}


	}


}
