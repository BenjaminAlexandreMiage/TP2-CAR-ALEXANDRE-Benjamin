package com.example.TP2Spring.agenda;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Agenda {

	private Long id;
	private Long idProprietaire;
	private String titre;
	
	
	public Agenda(Long idProprietaire,String titre) {
		this.idProprietaire = idProprietaire;
		this.titre = titre;
	}
	
	public Agenda() {}
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdProprietaire() {
		return idProprietaire;
	}
	public void setIdProprietaire(Long idProprietaire) {
		this.idProprietaire = idProprietaire;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	
	
}
