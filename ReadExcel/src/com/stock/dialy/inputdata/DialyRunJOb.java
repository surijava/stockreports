package com.stock.dialy.inputdata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stockmarket.csv.DayReportPojo;

public class DialyRunJOb {

	public static Map<String,List<DayReportPojo>> dataMap = new HashMap<String,List<DayReportPojo>>();
	public static Map<String,List<DayReportPojo>> dataMapPT = new HashMap<String,List<DayReportPojo>>();
	
	public static void main(String[] args) throws Exception {

		NSEVCSVOperationDayWise eq = new NSEVCSVOperationDayWise();
		eq.readFileNames();
		eq.operation();
		eq.dataBaseOperation(dataMap);
		
		StockPivotelPoints pt = new StockPivotelPoints();
		pt.readFileNames();
		pt.operation();
		pt.dataBaseOperation(dataMap);
		
		StockRSI rsi = new StockRSI();
		rsi.data();
		rsi.saveRecords();
		
	}
}
