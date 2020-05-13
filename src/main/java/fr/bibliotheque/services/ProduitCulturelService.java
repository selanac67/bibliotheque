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
import fr.bibliotheque.entities.ProduitCulturel;
import storage.FileSystemStorageService;

@Service
public class ProduitCulturelService<T extends ProduitCulturel> {

	
	@Autowired
	private ProduitCulturelDAO<T> pcDAO;

	@Autowired
	FileSystemStorageService fsStorageService;

	public Optional<T> getProduitCulturel(Long idProduit) {

		return pcDAO.findById(idProduit);

	}
	

	public  List<ProduitCulturelDTO> getProduitCulturelDTO(String discriminatorValue) {

		List<ProduitCulturelDTO> produitsDTO = new ArrayList<>();
		
		List<T> produits = (List<T>) pcDAO.findByProduitType(discriminatorValue);
		

		for (T pc : produits) {
			produitsDTO.add(new ProduitCulturelDTO(pc));
		}
		return produitsDTO;
	}

	public T getProduit(Long idProduit) {

		Optional<T> produit = (Optional<T>) pcDAO.findById(idProduit);

		return produit.isPresent() ? produit.get() :null;
	}

	public void enregistrerNouveauProduit(T  produitCulturel, MultipartFile inputImageFile)
			throws IllegalStateException, IOException {

		InputStream isInputImage = null;

		// Gestion de l upload de la photo dans le cas d'un nouveau produit
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
			produitCulturel.setNomPhoto(timestampedFileName);

		}

		produitCulturel.setDateAjout(new java.util.Date());

		pcDAO.save(produitCulturel);
	}

	public void majProduitCulturel(T produitCulturel) {

		pcDAO.save(produitCulturel);
	}

	public void supprimerProduitCulturel(T produitCulturel) {

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
