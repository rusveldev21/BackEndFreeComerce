package com.idat.freecomerce.Repository;

import java.util.List;
import java.util.Optional;

import com.idat.freecomerce.Model.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Long>{

	Optional<Categorie> findByCode(String code);

	List<Categorie> findAllByLibelleContaining(String libelle);
	@Query(value = "SELECT count(code) FROM Categorie")
	public int nbre();

	@Query(value = "SELECT max(code) FROM Categorie")
	public int max();
	

} 
