package com.idat.freecomerce.Repository;

import java.util.Optional;

import com.idat.freecomerce.Model.Compteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CounterRepository extends JpaRepository<Compteur, Long>{

	Optional<Compteur> findByAnnee(int annee);
	@Query(value = "SELECT count(*) FROM Compteur  where annee  = :annee")
	public int nbre(@Param("annee") int annee);
	
}
