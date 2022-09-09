package com.devsuperior.dsmeta.DTO;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import com.devsuperior.dsmeta.entity.Sale;

public class SaleDTO  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private String sellerName;
	private Integer visited;
	private Integer deals;
	private Double amount;
	private LocalDate date;
	public String getSellerName() {
		return sellerName;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	public Integer getVisited() {
		return visited;
	}
	public void setVisited(Integer visited) {
		this.visited = visited;
	}
	public Integer getDeals() {
		return deals;
	}
	public void setDeals(Integer deals) {
		this.deals = deals;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public SaleDTO(Sale sales) {
		sellerName = sales.getSellerName();
		visited = sales.getVisited();
		deals = sales.getDeals();
		amount = sales.getAmount();
		date = sales.getDate();
		id = sales.getId();

	}

	public SaleDTO(Long id, String sellerName, Integer visited, Integer deals, Double amount, LocalDate date) {
		super();
		this.sellerName = sellerName;
		this.visited = visited;
		this.deals = deals;
		this.amount = amount;
		this.date = date;
		this.id = id;
	}
	public SaleDTO() {
		super();
		
	}
	@Override
	public int hashCode() {
		return Objects.hash(amount, date, deals, sellerName, visited);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SaleDTO other = (SaleDTO) obj;
		return Objects.equals(amount, other.amount) && Objects.equals(date, other.date)
				&& Objects.equals(deals, other.deals) && Objects.equals(sellerName, other.sellerName)
				&& Objects.equals(visited, other.visited);
	}
	
	
	
	
}
