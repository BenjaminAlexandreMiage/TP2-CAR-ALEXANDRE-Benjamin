package com.example.TP2Spring.agenda;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/agenda")
public class PersonneController {

	@Autowired
	private PersonneService service;
	
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
	public String Connexion(@RequestParam String mail,@RequestParam String mdp) {
		boolean verifPersonne = service.findPersonne(mail,mdp);
		if(verifPersonne == false) {
			return "redirect:/agenda/home";
		}
		
		return "agenda/connexion";
	}
	
	
	
}
