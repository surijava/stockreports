package com.stockmarket.algorithms;

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

public class PivotelBreakOut {

	public static final String FOLDERNAME = "F:\\reports3";

	static ArrayList<String> supportone = new ArrayList<String>();
	static ArrayList<String> supporttwo = new ArrayList<String>();
	static ArrayList<String> supportthree = new ArrayList<String>();
	static ArrayList<String> resistanceone = new ArrayList<String>();
	static ArrayList<String> resistancetwo = new ArrayList<String>();
	static ArrayList<String> resistancethree = new ArrayList<String>();

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
					if(file.getName().contains("cm")){
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
		System.out.println(fileName+"------------------------");
		try {

			br = new BufferedReader(new FileReader(FOLDERNAME+"/"+fileName));
			System.out.println(FOLDERNAME+"/"+fileName);
			while ((line = br.readLine()) != null) {
				String[] stocks = line.split(cvsSplitBy);
				if(stocks[1].trim().equalsIgnoreCase("EQ")){
					DayReportPojo pojo = new DayReportPojo();
					pojo.setNseScriptCode(stocks[0]);
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

		Set keys = dataMap.keySet();
		Iterator it = keys.iterator();
		while (it.hasNext()) {

			String filename = (String) it.next();


			List<DayReportPojo> listOfstocks = dataMap.get(filename);
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

				if(high > r1 ){

					resistanceone.add(pojo.getNseScriptCode());
				}

				if(high > r2 ){

					resistancetwo.add(pojo.getNseScriptCode());
				}

				if(high > r3 ){

					resistancethree.add(pojo.getNseScriptCode());
				}
				
				if(low < s1){
					supportone.add(pojo.getNseScriptCode());
				}

				if(low < s2){
					supporttwo.add(pojo.getNseScriptCode());
				}
				
				if(low < s3){
					supportthree.add(pojo.getNseScriptCode());
				}


			}
		}
		
		System.out.println(resistanceone);
		System.out.println(resistancetwo);
		System.out.println(resistancethree);
		System.out.println(supportone);
		System.out.println(supporttwo);
		System.out.println(supportthree);
		
	}	

}
