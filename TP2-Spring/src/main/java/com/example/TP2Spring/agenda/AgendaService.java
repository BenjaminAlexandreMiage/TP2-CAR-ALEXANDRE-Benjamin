package com.example.TP2Spring.agenda;

public interface AgendaService {
	
	void ajouterAgenda(String titre, Long idProprietaire);
	
	Iterable<Agenda> getAllAgenda();

	Agenda findAgenda(Long id);

	Iterable<Agenda> getAgendaByIdProprietaire(Long idProprietaire);
	
}
