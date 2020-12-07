package com.managecities.importcsv.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/")
@Api(value = "Welcome")

public class Index {

	@ApiOperation(value = "Index")
	@GetMapping
	public List<?>index(){
		
		List<String> list = new ArrayList<String>();
		list.add("Welcome to the city management service");
		list.add("Whagger Url: http://localhost:8085/swagger-ui.html#/index/indexUsingGET");
		list.add(new Date().toString());
		
		return list;
	}
}
