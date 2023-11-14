package com.stockmarket.algorithms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;


import com.stockmarket.csv.MySqlConnection;

public class RSIAlogorithamCalculation {
	
	HashMap<String,Double> dataMap = new HashMap<String,Double>();
	ArrayList<Double> closeValues = new ArrayList<Double>();
	ArrayList<Double> changeValues = new ArrayList<Double>();
	ArrayList<Double> gainValues = new ArrayList<Double>();
	ArrayList<Double> lossValues = new ArrayList<Double>();
	ArrayList<String> nsescriptcodes = new ArrayList<String>();
	double avgGain = 0.0;
	double avgLoss =0.0;
	double rsValue = 0.0;
	
	double stockChange = 0.0;
	double stockGain =0.0;
	double stockLoss =0.0;
	double stockAvgGain =0.0;
	double stockAvgLoss =0.0;
	double stockRSI =0.0;
	
	

    public static void main(String[] args) {
		
    	RSIAlogorithamCalculation rsi = new RSIAlogorithamCalculation();
    	rsi.getData("zomato","2023-09-06");
    	rsi.calculateValues();
    	  
	}
    
    public double stockRSI(String nsescriptCode,String date){
    	
    	getData(nsescriptCode,date);
    	return calculateValues();
    	
    }
    
    
    public void getData(String nsescriptCode,String date){
    	
    	Connection connection = MySqlConnection.getConnection();

    	String query = "select  date,closevalue from stocksnsedaywisereport1  where nsescriptcode=? and date <= ? order by date desc LIMIT 15";   

    	try {
    		PreparedStatement statement = connection.prepareStatement(query);
    		statement.setString(1,nsescriptCode );
    		statement.setString(2,date );
    		ResultSet rs= statement.executeQuery();
    		while (rs.next()) {
    			//System.out.println(rs.getString("date")+"----"+rs.getString("closevalue"));
    			dataMap.put(rs.getString("date"), Double.parseDouble(rs.getString("closevalue")));
    			closeValues.add(Double.parseDouble(rs.getString("closevalue")));
    		}

    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	}
    	
    
    
    public double calculateValues(){
    	
    	double change = 0.0;
    	DecimalFormat df = new DecimalFormat("####0.00");
    	for (int i = closeValues.size()-1; i > 0 ; i--) {
    		//System.out.println(closeValues.get(i));
    		change = Double.parseDouble(df.format(closeValues.get(i-1)-closeValues.get(i)));
    		if(i != 0){
    			changeValues.add(change);
    		}

    		if(change >= 0){
    			gainValues.add(change);
    			avgGain = avgGain +change;
    		}else{
    			lossValues.add(-change);
    			avgLoss = avgLoss+ (-change);
    		}
    		//System.out.println("Change "+change);
    		change =0.0;

    	}
    	
    	avgGain = Double.parseDouble(df.format(avgGain/14));
    	
    	avgLoss = Double.parseDouble(df.format(avgLoss/14));
    	
    	rsValue = Double.parseDouble(df.format((avgGain /(avgGain + avgLoss)) *100));
    	
    	stockChange = Double.parseDouble(df.format(closeValues.get(1)-closeValues.get(0)));
    	
    	if(stockChange >= 0){
    		stockGain = stockChange;
    	}else{
    		stockLoss = -stockChange;
    	}
    	
    	stockAvgGain = Double.parseDouble(df.format((avgGain*13 + stockGain) /14));
    	stockAvgLoss = Double.parseDouble(df.format((avgLoss*13 + stockLoss) /14));
    	//stockRSI = Double.parseDouble(df.format((stockAvgGain/ (stockAvgGain+stockAvgLoss))*100));
    	stockRSI = Double.parseDouble(df.format(100-(100/(1+(avgGain/avgLoss)))));
    	
    	
    	/*System.out.println(changeValues.size());
    	System.out.println(changeValues);
    	System.out.println("Gain Values :"+gainValues);
    	System.out.println("Loss Values :"+lossValues);
    	System.out.println("Average Gain :"+avgGain);
    	System.out.println("Average Loss :"+avgLoss);
    	System.out.println("14 Days RSI :"+rsValue);
    	System.out.println("Stock Gain :"+stockAvgGain);
    	System.out.println("Stock Loss :"+stockAvgLoss);
    	System.out.println("Stock RSI :"+stockRSI);*/
    	
    	System.out.println(100-(100/(1+(avgGain/avgLoss))));
    	
    	return stockRSI;
    	
    	
    }
	 
}
