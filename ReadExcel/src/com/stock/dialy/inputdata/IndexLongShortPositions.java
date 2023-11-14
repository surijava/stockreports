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

import com.stockmarket.csv.DayReportPojo;
import com.stockmarket.csv.FiiDiiOpenInterest;
import com.stockmarket.csv.MySqlConnection;

public class IndexLongShortPositions {

	public static final String FOLDERNAME = "C:\\Surendra\\stockreports\\positions";

	public static List<String> fileNames = new ArrayList<String>();

	public static Map<String,List<FiiDiiOpenInterest>> dataMap = new HashMap<String,List<FiiDiiOpenInterest>>();

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
		List<FiiDiiOpenInterest> listOfstocks = new ArrayList<FiiDiiOpenInterest>();
		System.out.println(fileName+"------------------------");
		try {

			br = new BufferedReader(new FileReader(FOLDERNAME+"/"+fileName));
			//System.out.println(FOLDERNAME+"/"+fileName);
			int count =0; 
			while ((line = br.readLine()) != null) {
				//System.out.println(line);
				if(count > 1){
					
					String[] stocks = line.split(cvsSplitBy);
					//listOfstocks.add(line);
					FiiDiiOpenInterest pojo = new FiiDiiOpenInterest();
					pojo.setClienttype(stocks[0]);
					pojo.setFutureindexlong(stocks[1]);
					pojo.setFutureindexshort(stocks[2]);
					pojo.setFuturestocklong(stocks[3]);
					pojo.setFuturestockshort(stocks[4]);
					pojo.setOptionindexcalllong(stocks[5]);
					pojo.setOptionindexputlong(stocks[6]);
					pojo.setOptionindexcallshort(stocks[7]);
					pojo.setOptionindexputshort(stocks[8]);
					pojo.setOptionstockcalllong(stocks[9]);					
					pojo.setOptionstockputlog(stocks[10]);
					pojo.setOptionstockcallshort(stocks[11]);
					pojo.setOptionstockputshort(stocks[12]);
					pojo.setTotallongcontracts(stocks[13]);
					pojo.setTotalshortcontracts(stocks[14]);
					listOfstocks.add(pojo);
				}
				count++; 
			}
			dataMap.put(fileName, listOfstocks);
			//System.out.println(dataMap);
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
	
public static void dataBaseOperation(Map<String,List<FiiDiiOpenInterest>> dataMap) throws Exception{

		
		String query = "insert into fiidiiopeninterest (clienttype,futureindexlong,futureindexshort"
				+ ",futurestocklong,futurestockshort,optionindexcalllong,optionindexcallshort,"
				+ "optionindexputlong,optionindexputshort,"
				+ "optionstockcalllong,optionstockcallshort,optionstockputlog,"
				+ "optionstockputshort,totallongcontracts,totalshortcontracts,date) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Connection connection = MySqlConnection.getConnection();
		
		Set keys = dataMap.keySet();
		Iterator it = keys.iterator();

		SimpleDateFormat formatter1 = new SimpleDateFormat("ddMMyyyy");  

		while (it.hasNext()) {

			int count = 0 ;

			String filename = (String) it.next();
			  Date date2 = formatter1.parse(filename.replace("fao_participant_oi_","").replace(".csv",""));  
			  java.sql.Date insertdate = new java.sql.Date(date2.getTime()); 
			PreparedStatement statement1 = connection.prepareStatement("select count(*) from "
					+ "fiidiiopeninterest where date= ?");
			statement1.setDate(1, insertdate);
			ResultSet rs= statement1.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
				 if(!(dataMap.get(filename).size() == count)){
					 PreparedStatement statement = connection.prepareStatement("delete from fiidiiopeninterest "
					 		+ "where date= ?");
					 statement.setDate(1, insertdate);
					 statement.executeUpdate();
					 count = 0;
				 }
			}

			if(count == 0){
				
				System.out.println("Processing File is "+filename);
				List<FiiDiiOpenInterest> listOfstocks = dataMap.get(filename);
				PreparedStatement statement = connection.prepareStatement(query);
				for (int i = 0; i < listOfstocks.size(); i++) {
					FiiDiiOpenInterest pojo = listOfstocks.get(i);
					statement.setString(1, pojo.getClienttype());
					statement.setString(2, pojo.getFutureindexlong());
					statement.setString(3, pojo.getFutureindexshort());
					statement.setString(4, pojo.getFuturestocklong());
					statement.setString(5, pojo.getFuturestockshort());
					statement.setString(6, pojo.getOptionindexcalllong());
					statement.setString(7, pojo.getOptionindexcallshort());
					statement.setString(8, pojo.getOptionindexputlong());
					statement.setString(9, pojo.getOptionindexputshort());
					statement.setString(10, pojo.getOptionstockcalllong());
					statement.setString(11, pojo.getOptionstockcallshort());
					statement.setString(12, pojo.getOptionstockputlog());
					statement.setString(13, pojo.getOptionstockputshort());
					statement.setString(14, pojo.getTotallongcontracts());
					statement.setString(15, pojo.getTotalshortcontracts());
					statement.setDate(16, insertdate);
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
