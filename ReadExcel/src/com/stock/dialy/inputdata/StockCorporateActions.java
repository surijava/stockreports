package com.stock.dialy.inputdata;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.stockmarket.csv.BseEventsPojo;
import com.stockmarket.csv.DayReportPojo;
import com.stockmarket.csv.MySqlConnection;

public class StockCorporateActions {

	public static final String FOLDERNAME = "C:\\Surendra\\stockreports\\corporateactions";

	public static List<String> fileNames = new ArrayList<String>();

	public static Map<String,List<BseEventsPojo>> dataMap = new HashMap<String,List<BseEventsPojo>>();

	public static void main(String[] args) throws Exception {
		readFileNames();
		operation();
		dataBaseOperation(dataMap);
	}

	public static void readFileNames(){

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

	public  static void operation() throws Exception{

		for (int i = 0; i < fileNames.size(); i++) {
			readFileData(fileNames.get(i));
		}

	}

	public static void readFileData(String fileName) throws Exception{

		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		List<BseEventsPojo> listOfstocks = new ArrayList<BseEventsPojo>();
		System.out.println(fileName+"------------------------");
		try {

			br = new BufferedReader(new FileReader(FOLDERNAME+"/"+fileName));
			System.out.println(FOLDERNAME+"/"+fileName);
			int count = 0;
			while ((line = br.readLine()) != null) {
				
				if (count != 0 && line.contains("Quarterly")){
				
				String[] stocks = line.trim().split(cvsSplitBy);
				BseEventsPojo pojo = new BseEventsPojo();
				pojo.setCode(stocks[0]);
				pojo.setNseScriptCode(stocks[1]);
				pojo.setIndustry(stocks[2]);
				pojo.setPurpose(stocks[3]);
				pojo.setMeetingDate(stocks[4]);
				pojo.setAnnounceDate(stocks[5]);
				listOfstocks.add(pojo);
				}
				count ++; 
				
			}
			dataMap.put(fileName, listOfstocks);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
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

	public static void dataBaseOperation(Map<String,List<BseEventsPojo>> dataMap) throws Exception{

		
		String query = "insert into bseevents (code,nsescriptcode,industry,purpose,meetingdate,announcedate) values(?,?,?,?,?,?)";
		Connection connection = MySqlConnection.getConnection();
		
		Set keys = dataMap.keySet();
		Iterator it = keys.iterator();

		SimpleDateFormat formatter1 = new SimpleDateFormat("dd MMM yyyy");  

		DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy hh:mm:ss a");
		
		
		while (it.hasNext()) {


			String filename = (String) it.next();
			
			 // Date date2 = formatter1.parse(filename.replace("cm","").replace("bhav","").replace(".csv",""));  
			 // java.sql.Date insertdate = new java.sql.Date(date2.getTime()); 
				
				System.out.println("Processing File is "+filename);
				List<BseEventsPojo> listOfstocks = dataMap.get(filename);
				PreparedStatement statement = connection.prepareStatement(query);
				for (int i = 0; i < listOfstocks.size(); i++) {
					BseEventsPojo pojo = listOfstocks.get(i);
					statement.setString(1, pojo.getCode());
					statement.setString(2, pojo.getNseScriptCode());
					
					statement.setString(3, pojo.getIndustry());
					//System.out.println("-----"+pojo.getHigh());
					statement.setString(4, pojo.getPurpose());
					
					Date mDate = formatter1.parse(pojo.getMeetingDate());
					java.sql.Date insertdate = new java.sql.Date(mDate.getTime());
					statement.setDate(5, insertdate);
					
					statement.setString(6, pojo.announceDate);
					
					try {
						statement.executeUpdate();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			
		}
	}

}
