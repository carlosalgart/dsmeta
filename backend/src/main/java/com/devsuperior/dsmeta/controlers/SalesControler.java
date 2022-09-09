package com.devsuperior.dsmeta.controlers;

import java.net.URI;
import java.rmi.ServerException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.dsmeta.DTO.SaleDTO;
import com.devsuperior.dsmeta.services.SalesService;

@RestController
@RequestMapping(value = "/sales")
public class SalesControler {
	@Autowired
	private SalesService salesService;

	@GetMapping
	public Page<SaleDTO> findSales(@RequestParam(value = "minDate", defaultValue = "") String minDate,
			@RequestParam(value = "maxDate", defaultValue = "") String maxDate, Pageable pageable) {
		return salesService.findSales(minDate, maxDate, pageable);
	}

	@GetMapping(path = "/all")
	public ResponseEntity<Page<SaleDTO>> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "5") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "DESC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "sellerName") String orderBy) {

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Page<SaleDTO> list = salesService.findAllPaged(pageRequest);

		return ResponseEntity.ok(list);
	}

	@GetMapping(path = "/id/{id}")
	public ResponseEntity<SaleDTO> findbyId(@PathVariable("id") Long id) {
		SaleDTO SalesDTO = salesService.findById(id);
		return ResponseEntity.ok().body(SalesDTO);
	}

	@PostMapping(path = "/new", consumes = { "application/json", "application/xml" })
	public ResponseEntity<SaleDTO> insert(@RequestBody SaleDTO newSalesDTO) throws ServerException {
		SaleDTO SalesDTO = salesService.saveAll(newSalesDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(SalesDTO.getId())
				.toUri();
		return ResponseEntity.created(uri).body(SalesDTO);
	}

	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable Long id) throws ServerException {
		salesService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<SaleDTO> update(@PathVariable Long id, @RequestBody SaleDTO SalesDTO) {
		SalesDTO = salesService.update(id, SalesDTO);
		return ResponseEntity.accepted().body(SalesDTO);
	}
}
