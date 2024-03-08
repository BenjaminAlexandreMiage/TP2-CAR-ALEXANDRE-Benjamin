package com.example.TP2Spring.agenda;

public interface PersonneService {
	
	void ajouterPersonne(String mail,String mdp,String nom,String prenom);
	
	Iterable<Personne> getAllPersonne();
	
	Personne findPersonne(String mail, String mdp);

}
