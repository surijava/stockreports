package com.stockmarket.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.stockmarket.connections.MySqlConnection;
import com.stockmarket.pojos.StockPojo;

@Controller
public class CreateController {
	
	@RequestMapping(value = "/create" , method = RequestMethod.POST)
	public ModelAndView create(@ModelAttribute("SpringWeb")StockPojo stockdetails, 
			   
			   ModelMap model) throws SQLException, ParseException {

		String message = null; 
		//stockData(stockdetails);
		
		
		return new ModelAndView("create", "message", message);
	}
	@RequestMapping(value = "/dividend" , method = RequestMethod.POST)
	public ModelAndView dividend(@ModelAttribute("SpringWeb")StockPojo stockdetails, 
			   
			   ModelMap model) throws SQLException, ParseException {

		String message = null; 
		//stockData(stockdetails);
		
		
		return new ModelAndView("create", "message", message);
	}
	@RequestMapping(value = "/bonus" , method = RequestMethod.POST)
	public ModelAndView bonus(@ModelAttribute("SpringWeb")StockPojo stockdetails, 
			   
			   ModelMap model) throws SQLException, ParseException {

		String message = null; 
		//stockData(stockdetails);
		
		
		return new ModelAndView("create", "message", message);
	}
	
	@RequestMapping(value = "/split" , method = RequestMethod.POST)
	public ModelAndView home(@ModelAttribute("SpringWeb")StockPojo stockdetails, 
			   
			   ModelMap model) throws SQLException, ParseException {

		String message = null; 
		//stockData(stockdetails);
		
		
		return new ModelAndView("create", "message", message);
	}
	
	
	@SuppressWarnings("unused")
	private String stockData(StockPojo stockdetails) throws SQLException, ParseException{

		String message = null;
		
		Connection connection = MySqlConnection.getConnection();
		
		String query = "insert into stocks(stockname,bsescriptid,bsescriptcode,nsescriptcode,"
				+ "sector,industrytype,captype,companytype,capitalvalue,facevalue,bookvalue,"
				+ "pevalue,parentgroupname,ipoyear,numberofsharesissued,promoterstake,"
				+ "publicshares,indianpromoters,forgienpromoters,fiidii,importantpoints,reference)"
				+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, stockdetails.getStockname());
		statement.setString(2, stockdetails.getBsescriptid());
		statement.setString(3, stockdetails.getBsescriptcode());
		statement.setString(4, stockdetails.getNsescriptcode());
		statement.setString(5, stockdetails.getSector());
		statement.setString(6, stockdetails.getIndustrytype());
		statement.setString(7, stockdetails.getCaptype());
		statement.setString(8, stockdetails.getCompanytype());
		statement.setString(9, stockdetails.getCapitalvalue());
		statement.setString(10, stockdetails.getFacevalue());
		statement.setString(11, stockdetails.getBookvalue());
		statement.setString(12, stockdetails.getPevalue());
		statement.setString(13, stockdetails.getParentgroupname());
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");  
		Date date1 = formatter.parse(stockdetails.getIpoyear()); 
		statement.setDate(14, new java.sql.Date(date1.getTime()));
		statement.setString(15, stockdetails.getNumberofsharesissued());
		statement.setString(16, stockdetails.getPromoterstake());
		statement.setString(17, stockdetails.getPublicshares());
		statement.setString(18, stockdetails.getIndianpromoters());
		statement.setString(19, stockdetails.getForgienpromoters());
		statement.setString(20, stockdetails.getFiidii());
		statement.setString(21, stockdetails.getImportantpoints());
		statement.setString(22, stockdetails.getReference());
		int count = statement.executeUpdate();
		
		if(count == 1){
			message = "success";
		}else{
			message = "fail";
		}
		return message;
	}

}
