package fr.bibliotheque;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import fr.bibliotheque.dao.BibliothequeDAO;
import fr.bibliotheque.dao.CategorieDAO;
import fr.bibliotheque.dao.EmpruntDAO;
import fr.bibliotheque.dao.LivreDAO;
import fr.bibliotheque.dao.PersonneDAO;
import fr.bibliotheque.entities.Bibliotheque;
import fr.bibliotheque.entities.Categorie;
import fr.bibliotheque.entities.Emprunt;
import fr.bibliotheque.entities.Livre;
import fr.bibliotheque.entities.Personne;

@Component
public class DataInit implements ApplicationRunner {

	@Autowired
	private PersonneDAO personneDAO;

	@Autowired
	private CategorieDAO categorieDAO;

	@Autowired
	private LivreDAO livreDAO;

	@Autowired
	private EmpruntDAO empruntDAO;

	@Autowired
	private BibliothequeDAO biblioDAO;

	@Value("${application.mode}")
	private String appMode;


	@Override
	public void run(ApplicationArguments args) throws Exception {
		

			long countBibliotheque = biblioDAO.count();
			
			Bibliotheque biblio = new Bibliotheque();

			// Instanciation Biblio
			if (countBibliotheque == 0) {

				biblio.setNomBibliotheque("Médiathèque Plein Nord ");
				biblioDAO.save(biblio);

			}

			// Instanciation Membres
			long countPers = personneDAO.count();

			Personne p1 = new Personne();
			Personne p2 = new Personne();
			Personne p3 = new Personne();

			if (countPers == 0) {
				p1.setNom("Smith");
				p1.setPrenom("Mael");
				p1.setEstResponsable(false);
				p1.setNumeroAdherent("2012");
				p1.setBibliotheque(biblio);

				p2.setNom("Smith");
				p2.setPrenom("Malo");
				p2.setEstResponsable(true);
				p2.setNumeroAdherent("2009");
				p2.setBibliotheque(biblio);

				p3.setNom("Smith");
				p3.setPrenom("Dad");
				p3.setEstResponsable(false);
				p3.setNumeroAdherent("1979");
				p3.setBibliotheque(biblio);

				personneDAO.save(p1);
				personneDAO.save(p2);
				personneDAO.save(p3);
			}
	
			if(appMode.equals("dev")){

			// Instanciation Categories
			long countCategorie = 0;
			Categorie cat1 = new Categorie();
			Categorie cat2 = new Categorie();

			if (countCategorie == 0) {

				cat1.setLibelleCategorie("J'aime lire");
				cat2.setLibelleCategorie("Harry Potter");

				categorieDAO.save(cat1);
				categorieDAO.save(cat2);
			}
			
				
			

			// Instanciation livre
			long countLivre = livreDAO.count();

			Livre liv1 = new Livre();
			Livre liv2 = new Livre();
			Livre liv3 = new Livre();

			if (countLivre == 0) {

				liv1.setNomPhoto("abcd.png");
				liv1.setTitre("Tchoupi fait de la trompette");
				liv1.setCategorie(cat1);

				liv2.setNomPhoto("efgh.png");
				liv2.setTitre("La cuisine pour les nuls");
				liv2.setCategorie(cat2);

				liv3.setNomPhoto("ijkl.png");
				liv3.setTitre("Tchoupi fait du foot");
				liv3.setCategorie(cat2);

				livreDAO.save(liv1);
				livreDAO.save(liv2);
				livreDAO.save(liv3);

			}

			// Instanciation Emprunt
			long countEmprunt = empruntDAO.count();

			if (countEmprunt == 0) {

				Emprunt emp1 = new Emprunt();
				Emprunt emp2 = new Emprunt();

				emp1.setLivre(liv1);
				emp1.setPersonne(p3);
				emp1.setDateDebutEmprunt(new java.util.Date());

				emp2.setLivre(liv2);
				emp2.setPersonne(p3);
				emp2.setDateDebutEmprunt(new java.util.Date());

				empruntDAO.save(emp1);
				empruntDAO.save(emp2);

				// Instanciation relation
				liv1.addEmprunt(emp1);
				liv2.addEmprunt(emp2);
				livreDAO.save(liv1);
				livreDAO.save(liv2);
			}
		}
	}
}