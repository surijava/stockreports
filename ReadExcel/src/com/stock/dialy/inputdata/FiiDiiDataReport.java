package com.stock.dialy.inputdata;

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

import com.stockmarket.csv.FiiDiiDataPojo;
import com.stockmarket.csv.FiiDiiOpenInterest;
import com.stockmarket.csv.MySqlConnection;

public class FiiDiiDataReport {



	public static final String FOLDERNAME = "C:\\Surendra\\stockreports\\fiidiidata";

	public static List<String> fileNames = new ArrayList<String>();

	public static Map<String,List<FiiDiiDataPojo>> dataMap = new HashMap<String,List<FiiDiiDataPojo>>();

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
		String cvsSplitBy = ",";
		List<FiiDiiDataPojo> listOfstocks = new ArrayList<FiiDiiDataPojo>();
		System.out.println(fileName+"------------------------");
		try {

			br = new BufferedReader(new FileReader(FOLDERNAME+"/"+fileName));
			System.out.println(FOLDERNAME+"/"+fileName);
			int count =0; 
			while ((line = br.readLine()) != null) {

				if(count > 5){
					String lineone  = line.replace("*", "").replace("\"","");
					String[] stocks = lineone.split(cvsSplitBy);

					FiiDiiDataPojo pojo = new FiiDiiDataPojo();
					pojo.setCategory(stocks[0].trim());
					pojo.setDate(stocks[1]);
					pojo.setBuyValue(stocks[2]);
					pojo.setSellValue(stocks[3]);
					pojo.setNetValue(stocks[4]);
					listOfstocks.add(pojo);
				}
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

	public static void dataBaseOperation(Map<String,List<FiiDiiDataPojo>> dataMap) throws Exception{


		String query = "insert into fiidiidatareport (category,buyvalue,sellvalue,netvalue,date) values(?,?,?,?,?)";
		Connection connection = MySqlConnection.getConnection();

		Set keys = dataMap.keySet();
		Iterator it = keys.iterator();

		SimpleDateFormat formatter1 = new SimpleDateFormat("ddMMyyyy");  

		SimpleDateFormat formatter2 = new SimpleDateFormat("ddMMMyy");  

		while (it.hasNext()) {

			int count = 0 ;

			String filename = (String) it.next();

			Date date2 = formatter1.parse(filename.replace("fii-dii-latest","").replace(".csv",""));  
			java.sql.Date insertdate = new java.sql.Date(date2.getTime()); 

			PreparedStatement statement1 = connection.prepareStatement("select count(*) from "
					+ "fiidiidatareport where date= ?");
			statement1.setDate(1, insertdate);
			ResultSet rs= statement1.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
				if(!(dataMap.get(filename).size() == count)){
					PreparedStatement statement = connection.prepareStatement("delete from fiidiidatareport "
							+ "where date= ?");
					statement.setDate(1, insertdate);
					statement.executeUpdate();
					count = 0;
				}
			}

			if(count == 0){

				System.out.println("Processing File is "+filename);
				List<FiiDiiDataPojo> listOfstocks = dataMap.get(filename);
				PreparedStatement statement = connection.prepareStatement(query);
				for (int i = 0; i < listOfstocks.size(); i++) {
					FiiDiiDataPojo pojo = listOfstocks.get(i);
					statement.setString(1, pojo.getCategory());
					statement.setString(2, pojo.getBuyValue());
					statement.setString(3, pojo.getSellValue());
					statement.setString(4, pojo.getNetValue());
					
					/*String date = pojo.getDate().replace("-","");
					Date date21 = formatter2.parse(date);  
					
					java.sql.Date insertdate1 = new java.sql.Date(date21.getTime());*/ 
					statement.setDate(5, insertdate);

					try {
						statement.executeUpdate();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}else{
				System.out.println("file data exists in DB "
						+ "  "+filename);
			}
		}
	}





}
