package br.com.kdev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.kdev.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,	Long>{

}
