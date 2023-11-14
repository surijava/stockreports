package com.stockmarket.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.stockmarket.dao.SearchDAO;
import com.stockmarket.dao.YearWiseDataReportDAO;
import com.stockmarket.pojos.DayReportPojo;
import com.stockmarket.pojos.YearWiseReportDataPojo;

@Controller
public class YearWiseDataReportController {

	
	YearWiseDataReportDAO dao = new YearWiseDataReportDAO();
	
	@RequestMapping("/yearwisereportadd")
	public ModelAndView yearWiseReportAdd(@ModelAttribute("stock") YearWiseReportDataPojo yearwisedatapojo) throws Exception {
		
		if(yearwisedatapojo.getYear() != null){
			dao.insertYearWiseData(yearwisedatapojo);
		}
			
			return new ModelAndView("yearwisereportadd", "list", null);
				
	}
	
	@RequestMapping("/yearwisesearchreport")
	public ModelAndView yearWiseSearchReport(@ModelAttribute("stock") YearWiseReportDataPojo yearwisedatapojo) throws Exception {
		
		if(yearwisedatapojo.getYear() != null){
			List<YearWiseReportDataPojo> list = dao.yearWiseSearchReport(yearwisedatapojo);
			return new ModelAndView("yearwiesedatareport", "list", list);
			
		}else{
			return new ModelAndView("yearwiesedatareport", "list", null);
		}
		
				
	}
	
	@RequestMapping("/yearwisesearchreportupdate")
	public ModelAndView yearWiseSearchReportUpdate(@ModelAttribute("stock") YearWiseReportDataPojo yearwisedatapojo) throws Exception {
		
		if(yearwisedatapojo.getYear() != null){
			
			List<YearWiseReportDataPojo> list = dao.yearWiseSearchReportUpdate(yearwisedatapojo);
			return new ModelAndView("yearwiesedatareportupdate", "list", list);
			
		}else{
			return new ModelAndView("yearwiesedatareportupdate", "list", null);
		}
		
				
	}
	
	
	
	
}
