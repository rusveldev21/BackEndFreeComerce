package com.idat.freecomerce.Controller;

import java.util.Optional;

import com.idat.freecomerce.Service.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.idat.freecomerce.Model.Compteur;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping("/api")
public class CounterController {
	 @Autowired
	    private CounterService counterService;
	 @GetMapping("/compteurs/{annee}")
	 public  ResponseEntity<Compteur> nbre(@PathVariable int annee) {
		 System.out.println("Get Numbers...");
		 
		 int x = counterService.nbre(annee);
		
		 if (x == 0)
		 {
			 counterService.create(annee);
		 }
		 Optional<Compteur> cpt = counterService.findByAnnee(annee);
	        return cpt.map(ResponseEntity::ok)
	                   .orElseGet(() -> ResponseEntity.notFound()
                                         .build());
		
	 }
	 
	
	 

}
