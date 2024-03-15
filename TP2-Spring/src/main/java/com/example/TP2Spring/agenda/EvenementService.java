package com.example.TP2Spring.agenda;

import java.util.Date;

public interface EvenementService {
	
	void ajouterEvenement(Long idAgenda,String titre,Date date);
	
	Iterable<Evenement> getAllEvenement();
	
	Iterable<Evenement> getEvenementOfAgenda(Long idAgenda);
	
	void deleteEvenement(Long idEvenement);
	

}
