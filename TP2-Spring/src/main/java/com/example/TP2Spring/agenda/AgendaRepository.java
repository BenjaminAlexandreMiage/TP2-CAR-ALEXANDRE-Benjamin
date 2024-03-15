package com.example.TP2Spring.agenda;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AgendaRepository extends CrudRepository<Agenda, Long> {
	
	public Iterable <Personne> findByIdProprietaire( @Param("idProprietaire") Long idProprietaire);
	
	public Optional<Agenda> findById( @Param("id") Long id);
	
}
