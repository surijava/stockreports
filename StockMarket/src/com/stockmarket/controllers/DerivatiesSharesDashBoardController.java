package com.stockmarket.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.stockmarket.dao.DerivatiesDAO;
import com.stockmarket.models.DerivatiesDashboardModel;
import com.stockmarket.pojos.DayReportPojo;
import com.stockmarket.pojos.FiiDiiDataPojo;
import com.stockmarket.pojos.FiiDiiOpenInterestPojo;
import com.stockmarket.pojos.StockPojo;

@Controller
public class DerivatiesSharesDashBoardController {
	
	
	@RequestMapping("/derivatessharesdashboard")
	public ModelAndView derivatiesMonthlyRange(@ModelAttribute("stock") StockPojo stockpojo) throws Exception {

		
			DerivatiesDashboardModel model = new DerivatiesDashboardModel();
			
			List<StockPojo> eventsList = model.getEvents();
			List<FiiDiiOpenInterestPojo> openInterestList = model.derivatiesPositions();
			List<StockPojo> sharesEventsList = model.derivatiesWeeklyEvents();
			List<FiiDiiDataPojo> fiiDiiList = model.getFIIDIIData();
			
			ModelAndView view = new ModelAndView();
			view.addObject("eventList", eventsList);
			view.addObject("openInterestList", openInterestList);
			view.addObject("sharesEventsList", sharesEventsList);
			view.addObject("fiiDiiList", fiiDiiList);
			
			view.addObject("query", stockpojo);
			
			return view; 
		
	}

}
