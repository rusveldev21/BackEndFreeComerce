package com.idat.freecomerce.Repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.idat.freecomerce.Model.Supplier;
@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long>{


	List<Supplier> findAllByLibelleContaining(String libelle);

	Optional<Supplier> findByCode(int id);

	List<Supplier> findAllByEmail(String email);

}
