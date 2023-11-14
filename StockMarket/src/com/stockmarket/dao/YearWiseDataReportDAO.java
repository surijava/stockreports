package com.stockmarket.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.stockmarket.connections.MySqlConnection;
import com.stockmarket.pojos.YearWiseReportDataPojo;

public class YearWiseDataReportDAO {

	Connection connection = MySqlConnection.getConnection();

	public String insertYearWiseData(YearWiseReportDataPojo yearwisedatapojo){

		String[] date = yearwisedatapojo.getYear().split("-");
		String query = "insert into stockyearwisedata(year,month,day1,day2,day3,day4,day5,day6,day7,"
				+ "day8,day9,day10,day11,day12,day13,day14,day15,day16,day17,day18,day19,day20,day21,day22,"
				+ "day23,day24,day25,day26,day27,day28,day29,day30,day31,comments)"
				+ "value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {

			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, date[0]);
			statement.setString(2, date[1]);
			statement.setString(3, yearwisedatapojo.getDay1());
			statement.setString(4, yearwisedatapojo.getDay2());
			statement.setString(5, yearwisedatapojo.getDay3());
			statement.setString(6, yearwisedatapojo.getDay4());
			statement.setString(7, yearwisedatapojo.getDay5());
			statement.setString(8, yearwisedatapojo.getDay6());
			statement.setString(9, yearwisedatapojo.getDay7());
			statement.setString(10, yearwisedatapojo.getDay8());
			statement.setString(11, yearwisedatapojo.getDay9());
			statement.setString(12, yearwisedatapojo.getDay10());
			statement.setString(13, yearwisedatapojo.getDay11());
			statement.setString(14, yearwisedatapojo.getDay12());
			statement.setString(15, yearwisedatapojo.getDay13());
			statement.setString(16, yearwisedatapojo.getDay14());
			statement.setString(17, yearwisedatapojo.getDay15());
			statement.setString(18, yearwisedatapojo.getDay16());
			statement.setString(19, yearwisedatapojo.getDay17());
			statement.setString(20, yearwisedatapojo.getDay18());
			statement.setString(21, yearwisedatapojo.getDay19());
			statement.setString(22, yearwisedatapojo.getDay20());
			statement.setString(23, yearwisedatapojo.getDay21());
			statement.setString(24, yearwisedatapojo.getDay22());
			statement.setString(25, yearwisedatapojo.getDay23());
			statement.setString(26, yearwisedatapojo.getDay24());
			statement.setString(27, yearwisedatapojo.getDay25());
			statement.setString(28, yearwisedatapojo.getDay26());
			statement.setString(29, yearwisedatapojo.getDay27());
			statement.setString(30, yearwisedatapojo.getDay28());
			statement.setString(31, yearwisedatapojo.getDay29());
			statement.setString(32, yearwisedatapojo.getDay30());
			statement.setString(33, yearwisedatapojo.getDay31());
			statement.setString(34, yearwisedatapojo.getDay32());
			statement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}


		return null;
	}

	public List<YearWiseReportDataPojo> yearWiseSearchReport(YearWiseReportDataPojo yearwisedatapojo){

		String[] date = yearwisedatapojo.getYear().split("-");
		String query = "select * from stockyearwisedata where year=? and month=?";
		List<YearWiseReportDataPojo> result = new ArrayList<YearWiseReportDataPojo>();
		
		try {
						
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, date[0]);
			statement.setString(2, date[1]);
			ResultSet results = statement.executeQuery();
			
			while (results.next()) {
				YearWiseReportDataPojo pojo = new YearWiseReportDataPojo();
				pojo.setYear(yearwisedatapojo.getYear());
				pojo.setMonth(date[1]);
				pojo.setDay1(results.getString("day1"));
				pojo.setDay2(results.getString("day2"));
				pojo.setDay3(results.getString("day3"));
				pojo.setDay4(results.getString("day4"));
				pojo.setDay5(results.getString("day5"));
				pojo.setDay6(results.getString("day6"));
				pojo.setDay7(results.getString("day7"));
				pojo.setDay8(results.getString("day8"));
				pojo.setDay9(results.getString("day9"));
				pojo.setDay10(results.getString("day10"));
				pojo.setDay11(results.getString("day11"));
				pojo.setDay12(results.getString("day12"));
				pojo.setDay13(results.getString("day13"));
				pojo.setDay14(results.getString("day14"));
				pojo.setDay15(results.getString("day15"));
				pojo.setDay16(results.getString("day16"));
				pojo.setDay17(results.getString("day17"));
				pojo.setDay18(results.getString("day18"));
				pojo.setDay19(results.getString("day19"));
				pojo.setDay20(results.getString("day20"));
				pojo.setDay21(results.getString("day21"));
				pojo.setDay22(results.getString("day22"));
				pojo.setDay23(results.getString("day23"));
				pojo.setDay24(results.getString("day24"));
				pojo.setDay25(results.getString("day25"));
				pojo.setDay26(results.getString("day26"));
				pojo.setDay27(results.getString("day27"));
				pojo.setDay28(results.getString("day28"));
				pojo.setDay29(results.getString("day29"));
				pojo.setDay30(results.getString("day30"));
				pojo.setDay31(results.getString("day31"));
				pojo.setDay32(results.getString("comments"));
				result.add(pojo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return result;
	}
	
	public List<YearWiseReportDataPojo> yearWiseSearchReportUpdate(YearWiseReportDataPojo yearwisedatapojo){
		
		try {
			String[] date = yearwisedatapojo.getYear().split("-");
			if(yearwisedatapojo.getDaynumber().equalsIgnoreCase("day32")){
				yearwisedatapojo.setDaynumber("comments");
			}
			String query = "update stockyearwisedata set " +yearwisedatapojo.getDaynumber()+"=? where year=? and month=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, yearwisedatapojo.getDayvalue());
			statement.setString(2, date[0]);
			statement.setString(3, date[1]);
			statement.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<YearWiseReportDataPojo> result = yearWiseSearchReport(yearwisedatapojo);		
		return result;
	}
	
	

}
