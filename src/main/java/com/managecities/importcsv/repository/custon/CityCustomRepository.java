package com.managecities.importcsv.repository.custon;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.managecities.importcsv.model.Cities;

@Repository
public class CityCustomRepository {

	private EntityManager em;

	public CityCustomRepository(EntityManager em) {
		this.em = em;
	}

	@SuppressWarnings("unchecked")
	public List<Cities> findCitiesInLabel(String field, String value) {

		String query = "select * from cities where " + field + " like '%" + value + "%'";
		var q = em.createNativeQuery(query, Cities.class);

		return q.getResultList();

	}

	public List<?> findCountCitiesInLabel(String field) {

		String query = "select distinct " + field + " as field, count(*) as qtd from cities group by  " + field;
		var q = em.createNativeQuery(query);

		return q.getResultList();

	}

}
