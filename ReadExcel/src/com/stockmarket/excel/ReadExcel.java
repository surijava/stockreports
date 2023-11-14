package com.stockmarket.excel;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.stockmarket.csv.MySqlConnection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ReadExcel {
	
	private static final String FILE_NAME = "C:/Users/surendra.chebrolu/Downloads/EQ050218.xlsx";
	private static final String filedate ="05-02-2018";
	  
	
	public static void main(String[] args) {
		
		Map<Integer,List<String>> scripsMap = new HashMap<Integer,List<String>>();
		
		try {

            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();
            int count = 1;
            while (iterator.hasNext()) {

            	List<String> readDataList = new ArrayList<String>();
                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();
                int cellcount = 0;
                while (count !=0 && cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    if (cell != null)
                    {
                           cell.setCellType(Cell.CELL_TYPE_STRING);
                           switch (cell.getCellType())
                           {
                                  case Cell.CELL_TYPE_STRING:
                                         break;
                                  case Cell.CELL_TYPE_BOOLEAN:
                                  case Cell.CELL_TYPE_NUMERIC:
                                         cell.setCellType(Cell.CELL_TYPE_STRING);
                                         break;
                                  default:
                                         break;
                           }
                    }
                    readDataList.add(cellcount,cell.getStringCellValue());
                    cellcount++;
                }
                scripsMap.put(count, readDataList);
                
                count++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		performDataBaseOperation(scripsMap);
    }
	
	private static void performDataBaseOperation(Map<Integer,List<String>> scripsMap){
		
		 try {
			 System.out.println(scripsMap.values());
			  String query = "insert into stocksdaydata (bsescriptid,bsescriptcode,"
			  		+ "scgroup,sctype,openvalue,highvalue,lowvalue,closevalue,"
			  		+ "previousclosevalue,nooftrades,noofshares,turnover,tdcloindi,date) "
			  		+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			  Connection connection = MySqlConnection.getConnection();
			  PreparedStatement statement = connection.prepareStatement(query);
			  Collection<List<String>> values = scripsMap.values();
			  Iterator it = values.iterator();
			  while (it.hasNext()) {
				  List<String> stockDataList = (List) it.next();
				  statement.setString(1, stockDataList.get(0));
				  statement.setString(2, stockDataList.get(1));
				  statement.setString(3, stockDataList.get(2));
				  statement.setString(4, stockDataList.get(3));
				  statement.setString(5, stockDataList.get(4));
				  statement.setString(6, stockDataList.get(5));
				  statement.setString(7, stockDataList.get(6));
				  statement.setString(8, stockDataList.get(7));
				  statement.setString(9, stockDataList.get(8));
				  statement.setString(10, stockDataList.get(9));
				  statement.setString(11, stockDataList.get(10));
				  statement.setString(12, stockDataList.get(11));
				  statement.setString(13, stockDataList.get(12));
				  SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");  
				  Date date1 = formatter.parse(filedate); 
				  statement.setDate(14, new java.sql.Date(date1.getTime()));
				  statement.executeUpdate();
			}
			
		} catch (Exception e) {
				System.out.println(e);
		}
		
	}

}
