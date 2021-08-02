package com.idat.freecomerce.Repository;
import java.util.List;
import java.util.Optional;

import com.idat.freecomerce.Dto.ListArticle;
import com.idat.freecomerce.Model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>{

	

	Optional<Article> findByCode(String code);

	List<Article> findAllByLibelleContaining(String libelle);

	List<Article> findAllByCcateg(String code);

	List<Article> findAllByCscateg(String code);
	
	@Query(value = "SELECT new com.idat.freecomerce.Dto.ListArticle (a.id,a.code,a.libelle,b.libelle,c.libelle,a.pa,a.pv,a.tva,a.stock,d.libelle,d.code,a.description)"
			+ " from Article a, Scategorie b, Categorie c, Supplier d  where a.cscateg = b.code and b.ccateg = c.code and a.codef = d.code")
	List<ListArticle> listArticle();
	
	@Query(value = "SELECT new com.idat.freecomerce.Dto.ListArticle (a.id,a.code,a.libelle,b.libelle,c.libelle,a.pa,a.pv,a.tva,a.stock,d.libelle,d.code,a.description)"
			+ " from Article a, Scategorie b, Categorie c, Supplier d  where a.cscateg = b.code and b.ccateg = c.code and a.codef = d.code"
			+ " and  d.code  = :code")
	List<ListArticle> listArticleFour(@Param("code") int  code);
	
	@Query(value = "SELECT count(*)  FROM Article   WHERE cscateg  = :code")
	public int nbre (@Param("code") String  code);
	
	@Query(value = "SELECT max(code) FROM Article  where cscateg = :code")
	public int max(@Param("code") String  code);

	Optional<Article> findAllById(Long id);
	

} 
