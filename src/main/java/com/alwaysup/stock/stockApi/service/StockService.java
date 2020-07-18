package com.alwaysup.stock.stockApi.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.alwaysup.stock.stockApi.model.Stock;

public interface StockService {
	/**
	 * Create a Stock if the symbol is valid
	 * 
	 * @param symbol   The ticker symbol for the Stock
	 * @param fullName The full name of the Company
	 * @param exchange The exchange that the stock is listed on
	 * @return the newly created Stock
	 * @throws IllegalArgumentException if invalid input
	 */
	public Stock createStock(String symbol, String fullName, String exchange) throws IllegalArgumentException;

	/**
	 * Create a Stock if the symbol is valid
	 * 
	 * @param id The id of the stock to retrieve
	 * @return the retrieved Stock or null if nothing found
	 * @throws IllegalArgumentException if invalid input
	 */
	public Stock getStockById(int id) throws IllegalArgumentException;

	/**
	 * Delete a Stock by marking it as inactive
	 * 
	 * @param stock
	 * @return 0 if successful, positive integer for error
	 * @throws IllegalArgumentException if invalid input
	 */
	public int deleteStock(Stock stock) throws IllegalArgumentException;

	/**
	 * Get a Pageable List of Stocks by the exchange name
	 * 
	 * @param exchange the name of the
	 * @return Page<Stock> containing Stocks from that exchange
	 * @throws IllegalArgumentException if invalid input
	 */
	public List<Stock> getStocksByExchange(String exchange, Pageable pageable) throws IllegalArgumentException;

	/**
	 * Get a Pageable List of Stocks
	 * 
	 * @return Page<Stock> containing Stocks from that exchange
	 * @throws IllegalArgumentException if invalid input
	 */
	public List<Stock> getStocks(Pageable pageable) throws IllegalArgumentException;
}