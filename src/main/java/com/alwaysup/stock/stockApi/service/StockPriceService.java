package com.alwaysup.stock.stockApi.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;

import com.alwaysup.stock.stockApi.model.StockPrice;

public interface StockPriceService {
	/**
	 * Create a StockPrice for an existing Stock
	 * 
	 * @param symbol The ticker symbol for the Stock
	 * @param interval the interval in minutes
	 * @param open the price at open
	 * @param close the price at close
	 * @param high the highest price in between
	 * @param low the lowest price in between
	 * @param openTime the {@link Date} representing the start of the candle (yyyy-MM-dd HH:mm:ss)
	 * @return
	 * @throws IllegalArgumentException
	 */
	public StockPrice createStockPrice(String symbol, int interval, float open, float close, float high, float low,
			Date openTime) throws IllegalArgumentException;

	/**
	 * Update a StockPrice for an existing Stock
	 * 
	 * @param symbol The ticker symbol for the Stock
	 * @param interval the interval in minutes
	 * @param open the price at open
	 * @param close the price at close
	 * @param high the highest price in between
	 * @param low the lowest price in between
	 * @param openTime the {@link Date} representing the start of the candle (yyyy-MM-dd HH:mm:ss)
	 * @return
	 * @throws IllegalArgumentException
	 */
	public StockPrice updateStockPrice(String symbol, int interval, float open, float close, float high, float low,
			Date openTime) throws IllegalArgumentException;

	
	/**
	 * Get the stock prices by symbol
	 * @param symbol
	 * @param pageable
	 * @return
	 * @throws IllegalArgumentException
	 */
	public List<StockPrice> getStockPrices(String symbol, Pageable pageable) throws IllegalArgumentException;
}