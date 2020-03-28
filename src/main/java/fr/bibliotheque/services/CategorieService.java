package fr.bibliotheque.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.bibliotheque.dao.CategorieDAO;
import fr.bibliotheque.entities.Categorie;

@Service
public class CategorieService {

	@Autowired
	CategorieDAO categorieDAO;
	
	public List<Categorie> getListeCategories(){
		
		List<Categorie> categories = new ArrayList<>();
	
		Iterable<Categorie> iterLivre= categorieDAO.findAll();
		iterLivre.forEach(categories::add);		
		
		return categories;		
	}
	
	public Categorie getCategorie(Long idCategorie){
		Optional<Categorie> categorie=categorieDAO.findById(idCategorie);
		return categorie.isPresent()?categorie.get():new Categorie() ;
	}
	
	
	public void enregistrerCategorie(Categorie categorie){	
		categorieDAO.save(categorie);
	}
	
	public void supprimerCategorie(Categorie categorie){
		
		categorieDAO.delete(categorie);

		
	}
	
}
