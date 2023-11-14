package com.stockmarket.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MarginAvailableShares {
	
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
			System.out.println(fileNames);
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
    
		String deletequery = "delete from stockmargin";
		String insertquery = "insert into stockmargin(nsescriptid,marginvalue) values(?,?)";
		
		Connection connection = MySqlConnection.getConnection();
		
		PreparedStatement deletestatement = connection.prepareStatement(deletequery);
		deletestatement.executeUpdate();
		
		
		Set keys = dataMap.keySet();
		Iterator it = keys.iterator();
		
		while (it.hasNext()) {
			
			String filename = (String) it.next();
			List<DayReportPojo> listOfstocks = dataMap.get(filename);
			PreparedStatement statement = connection.prepareStatement(insertquery);
			for (int i = 0; i < listOfstocks.size(); i++) {
				DayReportPojo pojo = listOfstocks.get(i);
				statement.setString(1, pojo.getBaseScripId());
				statement.setString(2, pojo.getBaseScripCode());
				statement.executeUpdate();
			}

		}
		
	}
}
