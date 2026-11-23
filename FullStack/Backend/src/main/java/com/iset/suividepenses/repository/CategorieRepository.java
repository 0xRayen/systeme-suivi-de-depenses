package com.iset.suividepenses.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iset.suividepenses.entity.Categorie;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {

    List<Categorie> findAllByOrderByNomAsc();

    boolean existsByNomIgnoreCase(String nom);

    boolean existsByNomIgnoreCaseAndIdNot(String nom, Long id);

    Optional<Categorie> findByNomIgnoreCase(String nom);
}
