package com.example.TP2Spring.agenda;

import jakarta.persistence.*;

@Entity
public class Personne {
	
	private Long id;
	private String mail;
	private String mdp;
	private String nom;
	private String prenom;
	
	public Personne(String mail,String mdp,String nom,String prenom) {
		this.mail = mail;
		this.mdp = mdp;
		this.nom = nom;
		this.prenom = prenom;
	}
	
	public Personne() {}
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

}
