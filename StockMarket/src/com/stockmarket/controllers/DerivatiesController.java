package com.stockmarket.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.stockmarket.dao.CommonDAO;
import com.stockmarket.dao.DerivatiesDAO;
import com.stockmarket.models.DerivatiesGainerLosersModel;
import com.stockmarket.pojos.DayReportPojo;
import com.stockmarket.pojos.StockPojo;

@Controller
public class DerivatiesController {

	
	@RequestMapping("/derivatiesdailyreport")
	public ModelAndView profitStocksSarch(@ModelAttribute("stock") StockPojo stockpojo) throws Exception {

		if(stockpojo.getMonth() ==0){
			
			ModelAndView view = new ModelAndView();
			view.addObject("list", null);
			view.addObject("result",0);
			return view;
		}else{
			DerivatiesDAO dao = new DerivatiesDAO();
			List<DayReportPojo> list = dao.derivatiesMonthlyReport(stockpojo);
			
			ModelAndView view = new ModelAndView();
			view.addObject("list", list);
			view.addObject("query", stockpojo);
			
			if(list.size() != 0){
				view.addObject("result",0);
			}
			return view;
		}
		
	}
	
	@RequestMapping("/derivatiesgainerloser")
	public ModelAndView derivatiesMonthlyRange(@ModelAttribute("stock") StockPojo stockpojo) throws Exception {

		if(stockpojo.getMonth() ==0){
			return new ModelAndView("derivatiesgainerloser", "list", null);
		}else{
			
			DerivatiesGainerLosersModel model = new DerivatiesGainerLosersModel();
			List<String> dates = new CommonDAO().tradingDates(stockpojo);
						
			ModelAndView view = new ModelAndView();
			view.addObject("gainerlist", model.gainerList(stockpojo));
			view.addObject("loserlist", model.loserList(stockpojo));
			view.addObject("dates", dates);
			
			return view;
		}
		
	}
}
