package com.stockmarket.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.stockmarket.connections.MySqlConnection;
import com.stockmarket.pojos.DayReportPojo;
import com.stockmarket.pojos.StockPojo;
import com.stockmarket.pojos.YearWiseReportDataPojo;
import com.stockmarket.util.StockUtililty;

public class SearchDAO {


	static Connection connection = MySqlConnection.getConnection();
	
	public List<DayReportPojo> singleStockDayWiseSearchReport(StockPojo stockpojo) throws Exception{

		List<DayReportPojo> resultsList = new ArrayList<DayReportPojo>();
		try {

			java.sql.Date insertdate = new java.sql.Date(StockUtililty.
					daysBefore(stockpojo.getDate(),stockpojo.getPeriod()).getTime()); 
			java.sql.Date date = new java.sql.Date(StockUtililty.
					dateFormat(stockpojo.getDate()).getTime()); 
			PreparedStatement statement = connection.prepareStatement("select * from stocksnsedaywisereport1 where nsescriptcode=? and date between ? and ? order by date");
			statement.setString(1, stockpojo.getNsescriptcode().trim());
			statement.setDate(2, insertdate);
			statement.setDate(3, date);
			ResultSet resultSet = statement.executeQuery();
			int count =  0;
			double periodlow = periodLow(stockpojo.getNsescriptcode().trim(),stockpojo.getDate(),stockpojo.getPeriod());
			double periodhigh = periodHigh(stockpojo.getNsescriptcode().trim(),stockpojo.getDate(),stockpojo.getPeriod());
			double range =0.0;
			double change = 0.0;
			DecimalFormat structure = new DecimalFormat("0.00");
			while (resultSet.next()) {
				DayReportPojo pojo = new DayReportPojo();
				pojo.setNseScriptCode(resultSet.getString(2));
				pojo.setOpen(Double.parseDouble(resultSet.getString(3)));
				pojo.setHigh(Double.parseDouble(resultSet.getString(4)));
				if(Double.parseDouble(resultSet.getString(4)) == periodhigh ){
					pojo.setPeriodhighcounter(1);
				}else{
					pojo.setPeriodhighcounter(0);
				}
				pojo.setLow(Double.parseDouble(resultSet.getString(5)));
				if(Double.parseDouble(resultSet.getString(5)) == periodlow ){
					pojo.setPeriodlowcounter(1);
				}else{
					pojo.setPeriodlowcounter(0);
				}
				pojo.setClose(Double.parseDouble(resultSet.getString(6)));
				pojo.setLast(Double.parseDouble(resultSet.getString(7)));
				pojo.setPreviousClose(Double.parseDouble(resultSet.getString(8)));
				pojo.setNoshares(Double.parseDouble(resultSet.getString(9)));
				pojo.setTurnover(resultSet.getString(10));
				pojo.setNoTrades(resultSet.getInt(11));
				pojo.setDate(resultSet.getString(12));			
				
				change =Double.parseDouble(resultSet.getString(6))-Double.parseDouble(resultSet.getString(8));
				change = Double.parseDouble(structure.format(change));
				pojo.setChange(change);
				if(Double.parseDouble(resultSet.getString(8))-Double.parseDouble(resultSet.getString(6)) <= 0){
					pojo.setUpward(change);
				}else{
					pojo.setDownfall(change);
				}
				range = Double.parseDouble(resultSet.getString(4))-Double.parseDouble(resultSet.getString(5));
				pojo.setRange((int)range);
				if(resultSet.isLast()){
					pojo.setPeriod(count);
				}else{
					pojo.setPeriod(-100);
				}
				pojo.setMargin(marginvalue(resultSet.getString(2)));
				pojo.setPeriodlow(periodlow);
				pojo.setPeriodhigh(periodhigh);
				pojo.setYearLow(periodLow(stockpojo.getNsescriptcode().trim(),stockpojo.getDate(),365));
				pojo.setYearHigh(periodHigh(stockpojo.getNsescriptcode().trim(),stockpojo.getDate(),365));
				pojo.setYearLowDate(periodLowDate(stockpojo.getNsescriptcode().trim(),stockpojo.getDate(),365));
				pojo.setYearHighDate(periodHighDate(stockpojo.getNsescriptcode().trim(),stockpojo.getDate(),365));
				double openvalue = Double.parseDouble(new DecimalFormat("##.##").format(resultSet.getDouble(3)-resultSet.getDouble(8)));
				if(resultSet.getDouble(8) < resultSet.getDouble(3)){
					pojo.setBullish(openvalue);
				}else{
					pojo.setBarish(openvalue);
				}
				count++;
				resultsList.add(pojo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultsList;
	}


	public List<DayReportPojo> marginStocksSarchReport(StockPojo stockpojo) throws Exception{

		List<DayReportPojo> resultsList = new ArrayList<DayReportPojo>();

		StringBuilder query = new StringBuilder("select * from stocksnsedaywisereport1 as a join stockmargin "
				+ "as b on a.nsescriptcode=b.nsescriptcode where a.date=? and ");

		if(stockpojo.getMargin() != 0){
			query.append("b.marginvalue = ?");
		}
		query.append(" order by a.openvalue");
		try {

			java.sql.Date date = new java.sql.Date(StockUtililty.dateFormat(stockpojo.getDate()).getTime()); 

			PreparedStatement statement = connection.prepareStatement(query.toString());
			statement.setDate(1, date);

			if(stockpojo.getMargin() != 0){
				statement.setInt(2, stockpojo.getMargin());
			}

			ResultSet resultSet = statement.executeQuery();
			double range = 0.0;
			double change = 0.0;
			DecimalFormat structure = new DecimalFormat("0.00");
			while (resultSet.next()) {
				DayReportPojo pojo = new DayReportPojo();
				pojo.setNseScriptCode(resultSet.getString(2));
				pojo.setOpen(Double.parseDouble(resultSet.getString(3)));
				pojo.setHigh(Double.parseDouble(resultSet.getString(4)));
				pojo.setLow(Double.parseDouble(resultSet.getString(5)));
				pojo.setClose(Double.parseDouble(resultSet.getString(6)));
				pojo.setLast(Double.parseDouble(resultSet.getString(7)));
				pojo.setPreviousClose(Double.parseDouble(resultSet.getString(8)));
				pojo.setNoshares(resultSet.getDouble(9));
				pojo.setTurnover(resultSet.getString(10));
				pojo.setNoTrades(resultSet.getInt(11));
				pojo.setDate(resultSet.getString(12));
				range =Double.parseDouble(resultSet.getString(4))-Double.parseDouble(resultSet.getString(5));
				change = Double.parseDouble(resultSet.getString(6))-Double.parseDouble(resultSet.getString(8));
				change = Double.parseDouble(structure.format(change));
				pojo.setChange(change);
				if(Double.parseDouble(resultSet.getString(8))-Double.parseDouble(resultSet.getString(6)) <= 0){
					pojo.setUpward(change);
				}else{
					pojo.setDownfall(change);
				}
				pojo.setRange((int)range);
				pojo.setMargin(stockpojo.getMargin());
				resultsList.add(pojo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultsList;

	}

	public List<DayReportPojo> profitStocksSarchReport(StockPojo stockpojo) throws Exception{

		List<DayReportPojo> resultsList = new ArrayList<DayReportPojo>();

		String searchquery = null;

		if(stockpojo.getIndexType().equalsIgnoreCase("select")){

			if(stockpojo.getMargin() == 0){
				searchquery ="select  nsescriptcode,closevalue,previousclosevalue,openvalue,highvalue,lowvalue from stocksnsedaywisereport1 where date=?";
			}else{
				if(stockpojo.getMargin() == 100){
					searchquery = "select  a.nsescriptcode,a.closevalue,a.previousclosevalue,a.openvalue,a.highvalue,a.lowvalue "
							+ "from stocksnsedaywisereport1 as a join stockmargin as b on a.nsescriptcode=b.nsescriptcode "
							+ "where a.date=? ";

				}else{
					searchquery = "select a.nsescriptcode,a.closevalue,a.previousclosevalue,a.openvalue,a.highvalue,a.lowvalue"
							+ " from stocksnsedaywisereport1 as a join"
							+ "  stockmargin as b on a.nsescriptcode=b.nsescriptcode where b.marginvalue= '"+stockpojo.getMargin() +"' and a.date=? ";
				}
			}

		}else{

			if(stockpojo.getMargin() == 0){
				searchquery ="select distinct a.nsescriptcode,a.closevalue,a.previousclosevalue,c.indextype,"
						+ "a.openvalue,a.highvalue,a.lowvalue,c.lotsize from stocksnsedaywisereport1 as a join  "
						+ " stocknseindexes as c on a.nsescriptcode=c.nsescriptcode where c.indextype='"+stockpojo.getIndexType()+"' and a.date=?";
			}else{

				if(stockpojo.getMargin() == 100){
					searchquery = "select distinct a.nsescriptcode,a.closevalue,a.previousclosevalue,c.indextype,a.openvalue,a.highvalue,a.lowvalue,c.lotsize from "
							+ "stocksnsedaywisereport1 as a join stocknseindexes as c on a.nsescriptcode=c.nsescriptcode "
							+ " where c.indextype='"+stockpojo.getIndexType()+"' and a.date=? ";

				}else{
					searchquery = "select distinct a.nsescriptcode,a.closevalue,a.previousclosevalue,b.marginvalue,c.indextype,"
							+ "a.openvalue,a.highvalue,a.lowvalue,c.lotsize from stocksnsedaywisereport1 "
							+ " as a join stockmargin as b on a.nsescriptcode=b.nsescriptcode join  "
							+ " stocknseindexes as c on a.nsescriptcode=c.nsescriptcode where c.indextype='"+stockpojo.getIndexType()+"' and b.marginvalue = '"+stockpojo.getMargin() +"' "
							+ " and a.date= ?  ";
				}
			}
		}

		StringBuilder query = new StringBuilder(searchquery);
		int lowprice = 0;
		int highprice = 0;


		if(!stockpojo.getPrice().equalsIgnoreCase("select")){
			query.append(" and openvalue between ? and ?  ");
			lowprice = Integer.parseInt(stockpojo.getPrice().split("-")[0]);
			highprice = Integer.parseInt(stockpojo.getPrice().split("-")[1]);
		}
		query.append(" order by openvalue desc");

		try {

			java.sql.Date sqldate = new java.sql.Date(StockUtililty.
					dateFormat(stockpojo.getDate()).getTime()); 
			java.sql.Date insertdate = new java.sql.Date(StockUtililty.
					daysBefore(stockpojo.getDate(),stockpojo.getPeriod()).getTime()); 
			PreparedStatement statement = connection.prepareStatement(query.toString());
			statement.setDate(1, sqldate);
			if(!stockpojo.getPrice().equalsIgnoreCase("select")){
				statement.setInt(2, lowprice);
				statement.setInt(3, highprice);
			}
			ResultSet resultSet = statement.executeQuery();
			int count = 0;
			String nsescriptcode = null;
			DecimalFormat structure = new DecimalFormat("0.00");
			while (resultSet.next()) {

				double previousCloseValue = 0;
				nsescriptcode= resultSet.getString("nsescriptcode");
				double todayCloseValue = Double.parseDouble(resultSet.getString("closevalue"));
				DayReportPojo pojo = new DayReportPojo();
				pojo.setNseScriptCode(nsescriptcode);
				pojo.setOpen(Double.parseDouble(resultSet.getString("openvalue")));
				pojo.setHigh(Double.parseDouble(resultSet.getString("highvalue")));
				pojo.setLow(Double.parseDouble(resultSet.getString("lowvalue")));
				pojo.setLotsize(lotSize(nsescriptcode));
				pojo.setClose(todayCloseValue);
				pojo.setMargin(marginvalue(nsescriptcode));
				if(stockpojo.getPeriod() ==0){
					previousCloseValue = Double.parseDouble(resultSet.getString("previousclosevalue"));
				}else{

					previousCloseValue = checkDate(insertdate,nsescriptcode);

					if(previousCloseValue == 0){
						String periodquery = "select closevalue,max(date) from stocksnsedaywisereport1 where nsescriptcode=? and date <=?";
						PreparedStatement statement1 = connection.prepareStatement(periodquery);
						statement1.setString(1, nsescriptcode);
						statement1.setDate(2, insertdate);
						ResultSet rs = statement1.executeQuery();
						if(rs.next()){
							previousCloseValue = Double.parseDouble(rs.getString("closevalue"));
							System.out.println(rs.getString("max(date)"));
						}
					}
				}
				pojo.setPreviousClose(previousCloseValue);

				String profitType = stockpojo.getProfitType();
				String profit = stockpojo.getProfit();
      

				if(previousCloseValue >0){

					int lowprofit = 0;
					int highprofit = 0;
					double change = (double) (todayCloseValue-previousCloseValue);
					change = Double.parseDouble(structure.format(change));
					
					pojo.setChange(change);
					double percentage = (change/previousCloseValue)*100;
					percentage =Double.parseDouble(new DecimalFormat("##.##").format(percentage));
					pojo.setPercentage(percentage);

					if(!profit.equalsIgnoreCase("select") &&(profitType.equalsIgnoreCase("select"))){
						lowprofit = Integer.parseInt(stockpojo.getProfit().split("-")[0]);
						highprofit = Integer.parseInt(stockpojo.getProfit().split("-")[1]);
						if(change >lowprofit && change < highprofit){
							resultsList.add(pojo);
						}
					}

					if(!profit.equalsIgnoreCase("select") &&(profitType.equalsIgnoreCase("lose") && change < 0)){

						lowprofit = Integer.parseInt(stockpojo.getProfit().split("-")[0]);
						highprofit = Integer.parseInt(stockpojo.getProfit().split("-")[1]);
						change = (int) (previousCloseValue-todayCloseValue);
						pojo.setChange(previousCloseValue-todayCloseValue);
						percentage = (change/previousCloseValue)*100;
						percentage =Double.parseDouble(new DecimalFormat("##.##").format(percentage));
						pojo.setPercentage(percentage);
						if(change >= lowprofit && change < highprofit){
							resultsList.add(pojo);
						}
					}

					if(!profit.equalsIgnoreCase("select") &&(profitType.equalsIgnoreCase("gain") && change > 0)){

						lowprofit = Integer.parseInt(stockpojo.getProfit().split("-")[0]);
						highprofit = Integer.parseInt(stockpojo.getProfit().split("-")[1]);
						if(change >lowprofit && change < highprofit){
							resultsList.add(pojo);
						}
					}

					if(profit.equalsIgnoreCase("select") &&(profitType.equalsIgnoreCase("select"))){
						resultsList.add(pojo);
					}

					if(profit.equalsIgnoreCase("select") &&(profitType.equalsIgnoreCase("lose") && change < 0)){
						resultsList.add(pojo);
					}

					if(profit.equalsIgnoreCase("select") &&(profitType.equalsIgnoreCase("gain") && change > 0)){
						resultsList.add(pojo);
					}
				}
				count++;
				System.out.println(count);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		 Collections.sort(resultsList,new Comparator<DayReportPojo>() {

			@Override
			public int compare(DayReportPojo open1, DayReportPojo open2) {
				// TODO Auto-generated method stub
				return (Integer)open1.getOpen().compareTo(open2.getOpen());
			}
		});
		
		return resultsList;

	}

	public List<DayReportPojo> yearLowHIghStocksSarch(StockPojo stockpojo) throws Exception{

		List<DayReportPojo> resultsList = new ArrayList<DayReportPojo>();

		String searchquery = null;

		if(stockpojo.getIndexType().equalsIgnoreCase("select")){

			if(stockpojo.getMargin() == 0){
				searchquery ="select  nsescriptcode,closevalue,previousclosevalue,lowvalue,highvalue from stocksnsedaywisereport1 where date=?";
			}else{
				if(stockpojo.getMargin() == 100){
					searchquery = "select  a.nsescriptcode,a.closevalue,a.previousclosevalue,a.lowvalue,a.highvalue "
							+ "from stocksnsedaywisereport1 as a join stockmargin as b on a.nsescriptcode=b.nsescriptcode "
							+ "where a.date=? ";

				}else{
					searchquery = "select a.nsescriptcode,a.closevalue,a.previousclosevalue,a.lowvalue,a.highvalue from stocksnsedaywisereport1 as a join"
							+ "  stockmargin as b on a.nsescriptcode=b.nsescriptcode where b.marginvalue= '"+stockpojo.getMargin() +"' and a.date=? ";
				}
			}

		}else{

			if(stockpojo.getMargin() == 0){
				searchquery ="select distinct a.nsescriptcode,a.closevalue,a.previousclosevalue,a.lowvalue,a.highvalue,c.indextype from stocksnsedaywisereport1 as a join  "
						+ " stocknseindexes as c on a.nsescriptcode=c.nsescriptcode where c.indextype='"+stockpojo.getIndexType()+"' and a.date=?";
			}else{

				if(stockpojo.getMargin() == 100){
					searchquery = "select distinct a.nsescriptcode,a.closevalue,a.previousclosevalue,a.lowvalue,a.highvalue,c.indextype from "
							+ "stocksnsedaywisereport1 as a join stocknseindexes as c on a.nsescriptcode=c.nsescriptcode "
							+ " where c.indextype='"+stockpojo.getIndexType()+"' and a.date=? ";

				}else{
					searchquery = "select distinct a.nsescriptcode,a.closevalue,a.previousclosevalue,b.marginvalue,a.lowvalue,a.highvalue,c.indextype from stocksnsedaywisereport1 "
							+ " as a join stockmargin as b on a.nsescriptcode=b.nsescriptcode join  "
							+ " stocknseindexes as c on a.nsescriptcode=c.nsescriptcode where c.indextype='"+stockpojo.getIndexType()+"' and b.marginvalue = '"+stockpojo.getMargin() +"' "
							+ " and a.date= ?  ";
				}
			}
		}

		StringBuilder query = new StringBuilder(searchquery);
		int lowprice = 0;
		int highprice = 0;


		if(!stockpojo.getPrice().equalsIgnoreCase("select")){
			query.append(" and openvalue between ? and ?  ");
			lowprice = Integer.parseInt(stockpojo.getPrice().split("-")[0]);
			highprice = Integer.parseInt(stockpojo.getPrice().split("-")[1]);
		}
		query.append(" order by openvalue");

		try {

			java.sql.Date sqldate = new java.sql.Date(StockUtililty.
					dateFormat(stockpojo.getDate()).getTime()); 
			java.sql.Date insertdate = new java.sql.Date(StockUtililty.
					daysBefore(stockpojo.getDate(),stockpojo.getPeriod()).getTime()); 
			PreparedStatement statement = connection.prepareStatement(query.toString());

			statement.setDate(1, sqldate);
			if(!stockpojo.getPrice().equalsIgnoreCase("select")){
				statement.setInt(2, lowprice);
				statement.setInt(3, highprice);
			}
			ResultSet resultSet = statement.executeQuery();
			int count = 0;
			String nsescriptcode = null;
			String profitType = stockpojo.getProfitType();
			int nearvalue = -1;
			while (resultSet.next()) {

				double previousCloseValue = 0;
				nsescriptcode= resultSet.getString("nsescriptcode");
				double todayCloseValue = Double.parseDouble(resultSet.getString("closevalue"));
				DayReportPojo pojo = new DayReportPojo();
				pojo.setNseScriptCode(nsescriptcode);
				pojo.setLotsize(lotSize(nsescriptcode));
				pojo.setClose(todayCloseValue);
				pojo.setMargin(marginvalue(nsescriptcode));
				if(stockpojo.getPeriod() ==0){
					previousCloseValue = Double.parseDouble(resultSet.getString("previousclosevalue"));
				}else{

					previousCloseValue = checkDate(insertdate,nsescriptcode);

					if(previousCloseValue == 0){
						String periodquery = "select closevalue,max(date) from stocksnsedaywisereport1 where nsescriptcode=? and date <=?";
						PreparedStatement statement1 = connection.prepareStatement(periodquery);
						statement1.setString(1, nsescriptcode);
						statement1.setDate(2, insertdate);
						ResultSet rs = statement1.executeQuery();
						if(rs.next()){
							previousCloseValue = Double.parseDouble(resultSet.getString("closevalue"));
							System.out.println(rs.getString("max(date)"));
						}
					}
				}
				pojo.setPreviousClose(previousCloseValue);
				double low = 0.0;
				double high = 0.0;
				double yearlow = 0.0;
				double yearhigh = 0.0;
				nearvalue = stockpojo.getNearvalue();
				if(stockpojo.getPeriod() != 0){
					low = periodLow(nsescriptcode,stockpojo.getDate(),stockpojo.getPeriod());
					high = periodHigh(nsescriptcode,stockpojo.getDate(),stockpojo.getPeriod());

				}

				if(profitType.equalsIgnoreCase("select")){
					if((low ==yearlow) || (high==yearhigh)){
						pojo.setDate(stockpojo.getDate());
						resultsList.add(pojo);
					}
				}else{

					if(profitType.equalsIgnoreCase("low")){
						if(low == 0.0){
							low = Double.parseDouble(resultSet.getString("lowvalue"));
						}
						yearlow = periodLow(nsescriptcode,stockpojo.getDate(),365);
						System.out.println(nsescriptcode+"---"+low+"---"+yearlow);
						if((low == yearlow) || ((todayCloseValue-yearlow) <= nearvalue)){
							pojo.setDate(periodLowDate(nsescriptcode,stockpojo.getDate(),365)+"");
							pojo.setUpward(todayCloseValue-low);
							pojo.setLow(low);
							resultsList.add(pojo);
						}
					}
					if(profitType.equalsIgnoreCase("high")){
						if(high ==0.0){
							high = Double.parseDouble(resultSet.getString("highvalue"));
						}
						yearhigh = periodHigh(nsescriptcode,stockpojo.getDate(),365);
						System.out.println(nsescriptcode+"--high------------"+high+"---"+yearhigh);
						if((high==yearhigh) || ((high-todayCloseValue) <= nearvalue)){
							pojo.setDate(periodHighDate(nsescriptcode,stockpojo.getDate(),365)+"");
							pojo.setUpward(high - todayCloseValue);
							pojo.setHigh(high);
							resultsList.add(pojo);
						}
					}
				}


				System.out.println(count);
				count++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultsList;
	}

	public String lotSize(String nsescriptcode){

		String query = new String("select lotsize from stocknseindexes  where nsescriptcode=? and indextype='Derivaties';");
		String result = null;
		try {

			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, nsescriptcode);
			ResultSet rs = statement.executeQuery();
			if(rs.next()){
				result = rs.getString(1);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return result;
	}
	

	public int marginvalue(String nsescriptcode){

		String query = new String("select marginvalue from stockmargin where nsescriptcode=?");
		int result = 0;
		try {

			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, nsescriptcode);
			ResultSet rs = statement.executeQuery();
			if(rs.next()){
				result = rs.getInt(1);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return result;
	}

	public double periodLow(String nsescriptcode,String date,int period){

		double result = 0.0; 
		try {
			java.sql.Date insertdate = new java.sql.Date(StockUtililty.
					daysBefore(date,period).getTime()); 
			java.sql.Date date1 = new java.sql.Date(StockUtililty.
					dateFormat(date).getTime()); 

			PreparedStatement statement = connection.prepareStatement("select min(lowvalue) from stocksnsedaywisereport1 where nsescriptcode=? "
					+ "and date between ? and ? ");
			statement.setString(1, nsescriptcode);
			statement.setDate(2, insertdate);
			statement.setDate(3, date1);
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()){
				result = Double.parseDouble(resultSet.getString(1));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}


		return result; 
	}

	public double periodHigh(String nsescriptcode,String date,int period){
		double result = 0.0; 
		try {

			java.sql.Date insertdate = new java.sql.Date(StockUtililty.
					daysBefore(date,period).getTime()); 
			java.sql.Date date1 = new java.sql.Date(StockUtililty.
					dateFormat(date).getTime()); 

			PreparedStatement statement = connection.prepareStatement("select max(highvalue) from stocksnsedaywisereport1 where nsescriptcode=? "
					+ "and date between ? and ? ");
			statement.setString(1, nsescriptcode);
			statement.setDate(2, insertdate);
			statement.setDate(3, date1);
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()){
				result = Double.parseDouble(resultSet.getString(1));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}


		return result; 
	}

	double checkDate(java.sql.Date insertdate,String nsescriptcode){


		double result =0;

		try {

			PreparedStatement statement = connection.prepareStatement("select closevalue from stocksnsedaywisereport1 where nsescriptcode=? and date =?");
			statement.setString(1, nsescriptcode);
			statement.setDate(2, insertdate);
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()){
				result = Double.parseDouble(resultSet.getString(1));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	public Date periodLowDate(String nsescriptcode,String date,int period){

		Date result = null; 
		try {
			java.sql.Date insertdate = new java.sql.Date(StockUtililty.
					daysBefore(date,period).getTime()); 
			java.sql.Date date1 = new java.sql.Date(StockUtililty.
					dateFormat(date).getTime()); 

			PreparedStatement statement = connection.prepareStatement("select max(date) from stocksnsedaywisereport1 where lowvalue in ( "
					+ "select min(lowvalue) from stocksnsedaywisereport1 "
					+ "where nsescriptcode=? and date between ? and ? ) "
					+ "and  nsescriptcode=?");
			statement.setString(1, nsescriptcode);
			statement.setDate(2, insertdate);
			statement.setDate(3, date1);
			statement.setString(4, nsescriptcode);
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()){
				result = resultSet.getDate(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}


		return result; 
	}
	public Date periodHighDate(String nsescriptcode,String date,int period){

		Date result = null; 
		try {
			java.sql.Date insertdate = new java.sql.Date(StockUtililty.
					daysBefore(date,period).getTime()); 
			java.sql.Date date1 = new java.sql.Date(StockUtililty.
					dateFormat(date).getTime()); 

			PreparedStatement statement = connection.prepareStatement("select max(date) from stocksnsedaywisereport1 where highvalue in ( "
					+ "select max(highvalue) from stocksnsedaywisereport1 "
					+ "where nsescriptcode=? and date between ? and ? ) "
					+ "and  nsescriptcode=?");
			statement.setString(1, nsescriptcode);
			statement.setDate(2, insertdate);
			statement.setDate(3, date1);
			statement.setString(4, nsescriptcode);
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()){
				result = resultSet.getDate(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}


		return result; 
	}


	public List<DayReportPojo> dayTrading(StockPojo stockpojo){

		String query = "select * FROM stocksnsedialytrade";
		List<DayReportPojo> result = new ArrayList<DayReportPojo>();
		try {

			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			Date myDate = new Date();
			while (resultSet.next()) {
				DayReportPojo pojo = new DayReportPojo();
				
				pojo.setNseScriptCode(resultSet.getString("nsescriptcode"));
				pojo.setOpen(Double.parseDouble(resultSet.getString("openvalue")));
				pojo.setHigh(Double.parseDouble(resultSet.getString("highvalue")));
				pojo.setLow(Double.parseDouble(resultSet.getString("lowvalue")));
				double todayOpenValue = Double.parseDouble(resultSet.getString("openvalue"));
				double previouscloseValue = Double.parseDouble(resultSet.getString("closevalue").split("-")[0]);
				double previousOpenValue = Double.parseDouble(resultSet.getString("closevalue").split("-")[1]);
				pojo.setClose(previouscloseValue);
				pojo.setPreviousOpen(previousOpenValue);

				if(stockpojo.getProfitType().equals("buy")){

					if((previouscloseValue>previousOpenValue) && (previouscloseValue>todayOpenValue)){
						pojo.setPeriodlow(periodHigh(resultSet.getString("nsescriptcode"),new SimpleDateFormat("yyyy-MM-dd").format(myDate),4));
						result.add(pojo);
					}
					

				}else{

					if((previouscloseValue<previousOpenValue) && (previouscloseValue<todayOpenValue)){
						pojo.setPeriodlow(periodLow(resultSet.getString("nsescriptcode"),new SimpleDateFormat("yyyy-MM-dd").format(myDate),4));
						result.add(pojo);
					}
					
					
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	
}
