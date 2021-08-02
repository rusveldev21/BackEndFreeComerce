package com.idat.freecomerce.Repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.idat.freecomerce.Model.Panier;
@Repository
public interface CarRepository extends JpaRepository<Panier, Long>{

	List<Panier> findAllByNom(String nom);

}
