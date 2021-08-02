package com.idat.freecomerce.Service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.idat.freecomerce.Model.User;
import com.idat.freecomerce.Repository.ParameterRepository;
import com.idat.freecomerce.Repository.SupplierRepository;
import com.idat.freecomerce.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.idat.freecomerce.Model.Supplier;
import com.idat.freecomerce.Model.Parametre;

@Service
@Transactional
public class SupplierService {
	@Autowired
    SupplierRepository repository;
	@Autowired
    ParameterRepository paramRepository;
	@Autowired
    UserRepository userRepository;
	public List<Supplier> getAll() {
		System.out.println("Get all Fournisseurs 11111...");
    	return repository.findAll(Sort.by("libelle").ascending());	    	
    }
	
	
    public Optional<Supplier> findByCode(int id) {
        return repository.findByCode(id);
    }
    
    public long save(Supplier Four) {
    	System.out.println("save  all Fournisseurs 11111...");
    	long id = 1;
		 Optional<Parametre> ParamInfo = paramRepository.findById(id);
	 	    if (ParamInfo.isPresent()) {
		    	Parametre param = ParamInfo.get();
		           param.setNumf(param.getNumf()+1);
		           param = paramRepository.save(param);
		    }
	 	   User user = new User();
	        user.setUsername(Four.getEmail());
	        user.setNom(Four.getLibelle());
	        user.setCode(Four.getCode());
	        user.setEmail(Four.getEmail());
	        user.setPassword(Four.getPwd());
	        user.setRole("FOUR");
	        user.setActive(true);
	        userRepository.save(user);
        return repository.save(Four)
                             .getId();
    }
    
    
    public void update(int code, Supplier Supplier) {
        Optional<Supplier> four = repository.findByCode(code);
        if (four.isPresent()) {
            Supplier fr = four.get();
            fr.setLibelle(Supplier.getLibelle());
            fr.setAdresse(Supplier.getAdresse());
            repository.save(fr);
        }
    }
    
    
    public List<Supplier> findByEmail(String email) {
        return repository.findAllByEmail(email);
    }
  

    public List<Supplier> findByLibelle(String libelle) {
        return repository.findAllByLibelleContaining(libelle);
    }

    public void delete(int code) {
        Optional<Supplier> cat = repository.findByCode(code);
        cat.ifPresent(repository::delete);
    }
	

}
