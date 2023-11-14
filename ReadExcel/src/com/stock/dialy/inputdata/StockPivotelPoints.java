package com.stock.dialy.inputdata;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.stockmarket.csv.DayReportPojo;
import com.stockmarket.csv.MySqlConnection;

public class StockPivotelPoints {
	
	public static final String FOLDERNAME = "C:\\Surendra\\stockreports\\report3";

	public static List<String> fileNames = new ArrayList<String>();

	public static Map<String,List<DayReportPojo>> dataMap = new HashMap<String,List<DayReportPojo>>();

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
					if(file.getName().contains("cm")){
						fileNames.add(file.getName());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void operation() throws Exception{

		for (int i = 0; i < fileNames.size(); i++) {
			readFileData(fileNames.get(i));
		}

	}

	public static void readFileData(String fileName) throws Exception{

		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		List<DayReportPojo> listOfstocks = new ArrayList<DayReportPojo>();
		System.out.println(fileName+"------------------------");
		try {

			br = new BufferedReader(new FileReader(FOLDERNAME+"/"+fileName));
			System.out.println(FOLDERNAME+"/"+fileName);
			while ((line = br.readLine()) != null) {
				String[] stocks = line.split(cvsSplitBy);
				if(stocks[1].trim().equalsIgnoreCase("EQ") || stocks[1].trim().equalsIgnoreCase("BE") ){
					DayReportPojo pojo = new DayReportPojo();
					pojo.setNseScriptCode(stocks[0]);
					pojo.setSeries(stocks[1]);
					pojo.setOpen(stocks[2]);
					pojo.setHigh(stocks[3]);
					//System.out.println(stocks[3]);
					pojo.setLow(stocks[4]);
					pojo.setClose(stocks[5]);
					pojo.setLast(stocks[6]);
					pojo.setPreviousClose(stocks[7]);
					pojo.setNoshares(stocks[8]);
					pojo.setTurnover(stocks[9]);
					pojo.setNoTrades(stocks[11]);
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

		
		String query = "insert into stockpivotel (nsescriptcode,"
				+ "pivotelpoint,resistanceone,resistancetwo,resistancethree,supportone,supporttwo,supportthree"
				+ ",openvalue,highvalue,lowvalue,closevalue,date) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Connection connection = MySqlConnection.getConnection();
		
		Set keys = dataMap.keySet();
		Iterator it = keys.iterator();

		SimpleDateFormat formatter1 = new SimpleDateFormat("ddMMMyy");  

		while (it.hasNext()) {

			int count = 0 ;

			String filename = (String) it.next();
			  Date date2 = formatter1.parse(filename.replace("cm","").replace("bhav","").replace(".csv",""));  
			  java.sql.Date insertdate = new java.sql.Date(date2.getTime()); 
			PreparedStatement statement1 = connection.prepareStatement("select count(*) from "
					+ "stockpivotel where date= ?");
			statement1.setDate(1, insertdate);
			ResultSet rs= statement1.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
				 if(!(dataMap.get(filename).size() == count)){
					 PreparedStatement statement = connection.prepareStatement("delete from stockpivotel "
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
				double pivotel = 0.0;
				double r1 = 0.0;
				double r2 = 0.0;
				double r3 = 0.0;
				double s1 = 0.0;
				double s2 = 0.0;
				double s3 = 0.0;
				double high = 0.0;
				double close = 0.0;
				double low = 0.0;
				double open = 0.0;
				DecimalFormat df = new DecimalFormat("####0.00");
				for (int i = 0; i < listOfstocks.size(); i++) {
					
					DayReportPojo pojo = listOfstocks.get(i); 
					open = Double.parseDouble(pojo.getOpen()) ;
					high = Double.parseDouble(pojo.getHigh()) ;
					low = Double.parseDouble(pojo.getLow()) ;
					close = Double.parseDouble(pojo.getClose()) ;
					
					pivotel = (high+low+close)/3;
					
					r1 = pivotel + (pivotel-low);
					r2 = pivotel + (high -low);
					r3 = high + 2 *( pivotel -low);
					
					s1 = pivotel - (high-pivotel);
					s2 = pivotel - (high-low);
					s3 = low - 2 * (high-pivotel);
					
					
					statement.setString(1, pojo.getNseScriptCode());
					statement.setString(2, df.format(pivotel));
					statement.setString(3, df.format(r1));
					//System.out.println("-----"+pojo.getHigh());
					statement.setString(4,df.format(r2) );
					statement.setString(5, df.format(r3));
					statement.setString(6, df.format(s1));
					statement.setString(7, df.format(s2));
					statement.setString(8, df.format(s3));
					statement.setString(9, df.format(open));
					statement.setString(10, df.format(high));
					statement.setString(11, df.format(low));
					statement.setString(12, df.format(close));
					statement.setDate(13, insertdate);
					try {
						statement.executeUpdate();
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					 pivotel = 0.0;
					 r1 = 0.0;
					 r2 = 0.0;
					 r3 = 0.0;
					 s1 = 0.0;
					 s2 = 0.0;
					 s3 = 0.0;
					 high = 0.0;
					 close = 0.0;
					 low = 0.0;
					 open= 0.0;
				}
			}else{
				System.out.println("file data exists in DB "
						+ "  "+filename);
			}
		}
	}

}
