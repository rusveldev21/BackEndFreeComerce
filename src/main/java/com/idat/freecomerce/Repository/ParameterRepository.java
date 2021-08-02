package com.idat.freecomerce.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.idat.freecomerce.Model.Parametre;
@Repository
public interface ParameterRepository extends JpaRepository<Parametre, Long>{


}
