package fr.bibliotheque.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.bibliotheque.dao.BibliothequeDAO;
import fr.bibliotheque.entities.Bibliotheque;

@Service
public class BibliothequeService {
	
	@Autowired
	BibliothequeDAO bibliothequeDAO;
	
	
	public Bibliotheque getBibliotheque(Long idBibliotheque){
		Optional<Bibliotheque> bibliotheque=bibliothequeDAO.findById(idBibliotheque);
		return bibliotheque.isPresent()?bibliotheque.get():new Bibliotheque() ;
	}
	

}
