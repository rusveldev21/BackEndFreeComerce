package com.idat.freecomerce.Service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.idat.freecomerce.Repository.CarRepository;
import com.idat.freecomerce.Repository.CounterRepository;
import com.idat.freecomerce.Repository.LcarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.idat.freecomerce.Model.Lpanier;
import com.idat.freecomerce.Model.Panier;
import com.idat.freecomerce.Model.Compteur;

@Service
@Transactional
public class CarService {
	@Autowired
    CarRepository repository;
	@Autowired
    LcarRepository lprepository;
	@Autowired
    CounterRepository crepository;
	public List<Panier> getAll() {
		System.out.println("Get all Paniers 11111...");
    	return repository.findAll(Sort.by("numero").ascending());	    	
    }
	
	
    public Optional<Panier> findById(long id) {
        return repository.findById(id);
    }
    
    
    
   
    public long save(Panier Panier) {
    	 System.out.println("Save Lpaniers...");
		List<Lpanier> lpaniers = Panier.getLpaniers();
	    for (Lpanier lp : lpaniers) {
	        lp.setNumero(Panier.getNumero());
	        lprepository.save(lp);
	       }	 
	
     Optional<Compteur> CompteurInfo = crepository.findByAnnee(Panier.getAnnee());
     	if (CompteurInfo.isPresent()) {
	    	Compteur compteur = CompteurInfo.get();
	           compteur.setNumpanier(compteur.getNumpanier()+1);
	         compteur =   crepository.save(compteur);
     	}
     	return repository.save(Panier)
                .getId();
     	
    }
  
  

    public List<Panier> findByNom(String nom) {
        return repository.findAllByNom(nom);
    }

   
    public void delete(long  id) {
        Optional<Panier> cat = repository.findById(id);
        cat.ifPresent(repository::delete);
    }
}
