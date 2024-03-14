package com.example.TP2Spring.agenda;


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
	public String Connexion() {
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
	public String add (@RequestParam String titre,@RequestParam String mail,@RequestParam String mdp,Model model) {
		Personne proprio = service.findPersonne(mail, mdp);
		serviceAgenda.ajouterAgenda(titre,proprio.getId());
		
		Iterable<Agenda> agendas = serviceAgenda.getAllAgenda();
		model.addAttribute("agendas", agendas);
	
		return "redirect:/agenda/connexion";
	}
	
}
