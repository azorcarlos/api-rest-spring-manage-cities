package com.managecities.importcsv.Helpers;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import com.managecities.importcsv.model.Cities;




public class CSVHelper {
	
	public static String TIPO = "text/csv";
	public static String[] HEADERS = {"ibge_id","uf","name","capital","lon","lat","no_accents","alternative_names","microregion","mesoregion"};
	
	
	public static boolean hasCSVFormat(MultipartFile file) {
	    if (TIPO.equals(file.getContentType())
	    		|| file.getContentType().equals("application/vnd.ms-excel")) {
	      return true;
	    }

	    return false;
	  }
	
	public static List<Cities> csvToTutorials(InputStream is) {
		try {
			BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			
			@SuppressWarnings("resource")
			
			CSVParser csvParser = new CSVParser(fileReader,CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
			
			Iterable<CSVRecord> csvRecords = csvParser.getRecords();
	
			List<Cities> listaCidades = new ArrayList<>();
			for (CSVRecord csvRecord : csvRecords) {
		    	  Cities city = new Cities();
		    	  
		    	  city.setIbgeId(Long.parseLong(csvRecord.get("ibge_id")));
		    	  city.setUf(csvRecord.get("uf"));
		    	  city.setName(csvRecord.get("name"));
		    	  city.setCapital(csvRecord.get("capital"));
		    	  city.setLon(csvRecord.get("lon").toString());
		    	  city.setLat(csvRecord.get("lat"));
		    	  city.setNoAccents(csvRecord.get("no_accents"));
		    	  city.setAlternativeNames(csvRecord.get("alternative_names"));
		    	  city.setMicroregion(csvRecord.get("microregion"));
		    	  city.setMesoregion(csvRecord.get("mesoregion"));
		    	  
	           
		    	  listaCidades.add(city);
		      }
			
			return listaCidades;
			
		} catch (Exception e) {
			
			throw new RuntimeException("Ocorreu uma falha ao ler o arquivo: " + e.getMessage());
		}
	  }


}
