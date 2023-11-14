package com.stockmarket.controllers;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.stockmarket.dao.SearchDAO;
import com.stockmarket.pojos.DayReportPojo;
import com.stockmarket.pojos.StockPojo;
import com.stockmarket.pojos.YearWiseReportDataPojo;

@Controller
public class SearchController {

	SearchDAO dao = new SearchDAO();
	
	@RequestMapping("/stockdayreport")
	public ModelAndView search() throws SQLException {

		return new ModelAndView("results");
	}
	
	@RequestMapping("/singleStockDayWiseSearch")
	public ModelAndView singleStockDayWiseSearch(@ModelAttribute("stock") StockPojo stockpojo) throws Exception {

		if(stockpojo.getNsescriptcode() == null){
			return new ModelAndView("results", "list", null);
		}else{
			List<DayReportPojo> list = dao.singleStockDayWiseSearchReport(stockpojo);
			return new ModelAndView("results", "list", list);
		}
	}
	
	
	@RequestMapping("/marginstocksreportresults")
	public ModelAndView marginStocksSarch(@ModelAttribute("stock") StockPojo stockpojo) throws Exception {

		if(stockpojo.getMargin() == null){
			return new ModelAndView("marginstockreport", "list", null);
		}else{
			List<DayReportPojo> list = dao.marginStocksSarchReport(stockpojo);
			return new ModelAndView("marginstockreport", "list", list);
		}
		
	}
	
	@RequestMapping("/profitreportresults")
	public ModelAndView profitStocksSarch(@ModelAttribute("stock") StockPojo stockpojo) throws Exception {

		if(stockpojo.getProfitType() == null){
			return new ModelAndView("profitstockreport", "list", null);
		}else{
			SearchDAO dao = new SearchDAO();
			List<DayReportPojo> list = dao.profitStocksSarchReport(stockpojo);
			return new ModelAndView("profitstockreport", "list", list);
		}
		
	}
	
	@RequestMapping("/yearlowhighreportresults")
	public ModelAndView yearLowHIghStocksSarch(@ModelAttribute("stock") StockPojo stockpojo) throws Exception {
		
		if(stockpojo.getProfitType() == null){
			return new ModelAndView("yearlowhigh", "list", null);
		}else{
			SearchDAO dao = new SearchDAO();
			List<DayReportPojo> list = dao.yearLowHIghStocksSarch(stockpojo);
			return new ModelAndView("yearlowhigh", "list", list);
		}
		
	}
	
	@RequestMapping("/daytrading")
	public ModelAndView dayTrading(@ModelAttribute("stock") StockPojo stockpojo) throws Exception {
		
		ModelAndView model = new ModelAndView("dayTrading");
		if(stockpojo.getProfitType() != null){
			
			List<DayReportPojo> list = dao.dayTrading(stockpojo);
			model.addObject("list", list);
			
			if(stockpojo.getProfitType().equals("buy")){
				model.addObject("buy", "Buy");
			}else{
				model.addObject("buy", "Sell");
			}
		}	
		return model;
	}
	
	
	
}
