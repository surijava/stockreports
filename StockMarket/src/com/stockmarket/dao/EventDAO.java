package com.stockmarket.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.stockmarket.connections.MySqlConnection;
import com.stockmarket.pojos.FiiDiiDataPojo;
import com.stockmarket.pojos.FiiDiiOpenInterestPojo;
import com.stockmarket.pojos.StockPojo;
import com.stockmarket.util.StockUtililty;

public class EventDAO {

	Connection connection = MySqlConnection.getConnection();

	public String addEvent(StockPojo stockdetails){


		try {

			java.sql.Date date = new java.sql.Date(StockUtililty.
					dateFormat(stockdetails.getDate()).getTime()); 
			String query = "insert into events(eventtype,eventdate)"
					+ " values(?,?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, stockdetails.getChoice());
			statement.setDate(2,date );
			statement.executeUpdate();

			return "success";

		} catch (Exception e) {
			e.printStackTrace();
			return "fail"; 
		}


	}


	public List<StockPojo>  getEvents() throws Exception{

		List<StockPojo> events = new ArrayList<StockPojo>();

		LocalDateTime ldt = LocalDateTime.now();
		int period = 7;

		String currentDay = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH).format(ldt);

		java.sql.Date sixDatePreviousDate = new java.sql.Date(StockUtililty.
				daysAfter(currentDay,period).getTime()); 
		java.sql.Date  currentDate= new java.sql.Date(StockUtililty.
				daysBefore(currentDay,period).getTime());

		PreparedStatement statementDates = connection.prepareStatement("select * from events where eventdate between ? and ? order by eventdate ");
		statementDates.setDate(1, currentDate);
		statementDates.setDate(2, sixDatePreviousDate);

		ResultSet resultSet = statementDates.executeQuery();
		while(resultSet.next()) {

			StockPojo pojo = new StockPojo();

			pojo.setEventtype(resultSet.getString("eventtype"));
			pojo.setEventdate(resultSet.getString("eventdate"));

			events.add(pojo);
		}

		return events;
	}
	
	public List<FiiDiiDataPojo>  getFIIDIIData() throws Exception{

		List<FiiDiiDataPojo> events = new ArrayList<FiiDiiDataPojo>();

		LocalDateTime ldt = LocalDateTime.now();
		int period = 7;

		String currentDay = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH).format(ldt);

		java.sql.Date sixDatePreviousDate = new java.sql.Date(StockUtililty.
				daysBefore(currentDay,period).getTime()); 
		java.sql.Date  currentDate= new java.sql.Date(StockUtililty.
				dateFormat(currentDay).getTime());

		PreparedStatement statementDates = connection.prepareStatement("select * from fiidiidatareport where date between ? and ? order by date ");
		statementDates.setDate(1, sixDatePreviousDate);
		statementDates.setDate(2, currentDate);

		ResultSet resultSet = statementDates.executeQuery();
		while(resultSet.next()) {

			FiiDiiDataPojo pojo = new FiiDiiDataPojo();

			pojo.setCategory(resultSet.getString("category"));
			pojo.setBuyValue(resultSet.getString("buyvalue"));
			pojo.setSellValue(resultSet.getString("sellvalue"));
			pojo.setNetValue(resultSet.getString("netvalue"));
			pojo.setDate(resultSet.getString("date"));

			events.add(pojo);
		}

		return events;
	}

	public String addShareCorporateEvent(StockPojo stockdetails){


		try {

			java.sql.Date date = new java.sql.Date(StockUtililty.
					dateFormat(stockdetails.getDate()).getTime()); 
			String query = "insert into stockcorporateevent(eventtype,nsescriptcode,quarter,eventdate)"
					+ " values(?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, stockdetails.getChoice());
			statement.setString(2, stockdetails.getNsescriptcode());
			statement.setString(3, stockdetails.getQuarter());
			statement.setDate(4,date );
			statement.executeUpdate();

			return "success";

		} catch (Exception e) {
			e.printStackTrace();
			return "fail"; 
		}


	}

	public List<StockPojo> derivatiesWeeklyEvents() throws Exception{
		
		List<StockPojo> events = new ArrayList<StockPojo>();

		LocalDateTime ldt = LocalDateTime.now();
		int period = 7;

		String currentDay = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH).format(ldt);

		java.sql.Date sixDatePreviousDate = new java.sql.Date(StockUtililty.
				daysAfter(currentDay,period).getTime()); 
		java.sql.Date  currentDate= new java.sql.Date(StockUtililty.
				daysBefore(currentDay,4).getTime());

		PreparedStatement statementDates = connection.prepareStatement("select * from stockcorporateevent where eventdate between ? and ? order by eventdate ");
		statementDates.setDate(1, currentDate);
		statementDates.setDate(2, sixDatePreviousDate);

		ResultSet resultSet = statementDates.executeQuery();
		while(resultSet.next()) {

			StockPojo pojo = new StockPojo();

			pojo.setEventtype(resultSet.getString("eventtype"));
			pojo.setEventdate(resultSet.getString("eventdate"));
			pojo.setNsescriptcode(resultSet.getString("nsescriptcode"));
			pojo.setQuarter(resultSet.getString("quarter"));

			events.add(pojo);
		}

		return events;

	}

	public List<FiiDiiOpenInterestPojo> derivatiesPositions() throws Exception{

		final DecimalFormat factor = new DecimalFormat("0.00");  
		List<FiiDiiOpenInterestPojo> fiidiidata = new ArrayList<FiiDiiOpenInterestPojo>();
		LocalDateTime ldt = LocalDateTime.now();
		int period = 10;

		String currentDay = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH).format(ldt);

		java.sql.Date sixDatePreviousDate = new java.sql.Date(StockUtililty.
				daysBefore(currentDay,period).getTime()); 
		java.sql.Date  currentDate= new java.sql.Date(StockUtililty.
				dateFormat(currentDay).getTime());

		PreparedStatement statementDates = connection.prepareStatement("select * from fiidiiopeninterest where  date between ? and ? order by date ");
		statementDates.setDate(1, sixDatePreviousDate);
		statementDates.setDate(2, currentDate);

		ResultSet resultSet = statementDates.executeQuery();
		
		while(resultSet.next()) {

			if(resultSet.getString("clienttype").equalsIgnoreCase("FII")){

				FiiDiiOpenInterestPojo pojo = new FiiDiiOpenInterestPojo();
				pojo.setClienttype(resultSet.getString("clienttype"));
				Double indexlog = Double.parseDouble(resultSet.getString("futureindexlong"));
				Double indexShort = Double.parseDouble(resultSet.getString("futureindexshort"));
				pojo.setFutureindexlong(resultSet.getString("futureindexlong"));
				pojo.setFutureindexshort(resultSet.getString("futureindexshort"));
				pojo.setFuturestocklong(resultSet.getString("futurestocklong"));
				pojo.setFuturestockshort(resultSet.getString("futurestockshort"));
				pojo.setOptionindexcalllong(resultSet.getString("optionindexcalllong"));
				pojo.setOptionindexputlong(resultSet.getString("optionindexputlong"));
				pojo.setOptionindexcallshort(resultSet.getString("optionindexcallshort"));
				pojo.setOptionindexputshort(resultSet.getString("optionindexputshort"));
				pojo.setOptionstockcalllong(resultSet.getString("optionstockcalllong"));					
				pojo.setOptionstockputlog(resultSet.getString("optionstockputlog"));
				pojo.setOptionstockcallshort(resultSet.getString("optionstockcallshort"));
				pojo.setOptionstockputshort(resultSet.getString("optionstockputshort"));
				pojo.setTotallongcontracts(resultSet.getString("totallongcontracts"));
				pojo.setTotalshortcontracts(resultSet.getString("totalshortcontracts"));
				pojo.setDate(resultSet.getDate("date").toString());
				
				double totalLong = Math.round(indexlog/(indexlog+indexShort)*100);
				double totalShort = Math.round(indexShort/(indexlog+indexShort)*100);

				//double totalLong = Double.parseDouble(factor.format(indexlog/(indexlog+indexShort)*100));
				//double totalShort = Double.parseDouble(factor.format(indexShort/(indexlog+indexShort)*100));

				pojo.setTotalLong(totalLong);
				pojo.setTotalShort(totalShort);
				
				fiidiidata.add(pojo);
			}


		}


		return fiidiidata; 
	}

}

