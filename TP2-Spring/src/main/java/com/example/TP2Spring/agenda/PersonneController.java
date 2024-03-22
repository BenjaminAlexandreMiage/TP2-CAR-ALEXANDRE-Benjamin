package com.example.TP2Spring.agenda;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/agenda")
public class PersonneController {

	@Autowired
	private PersonneService service;
	
	@Autowired
	private AgendaService serviceAgenda;
	
	@Autowired
	private EvenementService serviceEvenement;
	
	//Page d'accueil
	@GetMapping("/home")
	public String home() {
		return "/agenda/home";
	}
	
	//Ajouter un compte 
	@PostMapping("/add")
	public String add (@RequestParam String mail, @RequestParam String mdp, @RequestParam String nom, @RequestParam String prenom  ) {
		service.ajouterPersonne(mail,mdp,nom,prenom);
		return "redirect:/agenda/home";
	}
	
	//Ce connecte via la page de login
	@PostMapping("/connexion")
	public String Connexion(@RequestParam String mail,@RequestParam String mdp,HttpSession session,Model model) {
		Personne verifPersonne = service.findPersonne(mail,mdp);
		if(verifPersonne == null) {
			return "redirect:/agenda/home";
		}
		
		session.setAttribute("nom", verifPersonne.getNom());
		session.setAttribute("prenom", verifPersonne.getPrenom());
		session.setAttribute("mail", verifPersonne.getMail());
		session.setAttribute("mdp", verifPersonne.getMdp());
		session.setAttribute("id", verifPersonne.getId());
		
		Iterable<Agenda> agendas = serviceAgenda.getAgendaByIdProprietaire((Long) session.getAttribute("id"));
		model.addAttribute("agendas", agendas);
		
		return "agenda/connexion";
	}
	
	//Accès à la page utilisateur en get
	@GetMapping("/connexion")
	public String Connexion(Model model,HttpSession session) {
		
		String mail = (String) session.getAttribute("nom");
		
		if(mail==null) {
			return "redirect:/agenda/home";
		}
		
			
		Iterable<Agenda> agendas = serviceAgenda.getAgendaByIdProprietaire((Long) session.getAttribute("id"));
		model.addAttribute("agendas", agendas);
			
		return "agenda/connexion";
		
		
	}
	
	//Gère la deconnexion (et la suppression de la session
	@GetMapping("/deconnexion")
	public String Deconnexion(HttpSession session) {
		
		session.removeAttribute("nom");
		session.removeAttribute("prenom");
		session.removeAttribute("mail");
		session.removeAttribute("id");
		session.removeAttribute("mdp");
		
		return "redirect:/agenda/home";
	}
	
	
	//Ajout d'un agenda
	@PostMapping("/addAgenda")
	public String add (@RequestParam String titre,@RequestParam String mail,@RequestParam String mdp) {
		Personne proprio = service.findPersonne(mail, mdp);
		serviceAgenda.ajouterAgenda(titre,proprio.getId());
	
		return "redirect:/agenda/connexion";
	}
	
	//Accès à la page de l'agenda
	@PostMapping("/directionAgenda")
	public String directionAgenda (@RequestParam Long idAgenda,@RequestParam Long idProprietaire,Model model,HttpSession session) {
		Agenda verifAgenda = serviceAgenda.findAgenda(idAgenda);
		if(verifAgenda == null) {
			return "redirect:/agenda/connexion";
		}
		
		session.setAttribute("idProprietaire", verifAgenda.getIdProprietaire());
		session.setAttribute("idAgenda", verifAgenda.getId());
		session.setAttribute("agendaTitre", verifAgenda.getTitre());
		
		Iterable<Evenement> evenements = serviceEvenement.getEvenementOfAgenda((Long) session.getAttribute("idAgenda"));
		model.addAttribute("evenements", evenements);
		
		return "agenda/pageAgenda";
	}
	
	//Accès à la page de l'agenda en get 
	@GetMapping("/directionAgenda")
	public String directionAgenda(Model model,HttpSession session) {
		
		String mail = (String) session.getAttribute("nom");
		
		if(mail==null) {
			return "redirect:/agenda/home";
		}
			
		Iterable<Evenement> evenements = serviceEvenement.getEvenementOfAgenda((Long) session.getAttribute("idAgenda"));
		model.addAttribute("evenements", evenements);
			
		return "agenda/pageAgenda";
		
	}
	
	//Ajout d'un évenement
	@PostMapping("/addEvenement")
	public String addEvenement (@RequestParam String titre,@RequestParam String date,@RequestParam Long idAgenda) throws ParseException {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date d = dateFormat.parse(date);
		
		Agenda agenda = serviceAgenda.findAgenda(idAgenda);
		serviceEvenement.ajouterEvenement(agenda.getId(),titre,d);
	
		return "redirect:/agenda/directionAgenda";
	}
	
	//Quitté la page de l'agenda
	@GetMapping("/quitteAgenda")
	public String quitteAgenda(HttpSession session) {
		
		String mail = (String) session.getAttribute("nom");
		
		if(mail==null) {
			return "redirect:/agenda/home";
		}
		
		session.removeAttribute("idProprietaire");
		session.removeAttribute("idAgenda");
		session.removeAttribute("agendaTitre");
		
		return "redirect:/agenda/connexion";
	}
	
	//Supprimer un évenement
	@PostMapping("/supprimerEvenement")
	public String supprimerEvenement(@RequestParam Long idEvenement) {
		
		serviceEvenement.deleteEvenement(idEvenement);
		
		return "redirect:/agenda/directionAgenda";
	}
	
	//Affiche l'impression d'un agenda
	@PostMapping("/afficheImpression")
	public String afficheImpression(Model model,HttpSession session) {
		
		Iterable<Evenement> evenements = serviceEvenement.getEvenementOfAgenda((Long) session.getAttribute("idAgenda"));
		model.addAttribute("evenements", evenements);
		
		return "/agenda/pageImpression";
		
	}
	
}
