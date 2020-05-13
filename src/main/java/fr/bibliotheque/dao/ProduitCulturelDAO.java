package fr.bibliotheque.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.bibliotheque.entities.ProduitCulturel;

@Repository
public interface ProduitCulturelDAO<T extends ProduitCulturel> extends CrudRepository<T,Long> {
	
	List<ProduitCulturel> findByProduitType(String produitType);
	
}




