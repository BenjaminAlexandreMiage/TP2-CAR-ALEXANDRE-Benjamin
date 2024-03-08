package com.example.TP2Spring.agenda;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface PersonneRepository extends CrudRepository<Personne, Long> {
	
	public Iterable <Personne> findByMailAndMdp( @Param("mail") String mail, @Param("mdp") String mdp );
	
	public Iterable <Personne> findByMail( @Param("mail") String mail);
	
	
}
