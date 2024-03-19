package com.example.TP2Spring.agenda;

import java.util.Optional;

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
	
	@Override
	public Agenda findAgenda(Long id) {
		Optional<Agenda> verifid = repoAgenda.findById(id);
		Agenda agendaTrouve = null;
		if(verifid.get().getId().equals(id)){
			agendaTrouve = verifid.get();
		}
		return agendaTrouve;
	}
	
	@Override
	public Iterable<Agenda> getAgendaByIdProprietaire(Long idProprietaire){
		return repoAgenda.findByIdProprietaire(idProprietaire);
	}


}
