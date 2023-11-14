package com.stockmarket.models;

import java.util.List;
import java.util.Map;

import com.stockmarket.dao.DerivatiesDAO;
import com.stockmarket.pojos.DayReportPojo;
import com.stockmarket.pojos.StockPojo;

public class DerivatiesGainerLosersModel {

	DerivatiesDAO dao = new DerivatiesDAO();

	public List<DayReportPojo> allGainerLoserList( StockPojo stockpojo){

		return null;

	}
	public Map<String,List<DayReportPojo>> gainerList( StockPojo stockpojo) throws Exception{

		stockpojo.setProfitType("gain");
		Map<String,List<DayReportPojo>> gainersDataMap = dao.derivatiesGainerLosers(stockpojo);

		return gainersDataMap;

	}
	public Map<String,List<DayReportPojo>> loserList( StockPojo stockpojo) throws Exception{

		stockpojo.setProfitType("lose");
		Map<String,List<DayReportPojo>> losersDataMap = dao.derivatiesGainerLosers(stockpojo);

		return losersDataMap;

	}
}
