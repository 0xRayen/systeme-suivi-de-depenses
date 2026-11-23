package com.iset.suividepenses.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iset.suividepenses.entity.Depense;

public interface DepenseRepository extends JpaRepository<Depense, Long> {

    boolean existsByCategorieId(Long categorieId);

    List<Depense> findAllByOrderByDateDescIdDesc();
}
