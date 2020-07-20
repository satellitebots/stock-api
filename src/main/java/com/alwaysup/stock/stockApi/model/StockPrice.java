package com.alwaysup.stock.stockApi.model;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;
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
	private float open;
	private float close;
	private float high;
	private float low;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date openTime;
	
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
	public int getInterval() {
		return interval;
	}
	public void setInterval(int interval) {
		this.interval = interval;
	}
	public float getOpen() {
		return open;
	}
	public void setOpen(float open) {
		this.open = open;
	}
	public float getClose() {
		return close;
	}
	public void setClose(float close) {
		this.close = close;
	}
	public float getHigh() {
		return high;
	}
	public void setHigh(float high) {
		this.high = high;
	}
	public float getLow() {
		return low;
	}
	public void setLow(float low) {
		this.low = low;
	}
	public Date getOpenTime() {
		return openTime;
	}
	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}
}
