package com.example.TP2Spring.agenda;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Evenement {

	private Long id;
	private Long idAgenda;
	private String titre;
	private Date date;
	
	public Evenement(Long idAgenda,String titre,Date date) {
		this.idAgenda = idAgenda;
		this.titre = titre;
		this.date = date; 
	}
	
	public Evenement() {}

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdAgenda() {
		return idAgenda;
	}

	public void setIdAgenda(Long idAgenda) {
		this.idAgenda = idAgenda;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	
	
}
