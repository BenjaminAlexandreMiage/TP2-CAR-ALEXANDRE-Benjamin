package com.example.TP2Spring.agenda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonneServiceImpl implements PersonneService {
	
	@Autowired
	private PersonneRepository repo;
	

	@Override
	public void ajouterPersonne(String mail,String mdp,String nom,String prenom){
		repo.save(new Personne(mail,mdp,nom,prenom));
	}

	@Override
	public Iterable<Personne> getAllPersonne() {
		return repo.findAll();
	}
	
	@Override
	public Personne findPersonne(String mail, String mdp) {
		Iterable<Personne> verifMail = repo.findByMail(mail);
		Personne personneTrouve = null;
		for(Personne p : verifMail) {
			if(p.getMdp().equals(mdp)){
				personneTrouve = p;
			}
		}
		return personneTrouve;
	}
	
}
