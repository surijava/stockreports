package com.stockmarket.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import com.stockmarket.dao.HigherLowerDAO;
import com.stockmarket.pojos.DayReportPojo;
import com.stockmarket.pojos.StockPojo;

public class HigerLowerModels {


	public Map<String,List<DayReportPojo>> derivatiesMonthlyHigherLowerReport(StockPojo stockpojo) throws Exception{

		Map<String,List<DayReportPojo>> finalResults = new TreeMap<String,List<DayReportPojo>>();

		HigherLowerDAO dao = new HigherLowerDAO();
		Map<String,List<DayReportPojo>>  results = dao.derivatiesMonthlyHigherLowerReport(stockpojo);

		double nextDay = 0.0;
		double currentDay = 0.0;
		int count = 0;

		for (String nsecode : results.keySet()) {

			count = 0;

			List<DayReportPojo> dataValues = results.get(nsecode);

			if(stockpojo.getChoice().equalsIgnoreCase("reverstrend")){

				for (int i = 0; i < dataValues.size()-2; i++) {
					
					DayReportPojo currentDaypojo = dataValues.get(i);
					double day = currentDaypojo.getHigh();
					double dayOne = dataValues.get(i+1).getHigh();
					double dayTwo = dataValues.get(i+2).getHigh();

					if(day > dayOne){
						if(day > dayTwo){
							finalResults.put(nsecode, results.get(nsecode));
						}
					}
				}

			}else{			


				for (int i = 0; i < dataValues.size()-1; i++) {

					if(!stockpojo.getRsi().equalsIgnoreCase("0")){

						DayReportPojo currentDaypojo = dataValues.get(dataValues.size()-1);

						String rsiValue=stockpojo.getRsi();
						char symbol = (char) rsiValue.indexOf(0);

						if(rsiValue.contains(">")){

							if(Double.parseDouble(currentDaypojo.getRsi()) > Double.parseDouble(rsiValue.replace(">","").trim())){
								count = 5;
								break;
							}
						}else{
							if(Double.parseDouble(currentDaypojo.getRsi()) < Double.parseDouble(rsiValue.replace("<","").trim())){
								count = 5;
								break;
							}

						}
					}

					if(stockpojo.getChoice().equalsIgnoreCase("hightoday")){
						DayReportPojo currentDaypojo = dataValues.get(dataValues.size()-1);
						if(currentDaypojo.getRange() > 10){
							count = 5;
							break;
						}
					}

					if(stockpojo.getChoice().equalsIgnoreCase("lowtoday")){
						DayReportPojo currentDaypojo = dataValues.get(dataValues.size()-1);
						if(currentDaypojo.getRange() < -10){
							count = 5;
							break;
						}
					}

					if(stockpojo.getChoice().equalsIgnoreCase("hightodayfall")){
						DayReportPojo currentDaypojo = dataValues.get(dataValues.size()-1);
						if(currentDaypojo.getRange() > 10 && (currentDaypojo.getChange() < 0) ){
							count = 5;
							break;
						}
					}



					if(stockpojo.getChoice().equalsIgnoreCase("lowtodayup")){
						DayReportPojo currentDaypojo = dataValues.get(dataValues.size()-1);
						if(currentDaypojo.getRange() < -10 && (currentDaypojo.getChange() > 0)){
							count = 5;
							break;
						}
					}

					if(stockpojo.getChoice().equalsIgnoreCase("hightrades")){
						DayReportPojo currentDaypojo = dataValues.get(i);
						if(currentDaypojo.getNoTrades() > 50000){
							count++;
						}
					}

					if(stockpojo.getChoice().equalsIgnoreCase("lowtrades")){
						DayReportPojo currentDaypojo = dataValues.get(i);
						if(currentDaypojo.getNoTrades() < 30000){
							count++;
						}
					}



					if(stockpojo.getChoice().equalsIgnoreCase("high")){

						DayReportPojo currentDaypojo = dataValues.get(i);
						currentDay = currentDaypojo.getHigh();
						DayReportPojo nextDaypojo = dataValues.get(i+1);
						nextDay = nextDaypojo.getHigh();
						if(nextDay >= currentDay){
							count++;
						}

					}
					if(stockpojo.getChoice().equalsIgnoreCase("lower")){

						DayReportPojo currentDaypojo = dataValues.get(i);
						currentDay = currentDaypojo.getLow();
						DayReportPojo nextDaypojo = dataValues.get(i+1);
						nextDay = nextDaypojo.getLow();

						if(nextDay <= currentDay){
							count++;
						}
					}

					if(stockpojo.getChoice().equalsIgnoreCase("uptrend")){

						DayReportPojo currentDaypojo = dataValues.get(i);
						if(currentDaypojo.getChange() > 0){
							count++;
						}
					}

					if(stockpojo.getChoice().equalsIgnoreCase("lowtrend")){

						DayReportPojo currentDaypojo = dataValues.get(i);
						if(currentDaypojo.getChange() < 0){
							count++;
						}
					}

					if(stockpojo.getChoice().equalsIgnoreCase("BreakOut")){

						/*DayReportPojo currentDaypojo = dataValues.get(i);
					currentDay = currentDaypojo.getHigh();
					DayReportPojo nextDaypojo = dataValues.get(i+1);
					nextDay = nextDaypojo.getHigh();
					if(nextDay >= currentDay){
						count++;
					}*/


						DayReportPojo currentDaypojo = dataValues.get(i);
						if(currentDaypojo.getChange() > 0){
							count++;
						}


						DayReportPojo currentDaypojoOne = dataValues.get(dataValues.size()-1);

						if(count >= 4 && currentDaypojoOne.getChange() <= 0){
							count ++;
						}

						DayReportPojo currentDaypojoTwo = dataValues.get(dataValues.size()-2);

						if(count >= 4 && currentDaypojoTwo.getChange() <= 0){
							count ++;
						}

					}




				}

				if( count >= 4){
					System.out.println(nsecode +"--------------------"+count);
					finalResults.put(nsecode, results.get(nsecode));
				}
			}
		}

		return finalResults;
	}

	public List<DayReportPojo> derivatiesMonthlyHighLowerResults(StockPojo stockpojo) throws Exception{

		HigherLowerDAO dao = new HigherLowerDAO();

		Map<String,List<DayReportPojo>> finalResults = derivatiesMonthlyHigherLowerReport(stockpojo);


		List<DayReportPojo> results = dao.derivatiesMonthlyHighLowerResults(stockpojo,finalResults.keySet());

		return results;

	}
}
