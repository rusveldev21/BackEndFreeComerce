package com.idat.freecomerce.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.idat.freecomerce.Model.Lpanier;
@Repository
public interface LcarRepository extends JpaRepository<Lpanier, Long>{

}
