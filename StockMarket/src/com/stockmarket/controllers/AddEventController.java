package com.stockmarket.controllers;

import java.sql.SQLException;
import java.text.ParseException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.stockmarket.models.EventModel;
import com.stockmarket.pojos.StockPojo;

@Controller
public class AddEventController {

	@RequestMapping(value = "/addevent" , method = RequestMethod.GET)
	public ModelAndView createGet(@ModelAttribute("SpringWeb")StockPojo stockdetails, 

			ModelMap model) throws SQLException, ParseException {

		return new ModelAndView("addevent", "message","");
	}

	@RequestMapping(value = "/addevent" , method = RequestMethod.POST)
	public ModelAndView create(@ModelAttribute("SpringWeb")StockPojo stockdetails, 

			ModelMap model) throws SQLException, ParseException {

		String message = null;

		if(stockdetails.getChoice() != null && !stockdetails.getChoice().equalsIgnoreCase("0")){
			EventModel eventModel = new EventModel();
			message = eventModel.addEvent(stockdetails);

			if(message.equalsIgnoreCase("success")){
				message = "success";
			}else{
				message ="";
			}

		}

		return new ModelAndView("addevent", "message", message);
	}


	@RequestMapping(value = "/sharecorporatedevent" , method = RequestMethod.GET)
	public ModelAndView addShareCorporateEvent(@ModelAttribute("SpringWeb")StockPojo stockdetails, 

			ModelMap model) throws SQLException, ParseException {

		return new ModelAndView("corporarateevent", "message","");
	}

	@RequestMapping(value = "/sharecorporatedevent" , method = RequestMethod.POST)
	public ModelAndView addShareCorporateEventPost(@ModelAttribute("SpringWeb")StockPojo stockdetails, 

			ModelMap model) throws SQLException, ParseException {

		String message = null;

		if(stockdetails.getChoice() != null && !stockdetails.getChoice().equalsIgnoreCase("0")){
			EventModel eventModel = new EventModel();
			message = eventModel.addShareCorporateEvent(stockdetails);

			if(message.equalsIgnoreCase("success")){
				message = "success";
			}else{
				message ="";
			}

		}

		return new ModelAndView("corporarateevent", "message", message);
	}
}

