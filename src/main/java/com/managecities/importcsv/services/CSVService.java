package com.managecities.importcsv.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.managecities.importcsv.Helpers.CSVHelper;
import com.managecities.importcsv.model.Cities;
import com.managecities.importcsv.repository.CitiesRepository;

@Service
public class CSVService {

	@Autowired
	CitiesRepository citiesRepository;
	
	
	public void save(MultipartFile file) {
		
		try {
			
			List<Cities> cidades = CSVHelper.csvToTutorials(file.getInputStream());
		
			citiesRepository.truncateMyTable();
			citiesRepository.saveAll(cidades);
		} catch (Exception e) {
			throw new RuntimeException("Ocorreu um erro ao gravar o arquvo: " + e.getMessage());
		}
		
	}
	
}
