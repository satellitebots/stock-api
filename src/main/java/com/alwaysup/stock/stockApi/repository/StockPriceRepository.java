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
	 * @param pageable
	 * @return
	 */
	@Query("Select price from StockPrice price where price.symbol=?1 and price.interval=15 order by id")
	public List<StockPrice> getAllBySymbol(String symbol, Pageable pageable);
}
