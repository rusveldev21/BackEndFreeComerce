package com.idat.freecomerce.Service;

import java.util.Optional;

import javax.transaction.Transactional;

import com.idat.freecomerce.Repository.CounterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idat.freecomerce.Model.Compteur;

@Service
@Transactional
public class CounterService {
	@Autowired
    CounterRepository repository;
	public CounterService() {
		// TODO Auto-generated constructor stub
	}

	public int nbre(int annee) {
		return repository.nbre(annee);
	}

	public void create(int annee) {
		 Compteur cpt = new Compteur();
	        cpt.setAnnee(annee);
	        cpt.setNumpanier(1);
	        repository.save(cpt);
		
	}

	public Optional<Compteur> findByAnnee(int annee) {
		// TODO Auto-generated method stub
		return repository.findByAnnee(annee);
	}

}
