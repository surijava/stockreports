package com.stockmarket.models;

import java.util.List;

import com.stockmarket.dao.EventDAO;
import com.stockmarket.pojos.FiiDiiDataPojo;
import com.stockmarket.pojos.FiiDiiOpenInterestPojo;
import com.stockmarket.pojos.StockPojo;

public class DerivatiesDashboardModel {


	EventDAO dao  = new EventDAO();

	public List<StockPojo> getEvents() throws Exception{
		return dao.getEvents();
	}

	public List<StockPojo> derivatiesWeeklyEvents() throws Exception{
		return dao.derivatiesWeeklyEvents();
	}

	public List<FiiDiiOpenInterestPojo> derivatiesPositions() throws Exception{
		return dao.derivatiesPositions();
	}

	public List<FiiDiiDataPojo>  getFIIDIIData() throws Exception{
		return dao.getFIIDIIData();
	}
	
}
