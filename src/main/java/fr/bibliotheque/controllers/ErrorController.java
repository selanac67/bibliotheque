package fr.bibliotheque.controllers;

import org.springframework.web.bind.annotation.GetMapping;


public class ErrorController {
	
	@GetMapping("/error")
	public String erreur(){
		return "Un problème est apparu dans la biblioteque";
	}

}
