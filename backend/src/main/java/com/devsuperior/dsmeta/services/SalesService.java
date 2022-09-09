package com.devsuperior.dsmeta.services;


import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsmeta.DTO.SaleDTO;
import com.devsuperior.dsmeta.entity.Sale;
import com.devsuperior.dsmeta.repositories.SalesRepository;
import com.devsuperior.dsmeta.services.exceptions.ResourceNotFoundException;

@Service
public class SalesService {
	@Autowired
	private SalesRepository salesRepository;

	@Transactional(readOnly = true)
	public Page<SaleDTO> findAllPaged(PageRequest pageRequest) {
		Page<Sale> list = salesRepository.findAll(pageRequest);
		return list.map(SaleDTO::new);
	}

	@Transactional(readOnly = true)
	public SaleDTO saveAll(SaleDTO Sales) {
		Sale entity = new Sale();
		entity.setSellerName(Sales.getSellerName());
		entity.setDate(Sales.getDate());
		entity = salesRepository.save(entity);
		return new SaleDTO(entity);
	}

	@Transactional(readOnly = true)
	public SaleDTO findById(Long id) {
		Optional<Sale> obj = salesRepository.findById(id);
		Sale entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity ID not found"));
		return new SaleDTO(entity);
	}

	@Transactional
	public SaleDTO update(Long id, SaleDTO SalesDTO) {
		try {
			Sale entity = salesRepository.getReferenceById(id);
			entity.setSellerName(SalesDTO.getSellerName());
			entity = salesRepository.save(entity);
			return new SaleDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("ID not found: " + id);
		}

	}
	
	@Transactional
	public void delete(Long id) {
		try {
			salesRepository.deleteById(id);

		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found: " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Id exists in another reference" + id);
		}
	}

	
	@Transactional
	public Page<SaleDTO> findSales(String minDate, String maxDate, Pageable pageable){
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		LocalDate min = LocalDate.parse(minDate);
		LocalDate max = maxDate.equals("")? today: LocalDate.parse(maxDate);
		return salesRepository.findSales(min, max, pageable);
		
	}
}
