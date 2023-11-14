package com.stockmarket.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.stockmarket.connections.MySqlConnection;
import com.stockmarket.pojos.StockPojo;
import com.stockmarket.util.StockUtililty;

public class CommonDAO {


	Connection connection = MySqlConnection.getConnection();

	public List<String> tradingDates(StockPojo stockpojo) throws Exception{

		List<String> resultsList = new ArrayList<String>();		
		String query = "select distinct date from stocksnsedaywisereport1   WHERE MONTH(date) = "+stockpojo.getMonth()+" "
				+ "AND YEAR(date) = "+stockpojo.getYear()+" order by date";
		PreparedStatement statement = connection.prepareStatement(query);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			resultsList.add(resultSet.getString("date"));
		}

		return resultsList;

	}

	public String getRsiValue(String nsescriptcode,String date){

		String rsi = null;

		String query = "select rsi from stockrsi where nsescriptcode=? and date=?";

		try {

			java.sql.Date  currentDate= new java.sql.Date(StockUtililty.dateFormat(date).getTime());

			PreparedStatement statementDates = connection.prepareStatement(query);
			statementDates.setString(1, nsescriptcode);
			statementDates.setDate(2, currentDate);
			ResultSet resultSet = statementDates.executeQuery();
			while(resultSet.next()) {
				rsi = resultSet.getString("rsi").trim();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rsi;
	}


}
