package com.alwaysup.stock.stockApi.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class StockPrice {
	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "id_generator")
	@SequenceGenerator(name = "id_generator", sequenceName = "STOCK_ID_SEQ")
	private int id;
	@JsonIgnore
	private String symbol;
	@JsonIgnore
	private int interval;
	private int open;
	private int close;
	private int high;
	private int low;

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public int getOpen() {
		return open;
	}

	public void setOpen(int open) {
		this.open = open;
	}

	public int getClose() {
		return close;
	}

	public void setClose(int close) {
		this.close = close;
	}

	public int getHigh() {
		return high;
	}

	public void setHigh(int high) {
		this.high = high;
	}

	public int getLow() {
		return low;
	}

	public void setLow(int low) {
		this.low = low;
	}
}
