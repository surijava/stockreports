package com.stockmarket.csv;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadCSVWithScanner {

	
	
	public static void main(String[] args) throws IOException {
		
		    
		    String csvFile = "C:/Users/surendra.chebrolu/Downloads/EQ020218.csv";
	        BufferedReader br = null;
	        String line = "";
	        String cvsSplitBy = ",";

	        try {

	            br = new BufferedReader(new FileReader(csvFile));
	            int count = 0;
	            while ((line = br.readLine()) != null) {
	                String[] stocks = line.split(cvsSplitBy);
	                System.out.println(stocks[0]);
	                count++;
	            }
	            System.out.println(count);

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

	
}
