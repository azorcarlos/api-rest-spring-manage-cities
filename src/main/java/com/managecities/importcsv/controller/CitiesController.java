package com.managecities.importcsv.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.managecities.importcsv.model.Cities;
import com.managecities.importcsv.repository.CitiesRepository;
import com.managecities.importcsv.repository.custon.CityCustomRepository;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/cities")
@Api(value = "cities", protocols = "POST, DELETE")
public class CitiesController {

	@Autowired
	CitiesRepository citiesRepository;

	@Autowired
	CityCustomRepository cityCustomRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Cities save(@RequestBody Cities cities) {

		return citiesRepository.save(cities);

	}

	@DeleteMapping("/{idCity}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("idCity") String id) {

		var cityExistis = citiesRepository.findById(Long.parseLong(id));
		if (cityExistis.isPresent()) {
			citiesRepository.deleteById(cityExistis.get().getId());
		}

	}

	@GetMapping("/isCapital")
	public List<Cities> findCapital() {

		return citiesRepository.findByCapitalOrderByName("true");
	}

	@GetMapping("/ibgeEstados")
	public List<Cities> findIbgeEstados() {

		return citiesRepository.findByCapitalOrderByName("true");
	}

	@GetMapping("/totalCities")
	public Long fundCount() {

		return citiesRepository.count();
	}

	@GetMapping("/countCitiesUf")
	public List<?> countCitiesUf() {

		return citiesRepository.findByCountCitiesUf();

	}

	@GetMapping("/maxMinCities")
	public List<?> minMaxCities() {

		return citiesRepository.findByMinMaxCities();

	}

	@GetMapping("/detalhesCidadesPorUf/{uf}")
	public List<Cities> detalhesCidadesPorUf(@PathVariable("uf") String uf) {

		return citiesRepository.findByUfOrderByName(uf.toUpperCase());

	}

	@GetMapping("/findFieldFile/{field}/{value}")
	public List<Cities> findFieldFile(@PathVariable("field") String field, @PathVariable("value") String value) {

		var returnData = cityCustomRepository.findCitiesInLabel(field, value);
		return returnData;

	}

	@GetMapping("/findCountField/{field}")
	public List<?> findCountCitiesInLabel(@PathVariable("field") String field) {

		return cityCustomRepository.findCountCitiesInLabel(field);

	}

}
