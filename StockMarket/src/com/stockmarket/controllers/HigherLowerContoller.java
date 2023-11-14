package com.stockmarket.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.stockmarket.dao.CommonDAO;
import com.stockmarket.dao.HigherLowerDAO;
import com.stockmarket.models.HigerLowerModels;
import com.stockmarket.pojos.DayReportPojo;
import com.stockmarket.pojos.StockPojo;

@Controller
public class HigherLowerContoller {
	
	
	@RequestMapping("/highhighlowerlower")
	public ModelAndView derivatiesMonthlyRange(@ModelAttribute("stock") StockPojo stockpojo) throws Exception {

		if(stockpojo.getMonth() ==0){
			return new ModelAndView("highhighlowerlower", "list", null);
		}else{
			
			ModelAndView view = new ModelAndView();
			
			HigerLowerModels highLoweModel = new HigerLowerModels();
			List<DayReportPojo>  results = highLoweModel.derivatiesMonthlyHighLowerResults(stockpojo);
			List<String> noOfDates = new CommonDAO().tradingDates(stockpojo);
			
			view.addObject("list", results);
			if(noOfDates.size() != 0 ){
				view.addObject("size", (results.size()) / noOfDates.size());
				
			}else{
				view.addObject("size","");
			}
			
			view.addObject("query", stockpojo);
			
			
			if(results.size() != 0){
				view.addObject("result",0);
			}
			
			return view;
		}
		
	}
	

}
