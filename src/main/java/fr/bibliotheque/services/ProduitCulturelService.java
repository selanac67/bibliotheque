package fr.bibliotheque.services;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import fr.bibliotheque.dao.ProduitCulturelDAO;
import fr.bibliotheque.dto.ProduitCulturelDTO;
import fr.bibliotheque.entities.Jeu;
import fr.bibliotheque.entities.Livre;
import fr.bibliotheque.entities.ProduitCulturel;
import storage.FileSystemStorageService;

@Service
public class ProduitCulturelService {

	@Autowired
	private ProduitCulturelDAO<Livre> livreDAO;

	@Autowired
	private ProduitCulturelDAO<Jeu> jeuDAO;

	@Autowired
	private ProduitCulturelDAO<ProduitCulturel> pcDAO;

	@Autowired
	FileSystemStorageService fsStorageService;

	public Optional<ProduitCulturel> getProduitCulturel(Long idProduit) {

		return pcDAO.findById(idProduit);

	}

	public List<Livre> getListeLivres() {

		List<Livre> livres = new ArrayList<>();

		Iterable<Livre> iterLivre = livreDAO.findAll();
		iterLivre.forEach(livres::add);

		return livres;
	}

	public List<ProduitCulturelDTO> getLivresDTO() {

		List<ProduitCulturelDTO> livresDTO = new ArrayList<>();
		List<Livre> livres = new ArrayList<>();

		Iterable<Livre> iterLivre = livreDAO.findAll();
		iterLivre.forEach(livres::add);

		for (Livre livre : livres) {
			livresDTO.add(new ProduitCulturelDTO(livre));
		}
		return livresDTO;
	}

	public Livre getLivre(Long idLivre) {

		Optional<Livre> livre = livreDAO.findById(idLivre);

		return livre.isPresent() ? livre.get() : new Livre();
	}

	public void enregistrerNouveauLivre(Livre livre, MultipartFile inputImageFile)
			throws IllegalStateException, IOException {

		InputStream isInputImage = null;

		// Gestion de l upload de la photo dans le cas d'un nouveau livre
		if (inputImageFile.getSize() > 0) {
			isInputImage = inputImageFile.getInputStream();

			LocalDate localDate = LocalDate.now();
			String localDateString = localDate.format(DateTimeFormatter.ISO_DATE);
			String timestampedFileName = localDateString + "-" + inputImageFile.getOriginalFilename();

			Path destFileNamePath = Paths.get(fsStorageService.getRootLocation().toString(), getSousRepUpload(),
					timestampedFileName);

			File destFile = destFileNamePath.toFile();

			// inputImageFile.transferTo(destFile);

			// resizeImage(destFile);

			resizeImage(isInputImage, destFile);

			// Sauvegarde en base de l'objet livre
			livre.setNomPhoto(timestampedFileName);

		}

		livre.setDateAjout(new java.util.Date());

		livreDAO.save(livre);
	}

	public void majProduitCulturel(ProduitCulturel produitCulturel) {

		pcDAO.save(produitCulturel);
	}

	public void supprimerProduitCulturel(ProduitCulturel produitCulturel) {

		String nomPhoto = produitCulturel.getNomPhoto();

		if (nomPhoto != null) {
			Path photoPath = Paths.get(fsStorageService.getRootLocation().toString(), getSousRepUpload(), nomPhoto);

			File photo = photoPath.toFile();

			photo.delete();

		}
		pcDAO.delete(produitCulturel);

	}

	private boolean resizeImage(File imageFile) throws IOException {
		BufferedImage image = ImageIO.read(imageFile);
		BufferedImage resized = resize(image, 200, 200);
		return ImageIO.write(resized, "png", imageFile);
	}

	private boolean resizeImage(InputStream imageFile, File destFile) throws IOException {
		BufferedImage image = ImageIO.read(imageFile);
		BufferedImage resized = resize(image, 200, 200);
		return ImageIO.write(resized, "png", destFile);
	}

	private static BufferedImage resize(BufferedImage img, double maxHeight, double maxWidth) {

		int finalHeight, finalWidth;
		int imgHeight = img.getHeight();
		int imgWidth = img.getWidth();

		double ratioReelSurTheoriqueHauteur = (double) (imgHeight / maxHeight);
		double ratioReelSurTheoriqueLargeur = (double) (imgWidth / maxWidth);

		if (ratioReelSurTheoriqueHauteur > ratioReelSurTheoriqueLargeur) {
			finalHeight = (int) (imgHeight / ratioReelSurTheoriqueHauteur);
			finalWidth = (int) (imgWidth / ratioReelSurTheoriqueHauteur);
		} else {
			finalHeight = (int) (imgHeight / ratioReelSurTheoriqueLargeur);
			finalWidth = (int) (imgWidth / ratioReelSurTheoriqueLargeur);
		}

		Image tmp = img.getScaledInstance(finalWidth, finalHeight, Image.SCALE_SMOOTH);

		BufferedImage resized = new BufferedImage(finalWidth, finalHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = resized.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();
		return resized;
	}

	// TODO FIX
	private String getSousRepUpload() {

		return "livres";
	}

}
