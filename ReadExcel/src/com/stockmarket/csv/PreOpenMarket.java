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

public class PreOpenMarket {

	public static final String FOLDERNAME = "C:/Users/surendra.chebrolu/Downloads/test";

	public static List<String> fileNames = new ArrayList<String>();

	public static Map<String,List<DayReportPojo>> dataMap = new HashMap<String,List<DayReportPojo>>();
	
	
	public static List<String> longbuy = new ArrayList<String>();
	public static List<String> longsell = new ArrayList<String>();

	public static void main(String[] args) throws Exception {
		readFileNames();
		operation();
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
				System.out.println(line);
				if(count >=2 ){

					String[] stocks = line.split(cvsSplitBy);
					for (int i = 0; i < stocks.length; i++) {

						if(i == 3){
							if(Float.parseFloat(stocks[1].replace(",","")) == Float.parseFloat(stocks[2].replace(",",""))){
								System.out.println(stocks[0].replace("\"","")+"--- sell");
								longsell.add(stocks[0].replace("\"",""));
							}

							if(Float.parseFloat(stocks[1].replace(",","")) == Float.parseFloat(stocks[3].replace(",",""))){
								System.out.println(stocks[0].replace("\"","")+"--- buy");
								longbuy.add(stocks[0].replace("\"",""));
							}

							break;

						}
					}

				}
				count++;

			}
			//dataMap.put(fileName, listOfstocks);

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

	

}
