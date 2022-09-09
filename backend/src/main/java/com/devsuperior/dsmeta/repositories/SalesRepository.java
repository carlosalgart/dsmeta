package com.devsuperior.dsmeta.repositories;


import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dsmeta.DTO.SaleDTO;
import com.devsuperior.dsmeta.entity.Sale;

public interface SalesRepository extends JpaRepository<Sale, Long> {

	@Query("SELECT obj FROM Sale obj WHERE obj.date BETWEEN :min AND :max ORDER BY obj.amount DESC")
	Page<SaleDTO> findSales(LocalDate min, LocalDate max, Pageable pageable);

}