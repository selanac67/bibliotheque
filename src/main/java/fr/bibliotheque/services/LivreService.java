package fr.bibliotheque.services;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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

import fr.bibliotheque.dao.LivreDAO;
import fr.bibliotheque.dto.LivreDTO;
import fr.bibliotheque.entities.Livre;
import storage.FileSystemStorageService;

@Service
public class LivreService {

	final static String SOUS_REP_UPLOAD = "livres";

	@Autowired
	LivreDAO livreDAO;

	@Autowired
	FileSystemStorageService fsStorageService;

	public List<Livre> getListeLivres() {

		List<Livre> livres = new ArrayList<>();

		Iterable<Livre> iterLivre = livreDAO.findAll();
		iterLivre.forEach(livres::add);

		return livres;
	}

	public List<LivreDTO> getLivresDTO() {

		List<LivreDTO> livresDTO = new ArrayList<>();
		List<Livre> livres = new ArrayList<>();

		Iterable<Livre> iterLivre = livreDAO.findAll();
		iterLivre.forEach(livres::add);

		for (Livre livre : livres) {
			livresDTO.add(new LivreDTO(livre));
		}
		return livresDTO;
	}

	public Livre getLivre(Long idLivre) {

		Optional<Livre> livre = livreDAO.findById(idLivre);

		return livre.isPresent() ? livre.get() : new Livre();
	}

	public void enregistrerNouveauLivre(Livre livre, MultipartFile inputImageFile)
			throws IllegalStateException, IOException {

		// Gestion de l upload de la photo dans le cas d'un nouveau livre
		if (inputImageFile.getResource().isFile()) {
			LocalDate localDate = LocalDate.now();
			String localDateString = localDate.format(DateTimeFormatter.ISO_DATE);
			String timestampedFileName = localDateString + "-" + inputImageFile.getOriginalFilename();

			Path destFileNamePath = Paths.get(fsStorageService.getRootLocation().toString(), SOUS_REP_UPLOAD,
					timestampedFileName);

			File destFile = destFileNamePath.toFile();

			inputImageFile.transferTo(destFile);

			resizeImage(destFile);

			// Sauvegarde en base de l'objet livre
			livre.setNomPhoto(timestampedFileName);
		}

		livre.setDateAjout(new java.util.Date());

		livreDAO.save(livre);
	}

	public void majLivre(Livre livre) {

		livreDAO.save(livre);
	}

	public void supprimerLivre(Livre livre) {

		String nomPhoto = livre.getNomPhoto();

		if (nomPhoto != null) {
			Path photoPath = Paths.get(fsStorageService.getRootLocation().toString(), SOUS_REP_UPLOAD, nomPhoto);

			File photo = photoPath.toFile();

			photo.delete();

		}


		livreDAO.delete(livre);

	}

	private boolean resizeImage(File imageFile) throws IOException {
		BufferedImage image = ImageIO.read(imageFile);
		BufferedImage resized = resize(image, 500, 500);
		return ImageIO.write(resized, "png", imageFile);
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

}
