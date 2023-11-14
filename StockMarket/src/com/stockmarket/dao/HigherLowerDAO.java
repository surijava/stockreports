package com.stockmarket.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.stockmarket.connections.MySqlConnection;
import com.stockmarket.pojos.DayReportPojo;
import com.stockmarket.pojos.StockPojo;
import com.stockmarket.util.StockUtililty;

public class HigherLowerDAO {


	Connection connection = MySqlConnection.getConnection();
	public Map<String,List<DayReportPojo>>  derivatiesMonthlyHigherLowerReport(StockPojo stockpojo) throws Exception{

		List<DayReportPojo> resultsList = null;

		Map<String,List<DayReportPojo>> finalData = new HashMap<String,List<DayReportPojo>>();

		String searchquery =null;

		if(stockpojo.getOscillation().equalsIgnoreCase("0")){

			searchquery ="select * from stocknseindexes where indextype='"+stockpojo.getIndexType()+"'"
					+ " and nsescriptcode in (select nsescriptcode from stocknseindexes  where indextype ='Derivaties') order by nsescriptcode ";

		}else{
			searchquery ="select * from stocknseindexes where indextype='"+stockpojo.getIndexType()+"' and oslationvalue ='"+stockpojo.getOscillation()
			+ "' and nsescriptcode in (select nsescriptcode from stocknseindexes  where indextype ='Derivaties') order by nsescriptcode ";
		}

		try {

			PreparedStatement statement = connection.prepareStatement(searchquery);
			ResultSet resultSet = statement.executeQuery();
			String maxdate = null;
			String date=null;
			double periodhighvalue = 0.0;
			double periodlowvalue = 0.0;
			int period = 0;

			List<String> dates = new CommonDAO().tradingDates(stockpojo);
			
			if(dates.size() >= 1){
				period = dates.size();
			}else{
				period = 5;
			}

			String currentDay = dates.get(0);

			String PreviousDate = dates.get(period-1);


			while (resultSet.next()) {
				String nsescriptcode = resultSet.getString("nsescriptcode").trim();
				
				
				//System.out.println(nsescriptcode+"--");

				String maxdatequery = "SELECT max(date) date,max(highvalue) high,min(lowvalue) low "
						+ " FROM stocksnsedaywisereport1 WHERE MONTH(date) ="+stockpojo.getMonth() +""
						+ " AND YEAR(date) = "+stockpojo.getYear()+" and nsescriptcode='"+nsescriptcode+"' order by date";

				Statement statement2 = connection.createStatement(
						ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE
						);
				ResultSet resultSet2 = statement2.executeQuery(maxdatequery);
				while (resultSet2.next()) {
					maxdate = resultSet2.getDate("date").toString();
					periodhighvalue = resultSet2.getDouble("high");
					periodlowvalue = resultSet2.getDouble("low");
				}

				
				
				String textquery = "SELECT * "
						+ " FROM stocksnsedaywisereport1 WHERE MONTH(date) ="+stockpojo.getMonth() +""
						+ " AND YEAR(date) = "+stockpojo.getYear()+" and nsescriptcode='"+nsescriptcode+"' order by date";
				Statement statement1 = connection.createStatement(
						ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE
						);
				ResultSet resultSet1 = statement1.executeQuery(textquery);
				DecimalFormat structure = new DecimalFormat("0.00");
				resultsList = new ArrayList<DayReportPojo>();

				while(resultSet1.next()) {
					
					DayReportPojo pojo = new DayReportPojo();
					pojo.setNseScriptCode(nsescriptcode);
					pojo.setLotsize(resultSet.getString("lotsize").trim());
					pojo.setOpen(Double.parseDouble(resultSet1.getString("openvalue")));
					pojo.setHigh(Double.parseDouble(resultSet1.getString("highvalue")));
					pojo.setLow(Double.parseDouble(resultSet1.getString("lowvalue")));
					pojo.setClose(Double.parseDouble(resultSet1.getString("closevalue")));
					pojo.setPreviousClose(Double.parseDouble(resultSet1.getString("previousclosevalue")));
					pojo.setNoshares(Double.parseDouble(resultSet1.getString("noofshares")));
					pojo.setTurnover(resultSet1.getString("turnover"));
					pojo.setNoTrades(Integer.parseInt(resultSet1.getString("nooftrades")));
					date= resultSet1.getDate("date").toString();
					//pojo.setDate(date.split("-")[2]);
					pojo.setDate(date);
					double change =Double.parseDouble(resultSet1.getString("closevalue"))-Double.parseDouble(resultSet1.getString("previousclosevalue"));
					change = Double.parseDouble(structure.format(change));
					pojo.setChange(change);
					if(Double.parseDouble(resultSet1.getString("highvalue")) == periodhighvalue){
						pojo.setRange(11);
					}else if(Double.parseDouble(resultSet1.getString("lowvalue")) == periodlowvalue){
						pojo.setRange(-11);
					}else{
						pojo.setRange(0);
					}
					pojo.setPeriodhigh(periodhighvalue);
					pojo.setPeriodlow(periodlowvalue);
					
					String rsiValue = new CommonDAO().getRsiValue(nsescriptcode,date);
					pojo.setRsi(rsiValue);

					//pojo.setYearLowDate(new SearchDAO().periodLowDate(stockpojo.getNsescriptcode().trim(),stockpojo.getDate(),	size));
					//pojo.setYearHighDate(new SearchDAO().periodHighDate(stockpojo.getNsescriptcode().trim(),stockpojo.getDate(),size));


					resultsList.add(pojo);
				}
				finalData.put(nsescriptcode, resultsList);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return finalData;
	}


	public List<DayReportPojo> derivatiesMonthlyHighLowerResults(StockPojo stockpojo,Set<String> nseCodes) throws Exception{

		List<DayReportPojo> resultsList = new ArrayList<DayReportPojo>();


		for (String dayReportPojo : nseCodes) {


			try {

				String maxdate = null;
				String date=null;
				double periodhighvalue = 0.0;
				double periodlowvalue = 0.0;
				String nsescriptcode = dayReportPojo.trim();

				//System.out.println(nsescriptcode+"--");

				String maxdatequery = "SELECT max(date) date,max(highvalue) high,min(lowvalue) low "
						+ " FROM stocksnsedaywisereport1 WHERE MONTH(date) ="+stockpojo.getMonth() +""
						+ " AND YEAR(date) = "+stockpojo.getYear()+" and nsescriptcode='"+nsescriptcode+"' order by date";

				Statement statement2 = connection.createStatement(
						ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE
						);
				ResultSet resultSet2 = statement2.executeQuery(maxdatequery);
				while (resultSet2.next()) {
					maxdate = resultSet2.getDate("date").toString();
					periodhighvalue = resultSet2.getDouble("high");
					periodlowvalue = resultSet2.getDouble("low");
					//System.out.println(nsescriptcode+"--"+maxdate);
				}

				String textquery = "SELECT *"
						+ " FROM stocksnsedaywisereport1 WHERE MONTH(date) ="+stockpojo.getMonth() +""
						+ " AND YEAR(date) = "+stockpojo.getYear()+" and nsescriptcode='"+nsescriptcode+"' order by date";
				Statement statement1 = connection.createStatement(
						ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE
						);
				ResultSet resultSet1 = statement1.executeQuery(textquery);
				DecimalFormat structure = new DecimalFormat("0.00");

				while (resultSet1.next()) {
					DayReportPojo pojo = new DayReportPojo();
					pojo.setNseScriptCode(nsescriptcode);
					//pojo.setLotsize(resultSet.getString("lotsize").trim());
					pojo.setOpen(Double.parseDouble(resultSet1.getString("openvalue")));
					pojo.setHigh(Double.parseDouble(resultSet1.getString("highvalue")));
					pojo.setLow(Double.parseDouble(resultSet1.getString("lowvalue")));
					pojo.setClose(Double.parseDouble(resultSet1.getString("closevalue")));
					pojo.setPreviousClose(Double.parseDouble(resultSet1.getString("previousclosevalue")));
					pojo.setNoshares(Double.parseDouble(resultSet1.getString("noofshares")));
					pojo.setTurnover(resultSet1.getString("turnover"));
					pojo.setNoTrades(Integer.parseInt(resultSet1.getString("nooftrades")));
					date= resultSet1.getDate("date").toString();
					//pojo.setDate(date.split("-")[2]);
					pojo.setDate(date);
					double change =Double.parseDouble(resultSet1.getString("closevalue"))-Double.parseDouble(resultSet1.getString("previousclosevalue"));
					change = Double.parseDouble(structure.format(change));
					pojo.setChange(change);
					if(Double.parseDouble(resultSet1.getString("highvalue")) == periodhighvalue){
						pojo.setRange(11);
					}else if(Double.parseDouble(resultSet1.getString("lowvalue")) == periodlowvalue){
						pojo.setRange(-11);
					}else{	
						pojo.setRange(0);
					}
					pojo.setPeriodhigh(periodhighvalue);
					pojo.setPeriodlow(periodlowvalue);

					//pojo.setYearLowDate(new SearchDAO().periodLowDate(stockpojo.getNsescriptcode().trim(),stockpojo.getDate(),	size));
					//pojo.setYearHighDate(new SearchDAO().periodHighDate(stockpojo.getNsescriptcode().trim(),stockpojo.getDate(),size));

					String rsiValue = new CommonDAO().getRsiValue(nsescriptcode,date);
					pojo.setRsi(rsiValue);

					resultsList.add(pojo);
					//System.out.println(resultsList.size()+"---------------");
				}
			}catch (Exception e) {
				e.printStackTrace();
			}

		}
		//System.out.println(resultsList.size()+"---------------000000000000000000");
		return resultsList;
	}

}
