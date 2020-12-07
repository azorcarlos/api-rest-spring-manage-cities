package com.managecities.importcsv.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.managecities.importcsv.Helpers.CSVHelper;
import com.managecities.importcsv.model.ResultImportDTO;
import com.managecities.importcsv.services.CSVService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/upload")
@Api(value = "Upload File")
public class UploadController {
	
	@Autowired
	CSVService csvService;
	
	
	@PostMapping
	@Transactional
	@ApiOperation(value = "Upload csv cities")
	public ResponseEntity<?>upload(@RequestParam("file") MultipartFile file){
		if(CSVHelper.hasCSVFormat(file)){
			csvService.save(file);
			
		}else {
			return ResponseEntity.status(HttpStatus.CREATED)
				     .body(new ResultImportDTO(file.getOriginalFilename().toString(),"Formato de arquivo não suportado",false));
			
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						     .body(new ResultImportDTO(file.getOriginalFilename().toString(),"Arquivo Importado com sucesso!",true));
		
	}
	

}
