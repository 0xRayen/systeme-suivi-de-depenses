package com.iset.suividepenses.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.iset.suividepenses.entity.Depense;

public interface DepenseRepository extends JpaRepository<Depense, Long> {

    boolean existsByCategorieId(Long categorieId);

    List<Depense> findAllByOrderByDateDescIdDesc();

    @Query("""
            SELECT d FROM Depense d
            WHERE (:categorieId IS NULL OR d.categorie.id = :categorieId)
              AND (:startDate IS NULL OR d.date >= :startDate)
              AND (:endDate IS NULL OR d.date <= :endDate)
              AND (:q IS NULL OR LOWER(d.titre) LIKE LOWER(CONCAT('%', :q, '%')))
            ORDER BY d.date DESC, d.id DESC
            """)
    List<Depense> rechercher(
            @Param("categorieId") Long categorieId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("q") String q);
}
