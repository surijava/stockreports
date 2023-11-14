package com.stockmarket.csv;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DerivatiesMonthlyRange {


	public static void main(String[] args) {

		Connection connection = MySqlConnection.getConnection();
		String searchquery ="select nsescriptcode from stocknseindexes where indextype='derivaties' order by nsescriptcode ";
		String query = "insert into stockderivatiesmonthlyrange (nsescriptcode,highvalue,lowvalue,month,year,monthlasttradeddate)"
				+ "values(?,?,?,?,?,?) ";
		
		SimpleDateFormat formatter1 = new SimpleDateFormat("ddMMyyyy"); 
		String year = "2019";
		

		try {

			PreparedStatement statement = connection.prepareStatement(searchquery);
			ResultSet resultSet = statement.executeQuery();
			int count = 0;
			while (resultSet.next()) {
				String nsescriptcode = resultSet.getString(1).trim();
				System.out.println(nsescriptcode+"--"+count);

				for (int i = 1; i <= 12; i++) {

					String maxdatequery = "SELECT max(date) date,max(highvalue) high,min(lowvalue) low "
							+ " FROM stocksnsedaywisereport1 WHERE MONTH(date) ="+i +""
							+ " AND YEAR(date) = "+year+" and nsescriptcode='"+nsescriptcode+"' order by date";

					Statement statement2 = connection.createStatement(
							ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE
							);
					ResultSet resultSet2 = statement2.executeQuery(maxdatequery);
					if (resultSet2.next()) {
						
						PreparedStatement statement3 = connection.prepareStatement(query);
						
						String high = resultSet2.getString("high");
						System.out.println(high);
						String low = resultSet2.getString("low");
						statement3.setString(1, nsescriptcode);
						statement3.setString(2, high);
						statement3.setString(3, low);
						statement3.setInt(4, i);
						statement3.setString(5, year);
						String date = resultSet2.getString("date");
						Date date2 = formatter1.parse(date.split("-")[2]+date.split("-")[1]+date.split("-")[0]);  
						  java.sql.Date insertdate = new java.sql.Date(date2.getTime()); 
						  statement3.setDate(6,insertdate );
						statement3.executeUpdate();
						
					}

				}
				count++;
			}
		}catch(Exception e){
			e.printStackTrace();

		}
	}
}
