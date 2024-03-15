package com.example.TP2Spring.agenda;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface EvenementRepository extends CrudRepository<Evenement, Long> {

	public Iterable<Evenement> findByIdAgenda( @Param("idAgenda") Long idAgenda);
	

	
	
}
