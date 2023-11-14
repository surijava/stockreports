package com.stockmarket.models;

import com.stockmarket.dao.EventDAO;
import com.stockmarket.pojos.StockPojo;

public class EventModel {

	public String addEvent(StockPojo stockdetails){

		EventDAO eventDao = new EventDAO();
		return eventDao.addEvent(stockdetails);
	}

	public String addShareCorporateEvent(StockPojo stockdetails){

		EventDAO eventDao = new EventDAO();
		return eventDao.addShareCorporateEvent(stockdetails);
	}

	

}
