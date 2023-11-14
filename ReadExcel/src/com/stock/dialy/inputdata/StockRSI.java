package com.stock.dialy.inputdata;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.stockmarket.algorithms.RSIAlogorithamCalculation;
import com.stockmarket.csv.MySqlConnection;

public class StockRSI {
	
	// fao_participant_oi_
	// F&O paticipant wise open interest 
	
	ArrayList<String> nsescriptcodes = new ArrayList<String>();
	ArrayList<String> nsescriptcodesrsi = new ArrayList<String>();
	
	public static void main(String[] args) {
		
		StockRSI rsi = new StockRSI();
		rsi.data();
		rsi.saveRecords();
		
	}
	
	public void data(){
		
		Connection connection = MySqlConnection.getConnection();

		String query1 = "select nsescriptcode,date from stocksnsedaywisereport1 where date between '2023-09-01' and '2029-12-31' and date not in (select date from stockrsi ) ";
 
		try {

			PreparedStatement statement = connection.prepareStatement(query1);
			ResultSet rs= statement.executeQuery();
			while (rs.next()) {

				nsescriptcodes.add(rs.getString("nsescriptcode")+","+rs.getDate("date"));
				
				//System.out.println(nsescriptcodes);
				/*if(count == 5){
					break;
				}
				count++;*/
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(nsescriptcodes.size());
		//System.out.println(nsescriptcodes);
	}
	
	public void saveRecords(){
		int count = 0;
		
		Connection connection = MySqlConnection.getConnection();
		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
		String closevalue = "select closevalue from stocksnsedaywisereport1 where nsescriptcode= ? and date=?";
		
		for (String code : nsescriptcodes) {

				try {
					RSIAlogorithamCalculation rsi = new RSIAlogorithamCalculation();
					double rsivalue = rsi.stockRSI(code.split(",")[0], code.split(",")[1]);
					nsescriptcodesrsi.add(code+","+rsivalue);
				} catch (Exception e) {
					e.printStackTrace();
					try {
						Date date2 = formatter1.parse(code.split(",")[1]);  
			  			  java.sql.Date insertdate = new java.sql.Date(date2.getTime()); 
						PreparedStatement statement = connection.prepareStatement(closevalue);
						statement.setString(1,code.split(",")[0] );
			    		statement.setDate(2,insertdate );
						ResultSet rs23= statement.executeQuery();
						nsescriptcodesrsi.add(code+","+Double.parseDouble(rs23.getString("closevalue")));
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				System.out.println(code +"-----------------"+count++);
			
		}
		System.out.println(nsescriptcodesrsi.size());
		
    	String queryInsert = "insert into stockrsi (nsescriptcode,rsi,date) values(?,?,?)";
		for (String codes : nsescriptcodesrsi) {
			
			System.out.println(codes);
	    	try {
	    			
	    			Date date2 = formatter1.parse(codes.split(",")[1]);  
	  			  java.sql.Date insertdate = new java.sql.Date(date2.getTime()); 
	  			  
	  			PreparedStatement insertStatement = connection.prepareStatement(queryInsert);
	  			insertStatement.setString(1, codes.split(",")[0]);
	  			insertStatement.setString(2, codes.split(",")[2]);
	  			insertStatement.setDate(3, insertdate);
	  			insertStatement.executeUpdate();
	  			

	    	} catch (Exception e) {
	    		e.printStackTrace();
	    	}
			 
			
			
		}
	}

}
