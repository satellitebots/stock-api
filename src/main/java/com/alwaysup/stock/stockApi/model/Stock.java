package com.alwaysup.stock.stockApi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Stock {
	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "id_generator")
	@SequenceGenerator(name = "id_generator", sequenceName = "STOCK_ID_SEQ")
	private int id;
	private String symbol;
	private String fullName;
	private String exchange;
	@JsonIgnore
	private int active;

	public Stock() {
		super();
	}

	public Stock(String symbol, String fullName, String exchange) {
		this.symbol = symbol;
		this.fullName = fullName;
		this.exchange = exchange;
		this.active = 1;
	}

	public String getSymbol() {
		return this.symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getExchange() {
		return this.exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return String.format("%s:%s", this.exchange, this.symbol);
	}

	@Override
	public boolean equals(Object obj) {
		Stock compStock = null;
		try {
			compStock = (Stock) obj;
		} catch (Exception e) {
			return false;
		}
		return this.symbol.equalsIgnoreCase(compStock.getSymbol())
				&& this.exchange.equalsIgnoreCase(compStock.getExchange());
	}
}
