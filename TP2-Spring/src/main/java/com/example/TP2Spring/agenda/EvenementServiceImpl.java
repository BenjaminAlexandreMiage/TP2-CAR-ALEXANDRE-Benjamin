package com.example.TP2Spring.agenda;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EvenementServiceImpl implements EvenementService {
	
	@Autowired
	private EvenementRepository repoEvenement;

	@Override
	public void ajouterEvenement(Long idAgenda, String titre, Date date) {
		repoEvenement.save(new Evenement(idAgenda,titre,date));
		
	}

	@Override
	public Iterable<Evenement> getAllEvenement() {
		return repoEvenement.findAll();
	}
	
	@Override
	public Iterable<Evenement> getEvenementOfAgenda(Long idAgenda){
		return repoEvenement.findByIdAgenda(idAgenda);
	}
	
	@Override
	public void deleteEvenement(Long idEvenement) {
		repoEvenement.deleteById(idEvenement);
	}
	
}
