package com.fup.base;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.fup.entities.Employee;

public class CSVHelper {
	public static String TYPE = "text/csv";
	  static String[] HEADERs = { "Id", "Title", "Description", "Published" };

	  public static boolean hasCSVFormat(MultipartFile file) {

	    if (!TYPE.equals(file.getContentType())) {
	      return false;
	    }

	    return true;
	  }

	  public static List<Employee> csvToEmployees(InputStream is) {
	    try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	        CSVParser csvParser = new CSVParser(fileReader,
	            CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
int count=1;
int cnt=0;
Map<CSVRecord, Integer> baseMap = new HashMap<CSVRecord, Integer>();
	      List<Employee> emps = new ArrayList<Employee>();

	      Iterable<CSVRecord> csvRecords = csvParser.getRecords();

	      for (CSVRecord csvRecord : csvRecords) {
          		if (!baseMap.containsKey(csvRecord)) {
          		baseMap.put(csvRecord, count);
          	
          		Employee emp = new Employee(
	              Long.parseLong(csvRecord.get("Id")),
	              csvRecord.get("Name"),
	              csvRecord.get("Designation"),
	              Integer.parseInt(csvRecord.get("age"))
	            );
          	
	       
          		emps.add(emp);
	        }else
	        	 baseMap.put(csvRecord, count++);
          		cnt++;
	      }
	      if(cnt>1000)
		        throw new RuntimeException("File containes more than 1000 duplicate records" );

	      return emps;
	    } catch (IOException e) {
	      throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
	    }
	  }

	/*  public static ByteArrayInputStream employeesToCSV(List<Employee> empdet) {
	    final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

	    try (ByteArrayOutputStream out = new ByteArrayOutputStream();
	        CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
	      for (Employee emp : empdet) {
	        List<String> data = Arrays.asList(
	              String.valueOf(emp.getId()),
	              emp.getName(),
	              emp.getDesignation(),
	              String.valueOf( emp.getAge())
	            );

	        csvPrinter.printRecord(data);
	      }

	      csvPrinter.flush();
	      return new ByteArrayInputStream(out.toByteArray());
	    } catch (IOException e) {
	      throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
	    }
	  }*/
	  }
