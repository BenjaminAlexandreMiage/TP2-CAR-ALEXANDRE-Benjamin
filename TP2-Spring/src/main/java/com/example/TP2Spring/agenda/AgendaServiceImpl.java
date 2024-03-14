package com.example.TP2Spring.agenda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendaServiceImpl implements AgendaService {
	
	
	@Autowired
	private AgendaRepository repoAgenda;

	
	@Override
	public void ajouterAgenda(String titre, Long idProprietaire) {
		repoAgenda.save(new Agenda(idProprietaire,titre));
		
	}

	@Override
	public Iterable<Agenda> getAllAgenda() {
		return repoAgenda.findAll();
	}

}
