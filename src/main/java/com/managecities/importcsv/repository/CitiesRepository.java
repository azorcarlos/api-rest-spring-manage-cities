package com.managecities.importcsv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.managecities.importcsv.model.Cities;
import com.managecities.importcsv.model.CitiesInterfaceTotalUf;

public interface CitiesRepository extends JpaRepository<Cities, Long> {
	@Modifying
	@Query(value = "truncate table cities", nativeQuery = true)
	void truncateMyTable();

	List<Cities> findByCapitalOrderByName(String Capital);

	@Query(value = "select uf,count(*) as qtd  from cities group by uf order by uf", nativeQuery = true)
	List<CitiesInterfaceTotalUf> findByCountCitiesUf();

	@Query(value = " select *                                                 "
			+ "  from (select uf,count(*) as qtd from cities group by uf) dta  "
			+ "   where dta.qtd in (                                           "
			+ " select max(dta.qtd)                                            "
			+ "  from (select uf,count(*) as qtd from cities group by uf) dta) "
			+ "  union                                                         "
			+ "  select *                                                      "
			+ "  from (select uf,count(*) as qtd from cities group by uf) dta  "
			+ "   where dta.qtd in (                                           "
			+ " select min(dta.qtd)                                            "
			+ "  from (select uf,count(*) as qtd from cities group by uf) dta) ", nativeQuery = true)
	List<CitiesInterfaceTotalUf> findByMinMaxCities();

	List<Cities> findByUfOrderByName(String Uf);



}
