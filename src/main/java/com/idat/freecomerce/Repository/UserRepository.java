package com.idat.freecomerce.Repository;
import java.util.List;
import java.util.Optional;

import com.idat.freecomerce.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findByUsername(String name);

	List<User> findAllByEmail(String email);


}
