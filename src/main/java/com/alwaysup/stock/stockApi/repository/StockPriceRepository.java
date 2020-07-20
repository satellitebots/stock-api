package com.alwaysup.stock.stockApi.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.alwaysup.stock.stockApi.model.StockPrice;

public interface StockPriceRepository extends CrudRepository<StockPrice, Integer> {
	/**
	 * Get All prices for the stock by 15 minute interval (default)
	 * 
	 * @param symbol   the {@link String} symbol of the stock
	 * @param pageable {@link Pageable} object defining which results to grab
	 * @return {@link List} of {@link StockPrice}s with the same symbol and interval
	 */
	@Query("Select price from StockPrice price where price.symbol=?1 and price.interval=15 order by stock.openTime DESC")
	public List<StockPrice> getAllBySymbol(String symbol, Pageable pageable);

	/**
	 * Get All prices for the stock by specified minutes interval
	 * 
	 * @param symbol   the {@link String} symbol of the stock
	 * @param interval the {@link Integer} value representing the candle length in
	 *                 minutes
	 * @param pageable {@link Pageable} object defining which results to grab
	 * @return {@link List} of {@link StockPrice}s with the same symbol and interval
	 */
	@Query("Select price from StockPrice price where price.symbol=?1 and price.interval=?2 order by stock.openTime DESC")
	public List<StockPrice> getAllBySymbolAndInterval(String symbol, int interval, Pageable pageable);

}
