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
	
	@GetMapping("/home")
	public String home() {
		return "/agenda/home";
	}
	
	@PostMapping("/add")
	public String add (@RequestParam String mail, @RequestParam String mdp, @RequestParam String nom, @RequestParam String prenom  ) {
		service.ajouterPersonne(mail,mdp,nom,prenom);
		return "redirect:/agenda/home";
	}
	
	@PostMapping("/connexion")
	public String Connexion(@RequestParam String mail,@RequestParam String mdp,HttpSession session) {
		Personne verifPersonne = service.findPersonne(mail,mdp);
		if(verifPersonne == null) {
			return "redirect:/agenda/home";
		}
		
		session.setAttribute("nom", verifPersonne.getNom());
		session.setAttribute("prenom", verifPersonne.getPrenom());
		session.setAttribute("mail", verifPersonne.getMail());
		session.setAttribute("mdp", verifPersonne.getMdp());
		session.setAttribute("id", verifPersonne.getId());
		
		return "agenda/connexion";
	}
	
	@GetMapping("/connexion")
	public String Connexion(Model model) {
		Iterable<Agenda> agendas = serviceAgenda.getAllAgenda();
		model.addAttribute("agendas", agendas);
		
		return "agenda/connexion";
	}
	
	
	@GetMapping("/deconnexion")
	public String Deconnexion(HttpSession session) {
		
		session.removeAttribute("nom");
		session.removeAttribute("prenom");
		session.removeAttribute("mail");
		session.removeAttribute("id");
		session.removeAttribute("mdp");
		
		return "redirect:/agenda/home";
	}
	
	
	@PostMapping("/addAgenda")
	public String add (@RequestParam String titre,@RequestParam String mail,@RequestParam String mdp) {
		Personne proprio = service.findPersonne(mail, mdp);
		serviceAgenda.ajouterAgenda(titre,proprio.getId());
	
		return "redirect:/agenda/connexion";
	}
	
	@PostMapping("/directionAgenda")
	public String directionAgenda (@RequestParam Long idAgenda,@RequestParam Long idProprietaire,HttpSession session) {
		Agenda verifAgenda = serviceAgenda.findAgenda(idAgenda);
		if(verifAgenda == null) {
			return "redirect:/agenda/connexion";
		}
		
		session.setAttribute("idProprietaire", verifAgenda.getIdProprietaire());
		session.setAttribute("idAgenda", verifAgenda.getId());
		session.setAttribute("agendaTitre", verifAgenda.getTitre());
		
		return "agenda/pageAgenda";
	}
	
	
	@GetMapping("/directionAgenda")
	public String directionAgenda(Model model) {
		Iterable<Evenement> evenements = serviceEvenement.getAllEvenement();
		model.addAttribute("evenements", evenements);
		
		return "agenda/pageAgenda";
	}
	
	
	@PostMapping("/addEvenement")
	public String addEvenement (@RequestParam String titre,@RequestParam String date,@RequestParam Long idAgenda) throws ParseException {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date d = dateFormat.parse(date);
		
		Agenda agenda = serviceAgenda.findAgenda(idAgenda);
		serviceEvenement.ajouterEvenement(agenda.getId(),titre,d);
	
		return "redirect:/agenda/directionAgenda";
	}
	
}
